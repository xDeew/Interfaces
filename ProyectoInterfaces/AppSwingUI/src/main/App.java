package main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

// Main Frame
public class App extends JFrame {
    private static final long serialVersionUID = 1L;

    public App() {
        setTitle("GUI");
        setLayout(null);
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RadioPanel radioPanel = new RadioPanel();
        radioPanel.setBounds(10, 10, 140, 100);

        CheckPanel checkPanel = new CheckPanel();
        checkPanel.setBounds(160, 10, 140, 100);

        JPanel labelPanel = new JPanel(null);
        labelPanel.setBounds(10, 120, 140, 60);
        labelPanel.setBorder(new TitledBorder("Etiquetas"));
        JLabel label1 = new JLabel("Etiqueta 1");
        label1.setBounds(10, 20, 120, 20);
        label1.setForeground(Color.RED);
        JLabel label2 = new JLabel("Etiqueta 2");
        label2.setBounds(10, 40, 120, 20);
        label2.setForeground(Color.GREEN);
        labelPanel.add(label1);
        labelPanel.add(label2);

        TextFieldPanel textFieldPanel = new TextFieldPanel();
        textFieldPanel.setBounds(160, 110, 150, 110);

        ButtonPanel buttonPanel = new ButtonPanel();
        buttonPanel.setBounds(320, 140, 150, 80);

        add(radioPanel);
        add(checkPanel);
        add(labelPanel);
        add(textFieldPanel);
        add(buttonPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new App();
    }
}

// RadioPanel
class RadioPanel extends JPanel {
    public RadioPanel() {
        setLayout(null);
        setBorder(new TitledBorder("RadioButtons"));
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
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
    }
}

// CheckPanel
class CheckPanel extends JPanel {
    public CheckPanel() {
        setLayout(null);
        setBorder(new TitledBorder("CheckBoxes"));
        JCheckBox checkBox1 = new JCheckBox("Check 1");
        checkBox1.setBounds(10, 20, 120, 20);
        checkBox1.setForeground(Color.RED);
        JCheckBox checkBox2 = new JCheckBox("Check 2");
        checkBox2.setBounds(10, 40, 120, 20);
        checkBox2.setForeground(Color.BLUE);
        add(checkBox1);
        add(checkBox2);
    }
}

// TextFieldPanel
class TextFieldPanel extends JPanel {
    public TextFieldPanel() {
        setLayout(null);
        setBorder(new TitledBorder("Campos de Texto"));
        JTextField textField1 = new JTextField();
        textField1.setBounds(10, 20, 120, 20);
        JTextField textField2 = new JTextField();
        textField2.setBounds(10, 50, 120, 20);
        JTextField textField3 = new JTextField();
        textField3.setBounds(10, 80, 120, 20);
        add(textField1);
        add(textField2);
        add(textField3);
    }
}

// ButtonPanel
class ButtonPanel extends JPanel {
    public ButtonPanel() {
        setLayout(null);
        setBorder(new TitledBorder("Botones"));
        JButton button1 = new JButton("Botón 1");
        button1.setBounds(10, 15, 120, 20);
        JButton button2 = new JButton("Botón 2");
        button2.setBounds(10, 45, 120, 20);
        add(button1);
        add(button2);
    }
}
