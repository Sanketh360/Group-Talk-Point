package group.chatting.application;

import java.net.*; //for server socket
import java.io.*;
import java.util.*; //for vector

public class Server implements Runnable { //implements Runnable because we are implementing multithreading to run the client parallel
    
    Socket socket;
    
    public static Vector client = new Vector(); //all the clients in vector
    
    public Server(Socket socket) {
        try {
            this.socket = socket; //assign socket to instance variable
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //for buffered reader and writer
    public void run() { //abstract method of Runnable and multithreading concept
        try {
            //server takes input from reader and broadcasts to other clients
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); //message sent by user comes through socket
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //to broadcast to other clients
            
            client.add(writer); //add client and broadcast
            
            while (true) { //infinite loop so the message can come more
                String data = reader.readLine().trim(); //read the data
                System.out.println("Received " + data);
                
                for (int i = 0; i < client.size(); i++) {
                    try {
                        BufferedWriter bw = (BufferedWriter) client.get(i); //get the message from clients and store in bw
                        bw.write(data); //write message
                        bw.write("\r\n");
                        bw.flush(); //send message
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception { //we can also use try-catch
        ServerSocket s = new ServerSocket(2003); //create server socket with a specific port
        while (true) { //don't know how many clients will be there
            Socket socket = s.accept(); //accept all the clients using server socket
            Server server = new Server(socket); //server class object
            Thread thread = new Thread(server); //we are connecting server to the client through threads
            thread.start(); //internally calls run method
        }
    }
}
