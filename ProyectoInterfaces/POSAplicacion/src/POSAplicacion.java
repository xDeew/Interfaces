import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class POSAplicacion extends JFrame {

    private int totalItems = 0;
    private double totalCost = 0.00;
    private JTextField totalItemsField;
    private JTextField totalCostField;
    private JPanel itemsPanel;
    private JTextField quantityField;
    private DecimalFormat df = new DecimalFormat("#0.00");

    // Supongamos que tenemos una lista de productos con su precio.
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

    // Constructor
    public POSAplicacion() {
        // Configuración inicial de la ventana
        setupMainWindow();

        // Panel superior para artículos totales y costo
        setupTopPanel();

        // Panel central para botones de artículos
        //   setupItemsPanel();

        // Panel derecho para teclado numérico y botones de control
        setupKeypadPanel();

        // Empaquetar y mostrar la ventana
        pack();
        centerWindow(this);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new POSAplicacion();
            }
        });
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
        getContentPane().setLayout(new BorderLayout());
    }

    private void setupTopPanel() {
        JPanel topPanel = new JPanel(new GridLayout(1, 2));
        totalItemsField = new JTextField("Artículos Totales: 0");
        totalItemsField.setEditable(false);
        totalCostField = new JTextField("Total: 0.00 €");
        totalCostField.setEditable(false);
        topPanel.add(totalItemsField);
        topPanel.add(totalCostField);
        getContentPane().add(topPanel, BorderLayout.NORTH);
    }

    /* private void setupItemsPanel() {
         itemsPanel = new JPanel(new GridLayout(3, 4)); // Ajustar según la cantidad de ítems

         // Lista de nombres de productos y nombres de archivo de imágenes



         for (int i = 0; i < productNames.length; i++) {
             String productName = productNames[i];
             String imageName = "/images/" + imageNames[i];
             ImageIcon icon = new ImageIcon(getClass().getResource(imageName));
             // Redimensionar la imagen para que encaje en el botón
             Image img = icon.getImage();
             Image newImg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
             icon = new ImageIcon(newImg);

             JButton button = new JButton(productName, icon);
             button.setVerticalTextPosition(AbstractButton.BOTTOM);
             button.setHorizontalTextPosition(AbstractButton.CENTER);
             button.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     // Aquí puedes usar productName directamente si el nombre del botón es el mismo que el del producto
                     addItem(productName, products.get(productName));
                 }
             });
             itemsPanel.add(button);
         }
         getContentPane().add(itemsPanel, BorderLayout.CENTER);
     }
 */
    private void setupKeypadPanel() {
        JPanel keypadPanel = new JPanel(new GridLayout(4, 3, 5, 5)); // Añadir un espacio entre los botones
        keypadPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Añadir algo de espacio alrededor del
        // panel

        // Agregar botones numéricos del 0 al 9
        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(String.valueOf(i));
            button.setFont(new Font("Arial", Font.BOLD, 24)); // Ajustar la fuente y el tamaño para que se parezca más a
            // la imagen
            button.addActionListener(new KeypadActionListener());
            keypadPanel.add(button);
        }

        // Agregar botón 0, 'Nuevo' y 'Salir'
        JButton zeroButton = new JButton("0");
        zeroButton.setFont(new Font("Arial", Font.BOLD, 24));
        zeroButton.addActionListener(new KeypadActionListener());
        keypadPanel.add(zeroButton);

        JButton newSaleButton = new JButton("Nuevo");
        newSaleButton.setFont(new Font("Arial", Font.BOLD, 24));
        newSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetSale();
            }
        });
        keypadPanel.add(newSaleButton);

        JButton exitButton = new JButton("Salir");
        exitButton.setFont(new Font("Arial", Font.BOLD,

                24));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        keypadPanel.add(exitButton);

        // Campo de texto para la cantidad sobre los botones numéricos
        quantityField = new JTextField();
        quantityField.setHorizontalAlignment(JTextField.CENTER);
        quantityField.setFont(new Font("Arial", Font.BOLD, 24));
        quantityField.setEditable(false);
        quantityField.setPreferredSize(new Dimension(200, 50)); // Ajustar el tamaño según sea necesario

        // Añadir el campo de texto para la cantidad al principio del panel del teclado
        keypadPanel.add(quantityField, BorderLayout.NORTH);

        getContentPane().add(keypadPanel, BorderLayout.EAST);
    }

    private void addItem(String item, double price) {
        int quantity = quantityField.getText().isEmpty() ? 1 : Integer.parseInt(quantityField.getText());
        totalItems += quantity;
        totalCost += price * quantity;
        updateDisplay();
        quantityField.setText(""); // Reset the quantity field after adding the item
    }

    private void updateDisplay() {
        totalItemsField.setText("Artículos Totales: " + totalItems);
        totalCostField.setText("Total: " + df.format(totalCost) + " €");
    }

    private void resetSale() {
        totalItems = 0;
        totalCost = 0.00;
        updateDisplay();
    }

    private class KeypadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            quantityField.setText(quantityField.getText() + command);
        }
    }
}