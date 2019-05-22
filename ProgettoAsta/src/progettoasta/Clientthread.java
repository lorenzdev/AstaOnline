
package progettoasta;

import java.net.*;
import java.io.*;
import java.util.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Clientthread extends Thread{
    
            
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    ServerAsta server;
    
    public Clientthread(Socket client,Document doc){
    
        this.client = client;
        
    }
    
    private void registrazione(String email, String pass, String citta_residenza, String indirizzo, String data_nascita, String nr_cell, String nome,String cognome){
        try{
            int idMax=0;
            boolean trovato=false;
            
               Node root = server.doc.getFirstChild();
               
               NodeList tmpUtenti = ((Element)server.doc.getFirstChild()).getElementsByTagName("utente");
               
          for(int i = 0;i < tmpUtenti.getLength();i++){

            Node utente = tmpUtenti.item(i);

            if(utente.getNodeType() == Node.ELEMENT_NODE) {
               Element el = (Element)utente;
               
              if(el.getElementsByTagName("e-mail").item(0).getTextContent().equals(email)){
                  trovato=true;
              }
               
              }
          }
          if(trovato==false){
               Element newUtente = server.doc.createElement("utente");
               
               Element newEmail = server.doc.createElement("e-mail");
               newEmail.setTextContent(pass);
               Element newPass = server.doc.createElement("pass");
               newPass.setTextContent(email);
               Element newCitta = server.doc.createElement("citta_residenza");
               newCitta.setTextContent(citta_residenza);
               Element newIndirizzo = server.doc.createElement("indirizzo");
               newIndirizzo.setTextContent(indirizzo);
               Element newDatan = server.doc.createElement("data_nascita");
               newDatan.setTextContent(data_nascita);
               Element newCell = server.doc.createElement("nr_cell");
               newCell.setTextContent(nr_cell);
               Element newNome = server.doc.createElement("nome");
               newNome.setTextContent(nome);
               Element newCognome = server.doc.createElement("cognome");
               newCognome.setTextContent(cognome);
               
               //id dell'utente
               for(int i = 0;i < tmpUtenti.getLength();i++){

            Node utente = tmpUtenti.item(i);

            if(utente.getNodeType() == Node.ELEMENT_NODE) {
               Element el = (Element)utente;
               
              if((Integer.parseInt(el.getElementsByTagName("id_utente").item(0).getTextContent()))>idMax){
                  idMax=Integer.parseInt(el.getElementsByTagName("id_utente").item(0).getTextContent());
              }
               
              }
          }
               
               Element newId=server.doc.createElement("id_utente");
               newId.setTextContent("");
               
               newUtente.appendChild(newEmail);
               newUtente.appendChild(newPass);
               // utente append id
               newUtente.appendChild(newCitta);
               newUtente.appendChild(newIndirizzo);
               newUtente.appendChild(newDatan);
               newUtente.appendChild(newCell);
               newUtente.appendChild(newNome);
               newUtente.appendChild(newCognome);
          }
               
               
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   
    
    public void run(){
       
        try{

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            String email=in.readLine();
            String pass=in.readLine();
            String citta_residenza=in.readLine();
            String indrizzo=in.readLine();
            String data_nascita=in.readLine();
            String nr_cell=in.readLine();
            String nome=in.readLine();
            String cognome=in.readLine();
            
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
