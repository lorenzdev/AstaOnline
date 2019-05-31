package progettoasta;


import java.io.StringReader;
import java.io.StringWriter;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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
                xmlString=xmlString+"<utente>"+"<email>"+utenti.getString("email")+"</email>"+
                        "<pass>"+utenti.getString("password")+"</pass>"+"<id_utente>"+utenti.getString("id_utente")+"</id_utente>"+
                        "<citta_residenza>"+utenti.getString("citta_residenza")+"</citta_residenza>"+
                        "<indirizzo>"+utenti.getString("indirizzo")+"</indirizzo>"+
                        "<data_nascita>"+utenti.getString("data_nascita")+"</data_nascita>"+
                        "<nr_cell>"+utenti.getString("nr_cell")+"</nr_cell>"+
                        "<nome>"+utenti.getString("nome")+"</nome>"+
                        "<cognome>"+utenti.getString("cognome")+"</cognome>"+"<update></update></utente>";
            }
            xmlString=xmlString+"</utenti><oggetti>";
            
            while(oggetti.next()){
                String id=oggetti.getString("id_oggetto");
                xmlString=xmlString+"<oggetto id=\""+id+"\">"+"<tipologia>"+oggetti.getString("tipologia")+"</tipologia>"+
                        "<prezzo>"+oggetti.getString("prezzo")+"</prezzo>"+"<nome>"+oggetti.getString("nome")+"</nome>"+
                        "<descrizione>"+oggetti.getString("descrizione")+"</descrizione>"+
                        "<data>"+oggetti.getString("data")+"</data>"+
                        "<email_autore>"+oggetti.getString("email_autore")+"</email_autore>";
                 PreparedStatement query = connection.prepareStatement("SELECT id_utente FROM registrazione WHERE id_oggetto= \""+id+"\" ");
                 ResultSet result= query.executeQuery();
                 xmlString=xmlString+"<partecipanti>";
                 while(result.next()){
                     xmlString=xmlString+"<utente id=\""+result.getString("id_utente")+"\">"+"<update></update></utente>";
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
         
       DOMSource domSource = new DOMSource(doc);
       StringWriter writer = new StringWriter();
       StreamResult result = new StreamResult(writer);
       TransformerFactory tf = TransformerFactory.newInstance();
       Transformer transformer = tf.newTransformer();
       transformer.transform(domSource, result);
       System.out.println(writer.toString());
        
            ServerSocket socket = new ServerSocket(port);
            
            System.out.println("Server pronto!");
            
         
            while(true){
      
                Socket client = socket.accept();
                

                Clientthread newConnect = new Clientthread (client);
                newConnect.start();
                System.out.println("\n...nuovo client collegato....");
                System.out.println("ip : "+client.getInetAddress());
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        finally{
           try{
               connection.close();
           
           }catch(Exception ex){
            ex.printStackTrace();
           }
           
        }
    }
    
    
}
