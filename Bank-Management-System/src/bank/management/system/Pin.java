package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pin extends JFrame implements ActionListener {
    JButton changeButton, backButton;
    JPasswordField newPinField, reEnterPinField;
    String currentPin;

    Pin(String pin) {
        this.currentPin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/new.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel backgroundLabel = new JLabel(i3);
        backgroundLabel.setBounds(0, 0, 1550, 830);
        add(backgroundLabel);

        JLabel changePinLabel = new JLabel("CHANGE YOUR ATM PIN");
        changePinLabel.setForeground(Color.WHITE);
        changePinLabel.setFont(new Font("System", Font.BOLD, 20));
        changePinLabel.setBounds(430, 150, 400, 35);
        backgroundLabel.add(changePinLabel);

        JLabel newPinLabel = new JLabel("New PIN : ");
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setFont(new Font("System", Font.BOLD, 16));
        newPinLabel.setBounds(430, 220, 150, 35);
        backgroundLabel.add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setBackground(new Color(65, 125, 128));
        newPinField.setForeground(Color.WHITE);
        newPinField.setBounds(600, 220, 180, 25);
        newPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        backgroundLabel.add(newPinField);

        JLabel reEnterPinLabel = new JLabel("Re-Enter New PIN : ");
        reEnterPinLabel.setForeground(Color.WHITE);
        reEnterPinLabel.setFont(new Font("System", Font.BOLD, 16));
        reEnterPinLabel.setBounds(430, 250, 400, 35);
        backgroundLabel.add(reEnterPinLabel);

        reEnterPinField = new JPasswordField();
        reEnterPinField.setBackground(new Color(65, 125, 128));
        reEnterPinField.setForeground(Color.WHITE);
        reEnterPinField.setBounds(600, 255, 180, 25);
        reEnterPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        backgroundLabel.add(reEnterPinField);

        changeButton = new JButton("CHANGE");
        changeButton.setBounds(500, 362, 150, 35);
        changeButton.setBackground(new Color(65, 125, 128));
        changeButton.setForeground(Color.WHITE);
        changeButton.addActionListener(this);
        backgroundLabel.add(changeButton);

        backButton = new JButton("BACK");
        backButton.setBounds(500, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backgroundLabel.add(backButton);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String newPin = new String(newPinField.getPassword());
            String reEnteredPin = new String(reEnterPinField.getPassword());

            if (!newPin.equals(reEnteredPin)) {
                JOptionPane.showMessageDialog(null, "Entered PIN do not Match");
                return;
            }

            if (newPin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter New PIN");
                return;
            }

            if (e.getSource() == changeButton) {
                Connn conn = new Connn();
                String updateBankQuery = "update bank set pin = '" + newPin + "' where pin = '" + currentPin + "'";
                String updateLoginQuery = "update login set pin = '" + newPin + "' where pin = '" + currentPin + "'";
                String updateSignupThreeQuery = "update signupthree set pin = '" + newPin + "' where pin = '"
                        + currentPin + "'";

                conn.statement.executeUpdate(updateBankQuery);
                conn.statement.executeUpdate(updateLoginQuery);
                conn.statement.executeUpdate(updateSignupThreeQuery);

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully");
                setVisible(false);
                new main_Class(newPin);

            } else if (e.getSource() == backButton) {
                new main_Class(currentPin);
                setVisible(false);
            }

        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
