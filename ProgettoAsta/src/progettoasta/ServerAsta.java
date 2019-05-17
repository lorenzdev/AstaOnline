package progettoasta;


import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ServerAsta {

    private static int port = 1234; 
     static final String DB_URL = "jdbc:mysql://localhost/asta";
    static final String DB_DRV = "com.mysql.jdbc.Driver";
    static final String DB_USER = "root";
    static final String DB_PASSWD = "root";
    static Connection connection = null;
    static Statement statement = null;
    
    
    public static void main(String[] args) {
        
        try{
            // CREO LA CONNESSIONE AL DATABASE
            Class.forName(DB_DRV);
            connection=DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWD);
            statement=connection.createStatement();
           
            //select utenti
            PreparedStatement selectUtente = connection.prepareStatement("SELECT * FROM utente");
            ResultSet utenti=selectUtente.executeQuery();
            
            //select oggetti
            PreparedStatement selectOggetti = connection.prepareStatement("SELECT * FROM oggetto");
            ResultSet oggetti=selectUtente.executeQuery();
            
            //select registrazione
            PreparedStatement selectRegi = connection.prepareStatement("SELECT * FROM registrazione");
            ResultSet registrazioni=selectRegi.executeQuery();
            
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
