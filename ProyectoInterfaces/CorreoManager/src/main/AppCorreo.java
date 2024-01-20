package main;

// Importaciones necesarias
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class AppCorreo extends JFrame {

	// Componentes de la UI
	private JTextField campoNombre, campoDireccion, campoTelefono, campoCorreo;
	private JButton botonMostrar, botonLimpiar, botonSalir;
	private JRadioButton radioMovil, radioTelefonoFijo;
	private ButtonGroup grupoTipoTelefono;
	private JLabel etiquetaNombre, etiquetaDireccion, etiquetaTelefono, etiquetaCorreo;

	public AppCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300); 
		setLayout(null); 

		campoNombre = new JTextField();
		campoDireccion = new JTextField();
		campoTelefono = new JTextField();
		campoCorreo = new JTextField();
		botonMostrar = new JButton("Mostrar");
		botonLimpiar = new JButton("Limpiar");
		botonSalir = new JButton("Salir");
		radioMovil = new JRadioButton("Movil");
		radioTelefonoFijo = new JRadioButton("Telefono Fijo");
		grupoTipoTelefono = new ButtonGroup();

		etiquetaNombre = new JLabel("Nombre:");
		etiquetaDireccion = new JLabel("Direccion:");
		etiquetaTelefono = new JLabel("Telefono:");
		etiquetaCorreo = new JLabel("Correo Electronico:");

		etiquetaNombre.setBounds(10, 10, 80, 25);
		etiquetaDireccion.setBounds(10, 45, 80, 25);
		etiquetaTelefono.setBounds(10, 80, 80, 25);
		etiquetaCorreo.setBounds(10, 115, 120, 25);

		
		campoNombre.setBounds(130, 10, 200, 25);
		campoDireccion.setBounds(130, 45, 200, 25);
		campoTelefono.setBounds(130, 80, 200, 25);
		campoCorreo.setBounds(130, 115, 200, 25);
		botonMostrar.setBounds(30, 150, 100, 25);
		botonLimpiar.setBounds(150, 150, 100, 25);
		botonSalir.setBounds(270, 150, 100, 25);
		radioMovil.setBounds(130, 185, 100, 25);
		radioTelefonoFijo.setBounds(230, 185, 120, 25);

		grupoTipoTelefono.add(radioMovil);
		grupoTipoTelefono.add(radioTelefonoFijo);
		radioTelefonoFijo.setEnabled(false);
		radioMovil.setEnabled(false);

		add(etiquetaNombre);
		add(etiquetaDireccion);
		add(etiquetaTelefono);
		add(etiquetaCorreo);
		add(campoNombre);
		add(campoDireccion);
		add(campoTelefono);
		add(campoCorreo);
		add(botonMostrar);
		add(botonLimpiar);
		add(botonSalir);
		add(radioMovil);
		add(radioTelefonoFijo);

		campoCorreo.getDocument().addDocumentListener(new ValidadorEmail());

		campoTelefono.getDocument().addDocumentListener(new ValidadorTelefono());

		botonMostrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarDatosUsuario();
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

		setVisible(true);
	}

	private void mostrarDatosUsuario() {
		if (campoCorreo.getBackground().equals(Color.GREEN) && campoTelefono.getBackground().equals(Color.GREEN)) {
			String informacionUsuario = "Nombre: " + campoNombre.getText() + "\nDireccion: " + campoDireccion.getText()
					+ "\nTelefono: " + campoTelefono.getText() + "\nCorreo Electronico: " + campoCorreo.getText();
			JOptionPane.showMessageDialog(this, informacionUsuario);
		} else {
			JOptionPane.showMessageDialog(this,
					"No puede mostrar datos incorrectos. Por favor, revise el correo electrónico y el teléfono.",
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarCampos() {
		campoNombre.setText("");
		campoDireccion.setText("");
		campoTelefono.setText("");
		campoCorreo.setText("");
		campoTelefono.setBackground(Color.WHITE);
		campoCorreo.setBackground(Color.WHITE);
		grupoTipoTelefono.clearSelection();
	}

	private class ValidadorEmail implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			validarEmail();
		}

		public void removeUpdate(DocumentEvent e) {
			validarEmail();
		}

		public void insertUpdate(DocumentEvent e) {
			validarEmail();
		}

		private void validarEmail() {
			String texto = campoCorreo.getText();
			if (texto.contains("@") && texto.contains(".")) {
				campoCorreo.setBackground(Color.GREEN);
			} else {
				campoCorreo.setBackground(Color.RED);
			}
		}
	}

	private class ValidadorTelefono implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			validarTelefono();
		}

		public void removeUpdate(DocumentEvent e) {
			validarTelefono();
		}

		public void insertUpdate(DocumentEvent e) {
			validarTelefono();
		}

		private void validarTelefono() {
			String texto = campoTelefono.getText();
			if ((texto.startsWith("8") || texto.startsWith("9")) && texto.length() == 9) {
				campoTelefono.setBackground(Color.GREEN);
				radioTelefonoFijo.setSelected(true);
			} else if (texto.startsWith("6") && texto.length() == 9) {
				campoTelefono.setBackground(Color.GREEN);
				radioMovil.setSelected(true);

			} else {
				campoTelefono.setBackground(Color.RED);
				grupoTipoTelefono.clearSelection();
			
			}
		}
	}

	public static void main(String[] args) {
		new AppCorreo();

	}
}
