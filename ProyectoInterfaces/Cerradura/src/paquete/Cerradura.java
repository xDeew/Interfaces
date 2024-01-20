package paquete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cerradura {
    private static final String COMBINACION_CORRECTA = "1234";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Caja Fuerte");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JSpinner[] spinners = new JSpinner[4];
        for (int i = 0; i < spinners.length; i++) {
            spinners[i] = new JSpinner(new SpinnerNumberModel(0, 0, 9, 1));
            JComponent comp = spinners[i].getEditor();
            JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
            field.setFont(new Font("Serif", Font.BOLD, 24));
            spinners[i].setPreferredSize(new Dimension(60, 60));
            frame.add(spinners[i]);
        }

        JButton boton = new JButton("Desbloquear");
        boton.setFont(new Font("Serif", Font.BOLD, 20));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(30, 150, 75));
        boton.setOpaque(true);
        boton.setBorderPainted(false);

        JLabel etiqueta = new JLabel("CAJA CERRADA");
        etiqueta.setFont(new Font("Serif", Font.BOLD, 20));
        etiqueta.setForeground(Color.RED);

        frame.add(boton);
        frame.add(etiqueta);

        frame.getContentPane().setBackground(Color.WHITE);

        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String combinacion = "";
                for (JSpinner spinner : spinners) {
                    combinacion += spinner.getValue().toString();
                }

                if (COMBINACION_CORRECTA.equals(combinacion)) {
                    etiqueta.setText("CAJA DESBLOQUEADA");
                    etiqueta.setForeground(new Color(30, 150, 75));
                } else {
                    etiqueta.setText("CAJA CERRADA");
                    etiqueta.setForeground(Color.RED);
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
