/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class ClientControl {
    private ClientView view;
    private String serverHost = "localhost";
    private int serverPort = 8888;
    
    public ClientControl(ClientView view) {
        this.view = view;
        this.view.addLoginListener(new LoginListener());
    }
    
    class LoginListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            try {
                User user = view.getUser();
                Socket mySocket = new Socket(serverHost, serverPort);
                ObjectOutputStream oos = new ObjectOutputStream(mySocket.getOutputStream());
                oos.writeObject(user);
                
                ObjectInputStream ois = new ObjectInputStream(mySocket.getInputStream());
                Object o = ois.readObject();
                if(o instanceof String) {
                    String result = (String)o;
                    if(result.equals("ok"))
                        view.showMessage("Login Success");
                    else view.showMessage("Failed");
                }
                mySocket.close();
            } catch (IOException ex) {
                view.showMessage(ex.getStackTrace().toString());
            } catch (ClassNotFoundException ex) {
                view.showMessage(ex.getStackTrace().toString());
            }
            
        }
            
    }
}
