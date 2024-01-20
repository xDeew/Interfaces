package main; 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Menus");
        ventana.setBounds(100, 100, 500, 300);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel lamina = new JPanel();
        lamina.setLayout(new BorderLayout());

        JLabel eleccion = new JLabel();
        JMenuBar barra = new JMenuBar();

        JMenu archivo = new JMenu("Archivo");
        JMenu estilo = new JMenu("Estilo");
        JMenu tamano = new JMenu("Tamaño");

        JMenuItem copiar = new JMenuItem("Copiar");
        JMenuItem cortar = new JMenuItem("Cortar");
        JMenuItem pegar = new JMenuItem("Pegar");

        JMenu edicion = new JMenu("Edición");
        JMenuItem buscar = new JMenuItem("Buscar");
        JMenuItem guardar = new JMenuItem("Guardar");

        JCheckBoxMenuItem negrita = new JCheckBoxMenuItem("Negrita");
        JCheckBoxMenuItem cursiva = new JCheckBoxMenuItem("Cursiva");

        ButtonGroup grupo = new ButtonGroup();
        JRadioButtonMenuItem pequeno = new JRadioButtonMenuItem("12");
        JRadioButtonMenuItem mediano = new JRadioButtonMenuItem("14", true);
        JRadioButtonMenuItem grande = new JRadioButtonMenuItem("16");

        copiar.addActionListener(e -> textArea.copy());
        cortar.addActionListener(e -> textArea.cut());
        pegar.addActionListener(e -> textArea.paste());

        negrita.addActionListener(e -> {
            if (negrita.isSelected()) {
                textArea.setFont(textArea.getFont().deriveFont(Font.BOLD));
                cursiva.setSelected(false);
            } else {
                textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
            }
        });

        cursiva.addActionListener(e -> {
            if (cursiva.isSelected()) {
                textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC));
                negrita.setSelected(false);
            } else {
                textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN));
            }
        });
        
        pequeno.addActionListener(e -> textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), 12)));
        mediano.addActionListener(e -> textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), 14)));
        grande.addActionListener(e -> textArea.setFont(new Font(textArea.getFont().getName(), textArea.getFont().getStyle(), 16)));

        edicion.add(buscar);
        edicion.add(guardar);

        archivo.add(copiar);
        archivo.add(cortar);
        archivo.add(pegar);
        archivo.addSeparator();
        archivo.add(edicion);

        estilo.add(negrita);
        estilo.add(cursiva);

        grupo.add(pequeno);
        grupo.add(mediano);
        grupo.add(grande);

        tamano.add(pequeno);
        tamano.add(mediano);
        tamano.add(grande);

        barra.add(archivo);
        barra.add(estilo);
        barra.add(tamano);

        lamina.add(barra, BorderLayout.NORTH);
        lamina.add(scrollPane, BorderLayout.CENTER); 
        ventana.add(lamina);
        ventana.setVisible(true);
    }
}
