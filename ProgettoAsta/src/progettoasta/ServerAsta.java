package progettoasta;


import java.io.StringReader;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ServerAsta {

    private static int port = 1234; 
     static final String DB_URL = "jdbc:mysql://remotemysql.com:3306?autoReconnect=true&useSSL=false";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "Q9BJUnWnNN";
    static final String DB_PASSWD = "6R1G8VK4nd";
    static Connection connection = null;
    static Statement statement = null;
    static String xmlString=null;
    public static Document doc;
    
    private static void updateXML(){
        try{
         // CREO LA CONNESSIONE AL DATABASE
            Class.forName(DB_DRV);
            connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
            statement=connection.createStatement();
           statement.executeUpdate("use Q9BJUnWnNN;");

            //select utenti
            PreparedStatement selectUtente = connection.prepareStatement("SELECT * FROM utente");
            ResultSet utenti=selectUtente.executeQuery();
            
            //select oggetti
            PreparedStatement selectOggetti = connection.prepareStatement("SELECT * FROM oggetto");
            ResultSet oggetti=selectOggetti.executeQuery();

            
        xmlString="<progetto><utenti>";
            while(utenti.next()){
                xmlString=xmlString+"<utente>"+"<e-mail>"+utenti.getString("e-mail")+"</e-mail>"+
                        "<pass>"+utenti.getString("password")+"</pass>"+"<id_utente>"+utenti.getString("id_utente")+"</id_utente>"+
                        "<citta_residenza>"+utenti.getString("citta_residenza")+"</citta_residenza>"+
                        "<indirizzo>"+utenti.getString("indirizzo")+"</indirizzo>"+
                        "<data_nascita>"+utenti.getString("data_nascita")+"</data_nascita>"+
                        "<nr_cell>"+utenti.getString("nr_cell")+"</nr_cell>"+
                        "<nome>"+utenti.getString("nome")+"</nome>"+
                        "<cognome>"+utenti.getString("cognome")+"</cognome>"+"</utente>";
            }
            xmlString=xmlString+"</utenti><oggetti>";
            
            while(oggetti.next()){
                String id=oggetti.getString("id_oggetto");
                xmlString=xmlString+"<oggetto id=\""+id+"\">"+"<tipologia>"+oggetti.getString("tipologia")+"</tipologia>"+
                        "<prezzo>"+oggetti.getString("prezzo")+"</prezzo>"+"<nome>"+oggetti.getString("nome")+"</nome>"+
                        "<descrizione>"+oggetti.getString("descrizione")+"</descrizione>"+
                        "<data>"+oggetti.getString("data")+"</data>"+
                        "<e-mail_autore>"+oggetti.getString("e-mail_autore")+"</e-mail_autore>";
                 PreparedStatement query = connection.prepareStatement("SELECT id_utente FROM registrazione WHERE id_oggetto= \""+id+"\" ");
                 ResultSet result= query.executeQuery();
                 xmlString=xmlString+"<partecipanti>";
                 while(result.next()){
                     xmlString=xmlString+"<utente id=\""+result.getString("id_utente")+"\"/>";
                 }
                 xmlString=xmlString+"</partecipanti></oggetto>";
            }
            xmlString=xmlString+"</oggetti></progetto>";  
            
             //Parser che produce l'oggetto dell'albero DOM (Document Object Model) dal contenuto XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //Definisco un oggetto di tipo DocumentBuilder che fa da API per ottenere l'istanza del DOM
        DocumentBuilder builder = factory.newDocumentBuilder();
        //creo doc xml
       doc = builder.parse(new InputSource(new StringReader(xmlString)));
       
               
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        
  
    }
  
    public static void main(String[] args) {
        
        try{
         updateXML();
         System.out.println(xmlString);
        
            ServerSocket socket = new ServerSocket(port);
            
            System.out.println("Server pronto!");
            
         
            while(true){
      
                Socket client = socket.accept();
                

                Clientthread newConnect = new Clientthread (client);
                newConnect.start();
                
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        finally{
           try{UpdateDB();
               connection.close();
           
           }catch(Exception ex){
            ex.printStackTrace();
           }
           
        }
    }
    
    
    private static void UpdateDB(){
     try{
        int c=0;
    PreparedStatement selectUtente = connection.prepareStatement("SELECT * FROM utente");
    ResultSet utenti=selectUtente.executeQuery();
    Node root = doc.getFirstChild();
               
               NodeList nodeListUtenti = ((Element)root).getElementsByTagName("utenti");
              NodeList XMLutenti=((Element)nodeListUtenti.item(0)).getElementsByTagName("utente");
    while(utenti.next())
    {
        c++;
    }
    //scorro gli utenti
    for(int i=0;i<XMLutenti.getLength();i++)
    {
        Element el;
            el = (Element)XMLutenti.item(i);
        for(int j=0;j<c;j++)
        {
            if(!el.getElementsByTagName("e-mail").item(0).getTextContent().equals(utenti.getString("e-mail")))
            {
                  String query = "INSERT INTO utenti (e-mail,password,citta_residenza,indirizzo,data_nascita,nr_cell,nome,cognome)VALUES"+ "('"+el.getElementsByTagName("e-mail").item(0).getTextContent()+"','"+el.getElementsByTagName("password").item(0).getTextContent()+"','"+el.getElementsByTagName("citta_residenza").item(0).getTextContent()+"','"+el.getElementsByTagName("indirizzo").item(0).getTextContent()+"','"+el.getElementsByTagName("data_nascita").item(0).getTextContent()+"','"+el.getElementsByTagName("nr_cell").item(0).getTextContent()+"','"+el.getElementsByTagName("nome").item(0).getTextContent()+"','"+el.getElementsByTagName("cognome").item(0).getTextContent()+"')";
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
