package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class POSApplication extends JFrame {

	private int totalItems = 0;
	private double totalCost = 0.00;
	private JTextArea totalItemsField;
	private JLabel totalCostField;
	private JLabel totalItemsLabel;
	private JLabel titleLabel;
	private JPanel itemsPanel;
	private JTextField quantityField;
	private DecimalFormat df = new DecimalFormat("#.00");
	private Map<String, Integer> itemQuantities = new HashMap<>();

	private Map<String, Double> products = new HashMap<String, Double>() {
		{
			put("Agua Mineral", 1.00);
			put("Café", 1.50);
			put("Coca-Cola", 1.50);
			put("Ensalada", 5.00);
			put("Fanta", 1.50);
			put("Hamburguesa", 5.00);
			put("Nachos", 3.00);
			put("Patatas Fritas", 2.00);
			put("Sandwich", 4.00);
			put("Tarta de Queso", 3.50);
			put("Té", 1.50);
			put("Vino", 6.00);
		}
	};

	public POSApplication() {
		setupMainWindow();

		setupTopPanel();

		setupItemsPanel();

		setupKeypadPanel();

		pack();

		centerWindow(this);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	private void centerWindow(JFrame frame) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - frame.getWidth()) / 2;
		int y = (screenSize.height - frame.getHeight()) / 2;
		frame.setLocation(x, y);
	}

	private void setupMainWindow() {
		setTitle("TPV EL BAR");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		getContentPane().setBackground(new Color(50, 50, 50));
		getContentPane().setLayout(new BorderLayout());
	}

	private void setupTopPanel() {
		JPanel topPanel = new JPanel(new GridBagLayout());
		topPanel.setBackground(new Color(25, 25, 112));
		GridBagConstraints gbc = new GridBagConstraints();

		titleLabel = new JLabel("TPV EL BAR", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setOpaque(true);
		titleLabel.setBackground(new Color(25, 25, 112));

		gbc.insets = new Insets(10, 0, 10, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(titleLabel, gbc);

		totalItemsField = new JTextArea(15, 25);
		totalItemsField.setEditable(false);
		totalItemsField.setFont(new Font("Consolas", Font.PLAIN, 14));
		totalItemsField.setForeground(Color.WHITE);
		totalItemsField.setBackground(new Color(25, 25, 112));
		totalItemsField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JScrollPane scrollPane = new JScrollPane(totalItemsField);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, new Color(65, 105, 225)));

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0.75;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		topPanel.add(scrollPane, gbc);

		totalItemsLabel = new JLabel("Total artículos: 0");
		totalItemsLabel.setFont(new Font("Arial", Font.BOLD, 30));
		totalItemsLabel.setForeground(new Color(255, 215, 0));
		totalItemsLabel.setOpaque(true);
		totalItemsLabel.setBackground(new Color(25, 25, 112));
		totalItemsLabel.setBorder(BorderFactory.createMatteBorder(2, 5, 2, 5, new Color(25, 25, 112)));
		totalItemsLabel.setHorizontalAlignment(SwingConstants.LEFT);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.weightx = 0.1; // Reducir el peso para que ocupe menos espacio horizontal
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.WEST; // Alinear al oeste (izquierda)
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(0, 10, 0, 0); // Ajustar los insets si es necesario para un mayor desplazamiento
		topPanel.add(totalItemsLabel, gbc);

		totalCostField = new JLabel("Total: 0,00 €");
		totalCostField.setFont(new Font("Arial", Font.BOLD, 30));
		totalCostField.setForeground(new Color(50, 205, 50));
		totalCostField.setOpaque(true);
		totalCostField.setBackground(new Color(25, 25, 112));
		totalCostField.setBorder(BorderFactory.createMatteBorder(2, 5, 2, 5, new Color(25, 25, 112)));
		totalCostField.setHorizontalAlignment(SwingConstants.RIGHT);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.weightx = 0.25;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		topPanel.add(totalCostField, gbc);

		getContentPane().add(topPanel, BorderLayout.NORTH);
	}

	private void resetSale() {
		totalItems = 0;
		totalCost = 0.00;
		itemQuantities.clear();
		totalItemsField.setText("");
		totalItemsLabel.setText("Total artículos: 0");
		updateDisplay();
	}

	private void setupItemsPanel() {
		itemsPanel = new JPanel(new GridLayout(3, 4));
		itemsPanel.setBackground(new Color(50, 50, 50));
		String[] productNames = { "Agua Mineral", "Café", "Coca-Cola", "Ensalada", "Fanta", "Hamburguesa", "Nachos",
				"Patatas Fritas", "Sandwich", "Tarta de Queso", "Té", "Vino" };

		String[] imageNames = { "agua_mineral.png", "cafe.png", "coca.png", "ensalada.png", "fanta.png",
				"hamburguesa.png", "nachos.png", "patatas.png", "sandwich.png", "tarta_queso.png", "te.png",
				"vino.png" };

		for (int i = 0; i < productNames.length; i++) {
			String productName = productNames[i];
			String imageName = "/images/" + imageNames[i];
			ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newImg);

			JButton button = new JButton(productName, icon);
			button.setVerticalTextPosition(AbstractButton.BOTTOM);
			button.setHorizontalTextPosition(AbstractButton.CENTER);
			button.setBackground(new Color(100, 149, 237));
			button.setForeground(Color.WHITE);
			button.setFocusPainted(false);
			button.setFont(new Font("SansSerif", Font.BOLD, 12));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (quantityField.getText().isEmpty()) {
						quantityField.setText("1"); // 1 será pues lógicamente la predeterminada.
					}
					addItem(productName, products.get(productName));
				}
			});
			itemsPanel.add(button);
		}
		getContentPane().add(itemsPanel, BorderLayout.CENTER);
	}

	private void setupKeypadPanel() {
		JPanel keypadPanel = new JPanel(new GridLayout(5, 3, 5, 5));
		keypadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		for (int i = 1; i <= 9; i++) {
			JButton button = createStyledButton(String.valueOf(i));
			button.addActionListener(new KeypadActionListener());
			keypadPanel.add(button);
		}

		JButton zeroButton = new JButton("0");
		zeroButton.setFont(new Font("Arial", Font.BOLD, 18));
		zeroButton.addActionListener(new KeypadActionListener());
		keypadPanel.add(zeroButton);

		JButton newSaleButton = new JButton("Nuevo");
		newSaleButton.setFont(new Font("Arial", Font.BOLD, 18));
		newSaleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				resetSale();
				totalCostField.setText("Total: 0,00 €");
			}
		});
		keypadPanel.add(newSaleButton);

		JButton emptyQuantityButton = new JButton("Vaciar cantidad");
		emptyQuantityButton.setFont(new Font("Arial", Font.BOLD, 18));
		emptyQuantityButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!quantityField.getText().isEmpty()) {
					String text = quantityField.getText();
					quantityField.setText(text.substring(0, text.length() - 1));
				}
			}
		});
		keypadPanel.add(emptyQuantityButton);

		JButton exitButton = new JButton("Salir");
		exitButton.setFont(new Font("Arial", Font.BOLD, 18));
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		keypadPanel.add(exitButton);

		quantityField = new JTextField();
		quantityField.setHorizontalAlignment(JTextField.CENTER);
		quantityField.setFont(new Font("Arial", Font.BOLD, 24));
		quantityField.setEditable(false);
		quantityField.setPreferredSize(new Dimension(200, 50));
		keypadPanel.add(quantityField, BorderLayout.NORTH);

		getContentPane().add(keypadPanel, BorderLayout.EAST);
	}

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("SansSerif", Font.BOLD, 18));
		button.setBackground(new Color(70, 130, 180));
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		return button;
	}

	private class KeypadActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			quantityField.setText(quantityField.getText() + command);
		}
	}

	private void addItem(String item, double price) {
		int quantity = 1;
		if (quantityField.getText().isEmpty() || quantityField.getText().equals("0")) {
			JOptionPane.showMessageDialog(this, "La cantidad introducida no es válida.", "Cantidad Inválida",
					JOptionPane.ERROR_MESSAGE);
			quantityField.setText("");
			return;
		}

		try {

			quantity = Integer.parseInt(quantityField.getText());

			if (quantity > 1000) {
				JOptionPane.showMessageDialog(this,
						"La cantidad introducida no es válida. Por favor, introduce un número menor o igual a 1000.",
						"Cantidad Inválida", JOptionPane.ERROR_MESSAGE);
				quantityField.setText("");
				return;
			}

			totalItems += quantity;
			totalCost += price * quantity;

			itemQuantities.put(item, itemQuantities.getOrDefault(item, 0) + quantity);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.", "Error de Número",
					JOptionPane.ERROR_MESSAGE);
			quantityField.setText("");
			return;
		}

		totalItemsLabel.setText("Total artículos: " + totalItems);
		updateDisplay();
		quantityField.setText("");
	}

	private void updateDisplay() {
		totalItemsField.setText("");
		for (Map.Entry<String, Integer> entry : itemQuantities.entrySet()) {
			String item = entry.getKey();
			int quantity = entry.getValue();
			double price = products.get(item);
			double itemTotal = price * quantity;
			totalItemsField.append(String.format("%s (%.2f €) x%d (%.2f €)\n", item, price, quantity, itemTotal));
		}
		totalCostField.setText("Total: " + df.format(totalCost) + " €");
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new POSApplication();
			}
		});
	}
}
