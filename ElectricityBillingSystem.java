import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class ElectricityBillingSystem extends JFrame implements ActionListener {
    // Components
    JLabel idLabel, cnameLabel, unitsLabel, totalLabel;
    JTextField idField, cnameField, unitsField;
    JButton calculateButton, printButton, clearButton;
    JTextArea printArea;
    double totalbill = 0;
    

    public ElectricityBillingSystem() {
        // Frame setup
        setTitle("Electricity Billing System");
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // UI components initialization
        idLabel = new JLabel("Electricity Billing System");
        idLabel.setBounds(200, 10, 200, 20);
        add(idLabel);

        idLabel = new JLabel("Customer ID:");
        idLabel.setBounds(125, 50, 100, 20);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(240, 50, 200, 20);
        add(idField);

        cnameLabel = new JLabel("Customer Name:");
        cnameLabel.setBounds(125, 80, 100, 20);
        add(cnameLabel);

        cnameField = new JTextField();
        cnameField.setBounds(240, 80, 200, 20);
        add(cnameField);

        unitsLabel = new JLabel("Units:");
        unitsLabel.setBounds(125, 110, 100, 20);
        add(unitsLabel);

        unitsField = new JTextField();
        unitsField.setBounds(240, 110, 200, 20);
        add(unitsField);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(120, 150, 100, 30);
        calculateButton.addActionListener(this);
        add(calculateButton);

        printButton = new JButton("Print");
        printButton.setBounds(235, 150, 100, 30);
        printButton.addActionListener(this);
        add(printButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(350, 150, 100, 30);
        clearButton.addActionListener(this);
        add(clearButton);

        totalLabel = new JLabel();
        totalLabel.setBounds(170, 200, 350, 20);
        add(totalLabel);

        printArea = new JTextArea();
        printArea.setBounds(120, 190, 330, 200);
        printArea.setEditable(false);
        add(printArea);

        // Show frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {

            calculate();
        } else if (e.getSource() == printButton) {
            print();
        } else if (e.getSource() == clearButton) {
            clear();
        }
    }

    public void calculate() {
        String cid = idField.getText();
        String cname = cnameField.getText();
        String unitsText = unitsField.getText();

        try {
            int conu = Integer.parseInt(unitsText);

            // Validate customer ID and units
            if (conu < 0) {
                JOptionPane.showMessageDialog(this, "Invalid units. Please enter a positive integer value.");
                return;
            }

            double chg;

            if (conu < 500) {
                chg = 1.00;
            } else if (conu < 600) {
                chg = 1.80;
            } else if (conu < 800) {
                chg = 2.80;
            } else {
                chg = 3.00;
            }

            double gramt = conu * chg;
            totalLabel.setText("Customer ID: " + cid + ", Total Bill: $" + gramt);
            totalbill = gramt;
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid units. Please enter an integer value.");
        }
    }

    public void print() {
        String billid = idField.getText();
        String customer = cnameField.getText();
        String conuText = unitsField.getText();

        if (!billid.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Invalid customer id. Please enter only integer values.");
            return;
        }

        // Validate customer name
        if (!customer.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Invalid customer name. Please enter only alphabetic characters.");
            return;
        }

        try {
            int conu = Integer.parseInt(conuText);

            String printContent = "Electricity Billing\n"
                                +"\n"
                                +"Bill Number: " + generateBillNumber() + "\n"
                                + "Customer id: " + billid + "\n"
                                + "Customer Name: " + customer + "\n"
                                + "Unit: " + conu + "\n"
                                + "Total: " +totalbill+ "\n"
                                + "Thank you. Come Again\n";
            printArea.setText(printContent);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid units. Please enter an integer value.");
        }
    }

    public void clear() {
        idField.setText("");
        cnameField.setText("");
        unitsField.setText("");
        totalLabel.setText("");
        printArea.setText("");
    }
      public String generateBillNumber() {
        Random rand = new Random();
        int billNumber = rand.nextInt(9000) + 1000; // Generates a random 4-digit number
        return String.valueOf(billNumber);
    }

    public static void main(String[] args) {
        new ElectricityBillingSystem();
    }
}
