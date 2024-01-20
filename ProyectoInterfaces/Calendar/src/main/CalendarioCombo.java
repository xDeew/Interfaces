package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarioCombo {

    private static JComboBox<String> comboMeses;
    private static JComboBox<Integer> comboDias;
    private static JComboBox<String> comboDiasSemana;
    private static JTextArea textAreaFecha;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calendario");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setSize(new Dimension(500, 200));
        agregarComponentes(frame);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void agregarComponentes(JFrame frame) {
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                          "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        comboMeses = new JComboBox<>(meses);

        comboDias = new JComboBox<>();

        String[] diasSemana = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        comboDiasSemana = new JComboBox<>(diasSemana);

        ActionListener actualizarFechaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDias();
                actualizarTextAreaFecha();
            }
        };

        comboMeses.addActionListener(actualizarFechaListener);
        comboDias.addActionListener(actualizarFechaListener); // Agregar el listener al comboDias
        comboDiasSemana.addActionListener(actualizarFechaListener);

        JPanel panelCombos = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelCombos.add(comboMeses);
        panelCombos.add(comboDias);
        panelCombos.add(comboDiasSemana);
        frame.add(panelCombos, BorderLayout.NORTH);

        textAreaFecha = new JTextArea(2, 20);
        textAreaFecha.setEditable(false);
        textAreaFecha.setFont(new Font("Serif", Font.BOLD, 16));
        JScrollPane scrollPane = new JScrollPane(textAreaFecha);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarDias();
        actualizarTextAreaFecha();
    }

    private static void actualizarDias() {
        int mes = comboMeses.getSelectedIndex();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int diasEnMes = new GregorianCalendar(year, mes, 1).getActualMaximum(Calendar.DAY_OF_MONTH);
        
        Integer diaSeleccionado = (Integer) comboDias.getSelectedItem();
        comboDias.removeAllItems();
        for (int i = 1; i <= diasEnMes; i++) {
            comboDias.addItem(i);
        }
        
        if (diaSeleccionado != null && diaSeleccionado <= diasEnMes) {
            comboDias.setSelectedItem(diaSeleccionado);
        } else {
            comboDias.setSelectedIndex(0);
        }
    }

    private static void actualizarTextAreaFecha() {
        String mes = (String) comboMeses.getSelectedItem();
        Integer dia = (Integer) comboDias.getSelectedItem();
        String diaSemana = (String) comboDiasSemana.getSelectedItem();
        
        if (dia != null && mes != null && diaSemana != null) {
            String fecha = "Mes: " + mes + ". Días: " + dia + ". Día de la semana: " + diaSemana + ".";
            textAreaFecha.setText(fecha);
        }
    }
}
