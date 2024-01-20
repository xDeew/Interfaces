package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioGridBagLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("GridBagLayout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10); 

     
        JLabel titulo = new JLabel("Manolo y Asociados");
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD)); 
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2; 
        constraints.anchor = GridBagConstraints.CENTER;
        frame.add(titulo, constraints);

        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.WEST;

        JLabel labelNombre = new JLabel("Nombre:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        frame.add(labelNombre, constraints);

        JTextField textNombre = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        frame.add(textNombre, constraints);

        JLabel labelCorreo = new JLabel("Correo:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        frame.add(labelCorreo, constraints);

        JTextField textCorreo = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        frame.add(textCorreo, constraints);

        JLabel labelDescripcion = new JLabel("Descripción:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        frame.add(labelDescripcion, constraints);

        JTextArea textDescripcion = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(textDescripcion);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridheight = 3; 
        frame.add(scrollPane, constraints);

        ButtonGroup opciones = new ButtonGroup();
        JRadioButton opcion1 = new JRadioButton("Opción 1");
        JRadioButton opcion2 = new JRadioButton("Opción 2");
        JRadioButton opcion3 = new JRadioButton("Opción 3");
        opciones.add(opcion1);
        opciones.add(opcion2);
        opciones.add(opcion3);
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.gridheight = 1; 
        frame.add(opcion1, constraints);
        constraints.gridy = 7;
        frame.add(opcion2, constraints);
        constraints.gridy = 8;
        frame.add(opcion3, constraints);

        JButton botonEnviar = new JButton("Enviar");
        constraints.gridx = 1;
        constraints.gridy = 9;
        constraints.anchor = GridBagConstraints.LAST_LINE_END; 
        frame.add(botonEnviar, constraints);

        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = textNombre.getText();
                String correo = textCorreo.getText();
                String descripcion = textDescripcion.getText();
                String mensaje = "Enviado\nNombre: " + nombre + "\nCorreo: " + correo + "\nDescripción: " + descripcion;
                JOptionPane.showMessageDialog(frame, mensaje);
            }
        });

        frame.pack();
        frame.setVisible(true);
    }
}
