package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

public class RegistroEmpleados extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField txtNombre, txtEdad, txtDireccion, txtTelefono, txtPuesto;

	public RegistroEmpleados() {
		this.setTitle("Registro de Empleados");

		this.setSize(400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);

		JLabel lblTitulo = new JLabel("Registro de Empleados");
		lblTitulo.setBounds(10, 10, 380, 25);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), Font.BOLD, 20));
		this.add(lblTitulo);

		ImageIcon imagen = redimensionarImagen("resources/tuna.png", 170, 170);

		Image iconoPropio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tuna.png"));
		setIconImage(iconoPropio);

		JLabel lblImagen = new JLabel(imagen);
		lblImagen.setBounds(133, 35, 160, 160);
		panel.add(lblImagen);

		txtNombre = crearCampo("Nombre:", 200);
		txtEdad = crearCampo("Edad:", 240);
		txtDireccion = crearCampo("Dirección:", 280);
		txtTelefono = crearCampo("Teléfono:", 320);
		txtPuesto = crearCampo("Puesto:", 360);

		this.add(panel);
		panel.add(lblImagen);

		JButton btnEnviar = crearBoton("Enviar", 10, 400);
		JButton btnCancelar = crearBoton("Cancelar", 120, 400);

		btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarInformacionEmpleado();
			}
		});

		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});

		panel.add(btnEnviar);
		panel.add(btnCancelar);

		this.setVisible(true);

	}

	private ImageIcon redimensionarImagen(String rutaImagen, int ancho, int alto) {
		ImageIcon imagenOriginal = new ImageIcon(rutaImagen);
		Image imagen = imagenOriginal.getImage();
		Image imagenRedimensionada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		return new ImageIcon(imagenRedimensionada);
	}

	private JTextField crearCampo(String etiqueta, int y) {
		JLabel lblCampo = new JLabel(etiqueta);
		lblCampo.setBounds(10, y, 100, 20);
		JTextField campoTexto = new JTextField();
		Border bordeColor = BorderFactory.createLineBorder(Color.RED, 2); // el 2 es el grosor
		campoTexto.setBorder(bordeColor);
		campoTexto.setBounds(120, y, 200, 20);
		panel.add(lblCampo);
		panel.add(campoTexto);
		return campoTexto;
	}

	private JButton crearBoton(String etiqueta, int x, int y) {
		JButton boton = new JButton(etiqueta);
		boton.setBounds(x, y, 90, 30);
		return boton;
	}

	private void mostrarInformacionEmpleado() {
		String nombre = txtNombre.getText();
		String edad = txtEdad.getText();
		String direccion = txtDireccion.getText();
		String telefono = txtTelefono.getText();
		String puesto = txtPuesto.getText();

		String mensaje = "Nombre: " + nombre + "\nEdad: " + edad + "\nDirección: " + direccion + "\nTeléfono: "
				+ telefono + "\nPuesto: " + puesto;
		JOptionPane.showMessageDialog(this, mensaje, "Información del Empleado", JOptionPane.INFORMATION_MESSAGE);
	}

	private void limpiarCampos() {
		txtNombre.setText("");
		txtEdad.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtPuesto.setText("");
	}

	public static void main(String[] args) {
		new RegistroEmpleados();
	}
}