import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener {
    JTextField textField;
    double num1, num2, result;
    char operator;

    public calculator() {
        setTitle("Compact Calculator");
        setSize(300, 400); // Reasonable window size
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Text field
        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 22));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.GREEN);
        add(textField, BorderLayout.NORTH);

        // Panel for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5)); // 5px spacing

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));

            // Color coding
            if (text.matches("[0-9]")) {
                button.setBackground(Color.LIGHT_GRAY);
            } else if (text.equals("C")) {
                button.setBackground(Color.RED);
                button.setForeground(Color.WHITE);
            } else if (text.equals("=")) {
                button.setBackground(Color.GREEN);
                button.setForeground(Color.WHITE);
            } else {
                button.setBackground(Color.ORANGE);
                button.setForeground(Color.WHITE);
            }

            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.GRAY);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9]")) {
            textField.setText(textField.getText() + command);
        } else if (command.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
        } else if (command.equals("=")) {
            try {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+': result = num1 + num2; break;
                    case '-': result = num1 - num2; break;
                    case '*': result = num1 * num2; break;
                    case '/': 
                        if (num2 != 0) result = num1 / num2;
                        else {
                            textField.setText("Error");
                            return;
                        }
                        break;
                }
                textField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        } else {
            try {
                num1 = Double.parseDouble(textField.getText());
                operator = command.charAt(0);
                textField.setText("");
            } catch (NumberFormatException ex) {
                textField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}
