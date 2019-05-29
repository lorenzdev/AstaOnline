
package progettoasta;

import java.net.*;
import java.io.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static progettoasta.ServerAsta.doc;



public class Clientthread extends Thread{
    
            
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    ServerAsta server;
    String reRegistrazione="";
    String reLogin="";
    public Clientthread(Socket client){
    
        this.client = client;
        
    }
    
      private void registrazioneAsta(){
          try{
              String tipologia=in.readLine();
              String risposta="oggetti: ";
               Node root = server.doc.getFirstChild();
                
            NodeList nodeListOggetti = ((Element)root).getElementsByTagName("oggetti");
            NodeList oggetti=((Element)nodeListOggetti.item(0)).getElementsByTagName("oggetto");
              
            if(tipologia.equals("*")){
                for(int i=0; i<oggetti.getLength(); i++){
                    Element el =(Element)oggetti.item(i);
                    risposta=risposta+" id oggetto: "+el.getElementsByTagName("id_oggetto").item(0).getTextContent()+
                            " tipologia: "+el.getElementsByTagName("tipologia").item(0).getTextContent()+
                            " prezzo: "+el.getElementsByTagName("prezzo").item(0).getTextContent()+
                            " nome: "+el.getElementsByTagName("nome").item(0).getTextContent()+
                            " data: "+el.getElementsByTagName("data").item(0).getTextContent()+
                            " email autore: "+el.getElementsByTagName("e-mail_autore").item(0).getTextContent();
                }
                out.println(risposta);
            }else{
                for(int i=0; i<oggetti.getLength(); i++){
                   
                    Element el =(Element) oggetti.item(i);
                    if(el.getElementsByTagName("tipologia").item(0).getTextContent().equals(tipologia)){
                    risposta=risposta+"\n id oggetto: "+el.getElementsByTagName("id_oggetto").item(0).getTextContent()+
                            "\n tipologia: "+el.getElementsByTagName("tipologia").item(0).getTextContent()+
                            "\n prezzo: "+el.getElementsByTagName("prezzo").item(0).getTextContent()+
                            "\n nome: "+el.getElementsByTagName("nome").item(0).getTextContent()+
                            "\n data: "+el.getElementsByTagName("data").item(0).getTextContent()+
                            "\n email autore: "+el.getElementsByTagName("e-mail_autore").item(0).getTextContent();
                    }
                }
                out.println(risposta);
            }
            
          }catch(Exception e){
            e.printStackTrace();
        }
      }
    
    private void login(){
        try{

            String email=in.readLine();
            String pass=in.readLine();
            boolean emailTrovata=false;
            boolean passTrovata=false;
            
            Node root = server.doc.getFirstChild();
               
            NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
            NodeList utenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
            
            for(int i=0;i<utenti.getLength();i++){
                Element utente=(Element)utenti.item(i);
                
                if(utente.getElementsByTagName("e-mail").item(0).getTextContent().equals(email)){
                    emailTrovata=true;
                    
                    if(utente.getElementsByTagName("pass").item(0).getTextContent().equals(pass)){
                    passTrovata=true;
                }
                }
                if(passTrovata==true && emailTrovata==true){
                    
                    out.println("true");
                    break;
                }
                out.println("false");
                
            }
           reLogin=in.readLine();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    
    }
  
    
    private void registrazione(){
        try{
            
            String email=in.readLine();
            String pass=in.readLine();
            String citta_residenza=in.readLine();
            String indirizzo=in.readLine();
            String data_nascita=in.readLine();
            String nr_cell=in.readLine();
            String nome=in.readLine();
            String cognome=in.readLine();
            
            int idMax=0;
            boolean trovato=false;
            
               Node root = server.doc.getFirstChild();
               
              NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
              Node nodeUtenti=nodeListUtenti.item(0);
              NodeList utenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
              
            
          for(int i = 0;i < utenti.getLength();i++){

            Node utente = utenti.item(i);
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
               Element newUpdate = server.doc.createElement("update");
               newUpdate.setTextContent("up");
               
               //id dell'utente
               for(int i = 0;i < utenti.getLength();i++){

            Node utente = utenti.item(i);

            if(utente.getNodeType() == Node.ELEMENT_NODE) {
               Element el = (Element)utente;
               if(!el.getElementsByTagName("id_utente").item(0).getTextContent().isEmpty())
               {
                   
                 if((Integer.parseInt(el.getElementsByTagName("id_utente").item(0).getTextContent()))>idMax){
                  idMax=Integer.parseInt(el.getElementsByTagName("id_utente").item(0).getTextContent());
              }       
               }
              
               
              }
          }

               Element newId=server.doc.createElement("id_utente");
               newId.setTextContent(Integer.toString(idMax+1));
               
               newUtente.appendChild(newEmail);
               newUtente.appendChild(newPass);
               newUtente.appendChild(newId);
               newUtente.appendChild(newCitta);
               newUtente.appendChild(newIndirizzo);
               newUtente.appendChild(newDatan);
               newUtente.appendChild(newCell);
               newUtente.appendChild(newNome);
               newUtente.appendChild(newCognome);
               newUtente.appendChild(newUpdate);
               
                nodeUtenti.appendChild(newUtente);
               out.println("true");
               
            //stampa xml per vedere aggiornametni
               DOMSource domSource = new DOMSource(doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       System.out.println(writer.toString());
            
          }else{
              out.println("false");
              
          }
               
           reRegistrazione=in.readLine();
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   
    
    public void run(){
       
        try{

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            
            String scelta=in.readLine();
            
            if(scelta.equals("si")){
                
                //il client si registra
                while(!reRegistrazione.equals("finito")){
                registrazione();
                }
            
            }else{
                while(!reLogin.equals("finito")){
                login();
                }
                registrazioneAsta();
            }
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
