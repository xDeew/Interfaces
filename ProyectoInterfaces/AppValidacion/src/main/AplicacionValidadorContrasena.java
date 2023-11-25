package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class AplicacionValidadorContrasena extends JFrame {

	private JTextField campoUsuario;
	private JPasswordField campoContrasena;
	private JLabel etiquetaCaracterEspecial, etiquetaNumero, etiquetaMayuscula;

	public AplicacionValidadorContrasena() {
		crearInterfaz();
	}

	private void crearInterfaz() {
		setTitle("Validador de Contrase�a");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null); 

		JLabel etiquetaUsuario = new JLabel("Usuario:");
		etiquetaUsuario.setBounds(10, 10, 80, 25);
		add(etiquetaUsuario);

		campoUsuario = new JTextField(20);
		campoUsuario.setBounds(100, 10, 165, 25);
		add(campoUsuario);

		JLabel etiquetaContrasena = new JLabel("Contrase�a:");
		etiquetaContrasena.setBounds(10, 40, 80, 25);
		add(etiquetaContrasena);

		campoContrasena = new JPasswordField(20); // para que la contra aparezca ps con puntos
		campoContrasena.setBounds(100, 40, 165, 25);
		add(campoContrasena);

		etiquetaCaracterEspecial = new JLabel("Debe contener al menos un carácter especial.");
		etiquetaCaracterEspecial.setBounds(10, 70, 300, 25);
		etiquetaCaracterEspecial.setForeground(Color.RED);
		add(etiquetaCaracterEspecial);

		etiquetaNumero = new JLabel("Debe contener al menos un número.");
		etiquetaNumero.setBounds(10, 100, 300, 25);
		etiquetaNumero.setForeground(Color.RED);
		add(etiquetaNumero);

		etiquetaMayuscula = new JLabel("Debe contener al menos una letra en mayúscula.");
		etiquetaMayuscula.setBounds(10, 130, 300, 25);
		etiquetaMayuscula.setForeground(Color.RED);
		add(etiquetaMayuscula);

		JButton botonMostrar = new JButton("Mostrar");
		botonMostrar.setBounds(10, 160, 80, 25);
		add(botonMostrar);

		JButton botonLimpiar = new JButton("Limpiar");
		botonLimpiar.setBounds(100, 160, 80, 25);
		add(botonLimpiar);

		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(190, 160, 80, 25);
		add(botonSalir);

		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDetalles();
			}
		});

		botonLimpiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});

		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		campoContrasena.getDocument().addDocumentListener(new EscuchaValidacionContrasena());

		setSize(350, 250);
		setLocationRelativeTo(null);
	}

	private class EscuchaValidacionContrasena implements DocumentListener {
		private void validarContrasena() {
			String contrasena = new String(campoContrasena.getPassword());

			if (contrasena.matches(".*\\d.*")) {
				etiquetaNumero.setForeground(Color.GREEN);
			} else {
				etiquetaNumero.setForeground(Color.RED);
			}

			if (contrasena.matches(".*[A-Z].*")) {
				etiquetaMayuscula.setForeground(Color.GREEN);
			} else {
				etiquetaMayuscula.setForeground(Color.RED);
			}

			if (contrasena.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*")) {
				etiquetaCaracterEspecial.setForeground(Color.GREEN);
			} else {
				etiquetaCaracterEspecial.setForeground(Color.RED);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			validarContrasena();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			validarContrasena();
		}

		@Override
		public void changedUpdate(DocumentEvent e) {
		}
	}

	private void mostrarDetalles() {
		String informacionUsuario = "Usuario: " + campoUsuario.getText() + "\nContrase�a: "
				+ new String(campoContrasena.getPassword());
		JOptionPane.showMessageDialog(this, informacionUsuario);
	}

	private void limpiarCampos() {
		campoUsuario.setText("");
		campoContrasena.setText("");
		etiquetaCaracterEspecial.setForeground(Color.RED);
		etiquetaNumero.setForeground(Color.RED);
		etiquetaMayuscula.setForeground(Color.RED);
	}

	public static void main(String[] args) {
		AplicacionValidadorContrasena aplicacion = new AplicacionValidadorContrasena();
		aplicacion.setVisible(true);
	}
}
