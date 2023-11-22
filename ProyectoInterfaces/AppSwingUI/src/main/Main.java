package main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Main extends JFrame {

    public Main() {
        setTitle("GUI");
        setLayout(null);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel radioPanel = new JPanel(null);
        radioPanel.setBounds(10, 10, 140, 100);
        radioPanel.setBorder(new TitledBorder("RadioButtons"));

        JPanel checkPanel = new JPanel(null);
        checkPanel.setBounds(160, 10, 140, 100);
        checkPanel.setBorder(new TitledBorder("CheckBoxes"));

        JPanel labelPanel = new JPanel(null);
        labelPanel.setBounds(10, 120, 140, 60);
        labelPanel.setBorder(new TitledBorder("Etiquetas"));

        JPanel textFieldPanel = new JPanel(null);
        textFieldPanel.setBounds(160, 110, 140, 80);
        textFieldPanel.setBorder(new TitledBorder("Campos de Texto"));

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBounds(10, 190, 290, 60);
        buttonPanel.setBorder(new TitledBorder("Botones"));

      
        JRadioButton radioButton1 = new JRadioButton("Opción 1");
        radioButton1.setBounds(10, 20, 120, 20);
        radioButton1.setForeground(Color.RED);

        JRadioButton radioButton2 = new JRadioButton("Opción 2");
        radioButton2.setBounds(10, 40, 120, 20);
        radioButton2.setForeground(Color.GREEN);

        JRadioButton radioButton3 = new JRadioButton("Opción 3");
        radioButton3.setBounds(10, 60, 120, 20);
        radioButton3.setForeground(Color.BLUE);

        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);
        radioGroup.add(radioButton3);

        radioPanel.add(radioButton1);
        radioPanel.add(radioButton2);
        radioPanel.add(radioButton3);

        // Check Boxes
        JCheckBox checkBox1 = new JCheckBox("Check 1");
        checkBox1.setBounds(10, 20, 120, 20);
        checkBox1.setForeground(Color.RED);

        JCheckBox checkBox2 = new JCheckBox("Check 2");
        checkBox2.setBounds(10, 40, 120, 20);
        checkBox2.setForeground(Color.BLUE);

        checkPanel.add(checkBox1);
        checkPanel.add(checkBox2);

        JLabel label1 = new JLabel("Etiqueta 1");
        label1.setBounds(10, 20, 120, 20);
        label1.setForeground(Color.RED);

        JLabel label2 = new JLabel("Etiqueta 2");
        label2.setBounds(10, 40, 120, 20);
        label2.setForeground(Color.GREEN);

        labelPanel.add(label1);
        labelPanel.add(label2);

        JTextField textField1 = new JTextField();
        textField1.setBounds(10, 20, 120, 20);

        JTextField textField2 = new JTextField();
        textField2.setBounds(10, 50, 120, 20);

        textFieldPanel.add(textField1);
        textFieldPanel.add(textField2);

        JButton button1 = new JButton("Botón 1");
        button1.setBounds(10, 20, 120, 20);

        JButton button2 = new JButton("Botón 2");
        button2.setBounds(160, 20, 120, 20);

        buttonPanel.add(button1);
        buttonPanel.add(button2);

        // Agrego paneles al JFrame
        add(radioPanel);
        add(checkPanel);
        add(labelPanel);
        add(textFieldPanel);
        add(buttonPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
