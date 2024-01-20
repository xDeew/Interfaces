import javax.swing.*;
import java.awt.event.ActionListener;

public class App {

	public static void main(String[] args) {
		JFrame frame = new JFrame("ActionListener para Varios Campos de Texto");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setSize(300, 150);

		JTextField textField1 = new JTextField();
		textField1.setBounds(100, 10, 180, 20);
		JTextField textField2 = new JTextField();
		textField2.setBounds(100, 40, 180, 20);
		JTextField textField3 = new JTextField();
		textField3.setBounds(100, 70, 180, 20);

		frame.add(textField1);
		frame.add(textField2);
		frame.add(textField3);

		JLabel label1 = new JLabel("Campo 1:");
		label1.setBounds(10, 10, 80, 20);
		JLabel label2 = new JLabel("Campo 2:");
		label2.setBounds(10, 40, 80, 20);
		JLabel label3 = new JLabel("Campo 3:");
		label3.setBounds(10, 70, 80, 20);

		frame.add(label1);
		frame.add(label2);
		frame.add(label3);

		ActionListener listener = e -> {
			JTextField source = (JTextField) e.getSource();
			String text = source.getText();
			JOptionPane.showMessageDialog(frame, "Texto ingresado: " + text);
		};

		textField1.addActionListener(listener);
		textField2.addActionListener(listener);
		textField3.addActionListener(listener);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
