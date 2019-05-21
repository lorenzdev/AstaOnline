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
import org.xml.sax.InputSource;

public class ServerAsta {

    private static int port = 1234; 
     static final String DB_URL = "jdbc:mysql://sql7.freemysqlhosting.net";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "sql7292605";
    static final String DB_PASSWD = "xqKBv2cAvM";
    static Connection connection = null;
    static Statement statement = null;
    static String xmlString=null;
    static Document doc=null;
    
    private static void updateXML(){
        try{
         // CREO LA CONNESSIONE AL DATABASE
            Class.forName(DB_DRV);
            connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
            statement=connection.createStatement();
           statement.executeUpdate("use sql7292605;");
            //select utenti
            PreparedStatement selectUtente = connection.prepareStatement("SELECT * FROM utente");
            ResultSet utenti=selectUtente.executeQuery();
            
            //select oggetti
            PreparedStatement selectOggetti = connection.prepareStatement("SELECT * FROM oggetto");
            ResultSet oggetti=selectOggetti.executeQuery();
            
            //select registrazione
            PreparedStatement selectRegi = connection.prepareStatement("SELECT * FROM registrazione");
            ResultSet registrazioni=selectRegi.executeQuery();
            
        xmlString="<progetto><utenti>";
            while(utenti.next()){
                xmlString=xmlString+"<utente>"+"<e-mail>"+utenti.getString("e-mail")+"</e-mail>"+
                        "<pass>"+utenti.getString("pass")+"</pass>"+"<id_utente>"+utenti.getString("id_utente")+"</id_utente>"+
                        "<citta_residenza>"+utenti.getString("citta_residenza")+"</citta_residenza>"+
                        "<indirizzo>"+utenti.getString("indirizzo")+"</indirizzo>"+
                        "<data_nascita>"+utenti.getString("data_nascita")+"</data_nascita>"+
                        "<nr_cell>"+utenti.getString("nr_cell")+"</nr_cell>"+
                        "<nome>"+utenti.getString("nome")+"</nome>"+
                        "<cognome>"+utenti.getString("cognome")+"</cognome>"+"</utente>";
            }
            xmlString=xmlString+"</utenti><oggetti>";
            
            while(utenti.next()){
                String id=oggetti.getString("id_oggetto");
                xmlString=xmlString+"<oggetto id=\""+id+"\">"+"<tipologia>"+oggetti.getString("tipologia")+"</tipologia>"+
                        "<prezzo>"+oggetti.getString("prezzo")+"</prezzo>"+"<nome>"+oggetti.getString("nome")+"</nome>"+
                        "<descrizione>"+utenti.getString("descrizione")+"</descrizione>"+
                        "<data>"+utenti.getString("data")+"</data>"+
                        "<e-mail_autore>"+utenti.getString("e-mail_autore")+"</e-mail_autore>";
                 PreparedStatement query = connection.prepareStatement("SELECT id_utente FROM registrazione WHERE id_oggetto="+id);
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
            
        //Parser che produce l'oggetto dell'albero DOM (Document Object Model) dal contenuto XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         
        //Definisco un oggetto di tipo DocumentBuilder che fa da API per ottenere l'istanza del DOM
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        //CREA STRINGA CON XML
        
            ServerSocket socket = new ServerSocket(port);
            
            System.out.println("Server pronto!");
            
         
            while(true){
      
                Socket client = socket.accept();
                

               // ThreadClient newConnect = new ThreadClient (client, treni, doc);
               // newConnect.start();
                
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
