/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Bui
 */
public class ClientView extends JFrame implements ActionListener {
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    
    public ClientView() {
        super("TCP Login View");
        
        txtUserName = new JTextField(15);
        txtPassword = new JPasswordField(15);
        txtPassword.setEchoChar('*');
        
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(new JLabel("Username:"));
        content.add(txtUserName);
        content.add(new JLabel("Password"));
        content.add(txtPassword);
        content.add(btnLogin = new JButton("Login"));
        
        this.setContentPane(content);
        this.pack();
        
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    public User getUser() {
        User model = new User(txtUserName.getText(), txtPassword.getText());
        return model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    public void addLoginListener(ActionListener log) {
        btnLogin.addActionListener(log);
    }
}
