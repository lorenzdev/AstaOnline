
package progettoasta;

import java.net.*;
import java.io.*;
import java.util.*;
import org.w3c.dom.Document;


public class Clientthread extends Thread{
    
            
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    Document doc=null;
    
    public Clientthread(Socket client,Document doc){
    
        this.client = client;
        this.doc = doc;
    }

   
    
    public void run(){
       
        try{

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
           
            String email=in.readLine();
            
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
