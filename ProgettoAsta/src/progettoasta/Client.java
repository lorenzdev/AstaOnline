/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package progettoasta;



import java.net.*;
import java.io.*;
import java.util.*;



public class Client {
    
  
    
    public Client(){
        
        try{
            Scanner scanner = new Scanner(System.in);
           
            
            System.out.println("Qual è l'indirizzo del server?");          
            String address = scanner.nextLine();

            System.out.println("Qual è la porta su cui il server offre il servizio?");
            String porta = scanner.nextLine();
            
            int port = Integer.parseInt(porta);
            
            
           
          
            Socket client = new Socket(address, port);
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            boolean a=false;
            if(a=false)
            {
                 System.out.println("si deve registrare?");          
                 String registrazione = in.readLine();
                 if(registrazione=="si")
                 {
                     System.out.println("inserire l'email");
                     String email=scanner.nextLine();  
                     System.out.println("inserire la password");
                     String pass=scanner.nextLine();  
                             
                 }
                 else
                 {
                     System.out.println("login");
                     System.out.println("inserire l'email");
                     String email=scanner.nextLine(); 
                     System.out.println("inserire la password");
                      String pass=scanner.nextLine();
                 }  
                 
                boolean risp=true;
                         if(risp=true)
                         {
                             
                         }
                 

                
            }
            System.out.println("di che tipo di pane vuoi vedere il prodotto?");
            String prodotto = scanner.nextLine();
        
            out.println(prodotto);
            
            String mem=in.readLine();
            
            while(mem!=null){
                
            System.out.println(mem);
            
            mem=in.readLine();
            
            }
            
          
            client.close();
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
   
    
    }    
    public static void main(String args[]){
       
        new Client();
        
    }
    
}
