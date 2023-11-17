package main;

import javax.swing.*;
import java.awt.*;

public class AplicacionSwing extends JFrame {

    
	private static final long serialVersionUID = 1L;

	public AplicacionSwing() {
        initUI();
    }

    private void initUI() {
        setLayout(null);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(Color.BLUE);
        panel1.setBounds(10, 10, 250, 150); 

        JButton button1 = new JButton("Botón 1");
        button1.setBounds(50, 10, 150, 25); 

        JTextField textField1 = new JTextField();
        textField1.setBounds(50, 45, 150, 25); 

        JTextArea textArea1 = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(textArea1);
        scrollPane1.setBounds(50, 80, 150, 60); 

        panel1.add(button1);
        panel1.add(textField1);
        panel1.add(scrollPane1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.GREEN);
        panel2.setBounds(270, 10, 250, 150); 

        JButton button2 = new JButton("Botón 2");
        button2.setBounds(50, 10, 150, 25); 

        JTextField textField2 = new JTextField();
        textField2.setBounds(50, 45, 150, 25); 

        JTextArea textArea2 = new JTextArea();
        JScrollPane scrollPane2 = new JScrollPane(textArea2);
        scrollPane2.setBounds(50, 80, 150, 60); 

        panel2.add(button2);
        panel2.add(textField2);
        panel2.add(scrollPane2);

        add(panel1);
        add(panel2);

      
        setTitle("Programa con 2 Paneles");
        setSize(540, 200); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
       new AplicacionSwing();
    }
}
