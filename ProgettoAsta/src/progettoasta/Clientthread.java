
package progettoasta;

import java.net.*;
import java.io.*;
import java.sql.PreparedStatement;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static progettoasta.ServerAsta.connection;
import static progettoasta.ServerAsta.doc;



public class Clientthread extends Thread{
    
            
    
    Socket client;
    BufferedReader in;
    PrintWriter out;
    ServerAsta server;
    String reRegistrazione="";
    String reLogin="";
    String reRegAsta="";
    String emailUtente="";
    String controllo="si";
    
    public Clientthread(Socket client){
    
        this.client = client;
        
    }
    
      private void visualizzaAsta(){
          try{
              String tipologia=in.readLine();
              String risposta="oggetti: ";
               Node root = server.doc.getFirstChild();
                
            NodeList nodeListOggetti = ((Element)root).getElementsByTagName("oggetti");
            NodeList oggetti=((Element)nodeListOggetti.item(0)).getElementsByTagName("oggetto");
              
            if(tipologia.equals("tutto")){
                for(int i=0; i<oggetti.getLength(); i++){
                    Element el =(Element)oggetti.item(i);
                    risposta=risposta+" id oggetto: "+el.getAttribute("id")+
                            " tipologia: "+el.getElementsByTagName("tipologia").item(0).getTextContent()+
                            " prezzo: "+el.getElementsByTagName("prezzo").item(0).getTextContent()+
                            " nome: "+el.getElementsByTagName("nome").item(0).getTextContent()+
                            " data: "+el.getElementsByTagName("data").item(0).getTextContent()+
                            " email autore: "+el.getElementsByTagName("email_autore").item(0).getTextContent();
                }
                out.println(risposta);
            }else{
                for(int i=0; i<oggetti.getLength(); i++){
                   
                    Element el =(Element) oggetti.item(i);
                    if(el.getElementsByTagName("tipologia").item(0).getTextContent().equals(tipologia)){
                    risposta=risposta+" id oggetto: "+el.getAttribute("id")+
                            " tipologia: "+el.getElementsByTagName("tipologia").item(0).getTextContent()+
                            " prezzo: "+el.getElementsByTagName("prezzo").item(0).getTextContent()+
                            " nome: "+el.getElementsByTagName("nome").item(0).getTextContent()+
                            " data: "+el.getElementsByTagName("data").item(0).getTextContent()+
                            " email autore: "+el.getElementsByTagName("email_autore").item(0).getTextContent();
                    }
                }
                out.println(risposta);
            }
            
          }catch(Exception e){
            e.printStackTrace();
        }
      }
      
