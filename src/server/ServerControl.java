/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bui
 */
public class ServerControl {
    private ServerView view;
    private Connection con;
    private ServerSocket myServer;
    private Socket clientSocket;
    private int serverPort = 8888;
    
    public ServerControl(ServerView view) {
        this.view = view;
        openServer(serverPort);
        view.showMessage("server is running");
        
        while(true){
            listenning();
        }
    }
    
    private void openServer(int portNumber){
        try {
            myServer = new ServerSocket(portNumber);
        } catch (IOException ex) {
            view.showMessage(ex.toString());
        }
    }
    
    private void listenning(){
        try {
            clientSocket = new Socket();
            clientSocket = myServer.accept();
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            
            Object o = ois.readObject();
            if(o instanceof User){
                User user = (User)o;
                if(checkUser(user)){
                    oos.writeObject("ok");
                }
                else
                    oos.writeObject("false");
            }
                
        } catch (IOException ex) {
            view.showMessage(ex.toString());
        } catch (ClassNotFoundException ex) {
            view.showMessage(ex.toString());
        }
    }
    
    private boolean checkUser(User user){
        if(user.getUserName().equals("thang") && user.getPassWord().equals("thang123"))
            return true;
        return false;
    }
}
