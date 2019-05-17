
package progettoasta;

import java.net.*;
import java.io.*;
import java.util.*;


public class clientthread extends Thread{
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    ArrayList<ArrayList<String>> forno;
    
    
    public clientthread(Socket client, ArrayList<ArrayList<String>> forno){
        this.client = client;
        this.forno = forno;
    }

   
    
    public void run(){
       
        try{

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            boolean bol=false;
            String prodotto=in.readLine();
            for(int i=0; i<forno.size(); i++)
            {
            ArrayList<String> prod = forno.get(i);
            if(prod.get(0).equals(prodotto)){
                bol=true;
                out.println("prezzo: "+prod.get(1));
            }
            }
            if(bol==false){
                out.println("prodotto non esistente");
            }
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