     private void registrazioneAsta(){
        try{
            String scelta=in.readLine();
            String nominativo;
        if(scelta.equals("si")){
          nominativo=in.readLine();
           Node root = server.doc.getFirstChild();
              
            NodeList nodeListOggetti = ((Element)root).getElementsByTagName("oggetti");
            NodeList nodeListPartecipanti=((Element)root).getElementsByTagName("partecipanti");
            Node nodePartecipanti=nodeListPartecipanti.item(0);
            NodeList oggetti=((Element)nodeListOggetti.item(0)).getElementsByTagName("oggetto");
            boolean riprovo=true;
            for(int i =0 ;i <oggetti.getLength();i++){
                Element el=(Element)oggetti.item(i);
                if(el.getAttribute("id").equals(nominativo)){
                    Element newUtente=server.doc.createElement("utente");
                    newUtente.setAttribute("id", trovaID());
                    Element newUpdate=server.doc.createElement("update");
                    newUpdate.setTextContent("up");
                    newUtente.appendChild(newUpdate);
                    nodePartecipanti.appendChild(newUtente);
                   out.println("true");
                   reRegAsta="finito";
                   riprovo=false;
                   break;
                }
            }
            if(riprovo==true){
                reRegAsta="riprova";
            out.println("false");
            }
        }
        
        
        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
     }
     
     private String trovaID() throws IOException{
         try{
               Node root = server.doc.getFirstChild();
               
            NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
            NodeList utenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
            
            for(int i=0;i<utenti.getLength();i++){
                Element utente=(Element)utenti.item(i);
                if(utente.getElementsByTagName("email").item(0).getTextContent().equals(emailUtente)){
                    return (utente.getElementsByTagName("id_utente").item(0).getTextContent());
                }
            }
          
                    
                
         } catch(Exception e){
            e.printStackTrace();
        }
        
        throw new IOException("errore utente non loggato");
         
     }
     
    private void login(){
        try{
            boolean loginTrovato=false;
            String email=in.readLine();
            String pass=in.readLine();
            boolean emailTrovata=false;
            boolean passTrovata=false;
            
            Node root = server.doc.getFirstChild();
               
            NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
            NodeList utenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
            
            for(int i=0;i<utenti.getLength();i++){
                Element utente=(Element)utenti.item(i);
                
                if(utente.getElementsByTagName("email").item(0).getTextContent().equals(email)){
                    emailTrovata=true;
                    
                    if(utente.getElementsByTagName("pass").item(0).getTextContent().equals(pass)){
                    passTrovata=true;
                }
                }
                if(passTrovata==true && emailTrovata==true){
                    reLogin="finito";
                    loginTrovato=true;
                    emailUtente=email;
                    out.println("true");
                    break;
                }else{
                    loginTrovato=false;}
            }
            
           if(loginTrovato==false){
               
               reLogin="riprova";
               out.println("false");
           }
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
               
              if(el.getElementsByTagName("email").item(0).getTextContent().equals(email)){
                  trovato=true;
              }
               
              }
          }
          if(trovato==false){
               Element newUtente = server.doc.createElement("utente");
               
               Element newEmail = server.doc.createElement("email");
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
                
                reRegistrazione="finito";
               out.println("true");
               
            //stampa xml per vedere aggiornametni
            System.out.println("\n  NUOVO UTENTE REGISTRATO");
               DOMSource domSource = new DOMSource(doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       System.out.println(writer.toString());
            
          }else{
              reRegistrazione="riprova";
              out.println("false");
              
          }
               
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }

   
    
    public void run(){
       
        try{

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            

              
            while(controllo.equals("si")){
                reLogin="";
                reRegAsta="";
                reRegistrazione="";
                   String scelta=in.readLine();
            int menu=Integer.parseInt(scelta);
            switch(menu){
            
                case 1:{
                    
                while(!reRegistrazione.equals("finito")){
                registrazione();
                }
                break;
                }
               
           case 2:{
               
               while(!reLogin.equals("finito")){
                login();
                }
               break;
           }
           
           case 3:{
               if(!emailUtente.equals(""))
               {out.println("loggato");
                visualizzaAsta();
                while(!reRegAsta.equals("finito")){
                registrazioneAsta();
                }
               }else{
                   out.println("nonloggato");
               }
               break;
           }
            
           case 4:{
            updateDB();
            in.close();
            out.close();
            client.close();
            controllo="chiudi";
            break;
           }
           
           default: break;
                
            }
            }
            in.close();
            out.close();
            client.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
      private void updateDB(){
     try{
    
    Node root = server.doc.getFirstChild();
               
    NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
    NodeList XMLutenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
  
    NodeList nodeListOgg=((Element)root).getElementsByTagName("oggetti");
    NodeList nodeListOgetti = ((Element)nodeListOgg.item(0)).getElementsByTagName("oggetto");
    
   
    
    for(int i=0;i<XMLutenti.getLength();i++)
    {
        Element el;
            el = (Element)XMLutenti.item(i);
        if(el.getElementsByTagName("update").item(0).getTextContent().equals("up"))
        {
             String query = "INSERT INTO utente (email,password,id_utente,citta_residenza,indirizzo,data_nascita,nr_cell,nome,cognome)VALUES"+ "('"+el.getElementsByTagName("email").item(0).getTextContent()+"','"+el.getElementsByTagName("pass").item(0).getTextContent()+"','"+el.getElementsByTagName("id_utente").item(0).getTextContent()+"','"+el.getElementsByTagName("citta_residenza").item(0).getTextContent()+"','"+el.getElementsByTagName("indirizzo").item(0).getTextContent()+"','"+el.getElementsByTagName("data_nascita").item(0).getTextContent()+"','"+el.getElementsByTagName("nr_cell").item(0).getTextContent()+"','"+el.getElementsByTagName("nome").item(0).getTextContent()+"','"+el.getElementsByTagName("cognome").item(0).getTextContent()+"')";
             PreparedStatement ins = server.connection.prepareStatement(query);
             ins.executeUpdate();      
        }
    }
    for(int j=0;j<nodeListOgetti.getLength();j++){
        
    NodeList nodeListPart=((Element)nodeListOgetti.item(j)).getElementsByTagName("partecipanti");
    NodeList nodeListUtentiPartecipanti =((Element)nodeListPart.item(0)).getElementsByTagName("utente");
    Element oggetto=(Element)nodeListOgetti.item(j);
    for(int i=0; i<nodeListUtentiPartecipanti.getLength();i++){
        Element el=(Element)nodeListUtentiPartecipanti.item(i);
        
         if(el.getElementsByTagName("update").item(0).getTextContent().equals("up"))
        {
            
             String query = "INSERT INTO registrazione (id_utente,id_oggetto)VALUES"+ "('"+el.getAttribute("id")+"','"+oggetto.getAttribute("id")+"')";
             PreparedStatement ins = server.connection.prepareStatement(query);
             ins.executeUpdate();      
        }
    }
    }
}
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    

    
}
}

    

