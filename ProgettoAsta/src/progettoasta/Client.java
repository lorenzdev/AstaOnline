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
                 String registrazione = scanner.nextLine();
                 out.println(registrazione);
                 if(registrazione=="si")
                     
                   
                {   
                    boolean regg=true;
                    
                    while(regg=true)
                    {
                     System.out.println("inserire l'email");
                     String email=scanner.nextLine();  
                     out.println(email);
                     System.out.println("inserire la password");
                     String pass=scanner.nextLine();
                     out.println(pass);
                     System.out.println("inseririmento dati personali");
                     System.out.println("inserisci la citta di residenza");
                     String citta_residenza=scanner.nextLine();
                     out.println(citta_residenza);
                      System.out.println("inserisci l'indirizzo");
                     String indirizzo=scanner.nextLine();
                     out.println(indirizzo);
                      System.out.println("inserisci la data di nascita");
                     String data_nascita=scanner.nextLine();
                     out.println(data_nascita);
                      System.out.println("inserisci il numero cellulare");
                     String nr_cell=scanner.nextLine();
                     out.println(nr_cell);
                      System.out.println("inserisci il nome");
                     String nome=scanner.nextLine();
                     out.println(nome);
                      System.out.println("inserisci il cognome");
                     String cognome=scanner.nextLine();
                     out.println(cognome);
                        String reg=in.readLine();
                        if(reg=="false")
                        {
                            out.println("email gia usata,reinserire i dati");
                            regg=true;
                        }
                        else
                        {
                             out.println("registrazione eseguita con successo");
                             regg=false;        ;
                        }    
                    }
                     
                    
                  
                     
                 }
                 else
                 {
                     
                    boolean logg=true;
                    
                    while(logg=true)
                    { System.out.println("login");
                      System.out.println("inserire l'email");
                      String email_log=scanner.nextLine();
                      out.println(email_log);
                      System.out.println("inserire la password");
                      String pass_log=scanner.nextLine();
                      out.println(pass_log);
                      String pass=in.readLine();
                      String email=in.readLine();
                        if(pass=="false")
                        {
                            out.println("password sbagliata,riprovare");
                            logg=true;
                        }
                        if(email=="false")
                        {
                            out.println("email sbagliata sbagliata,riprovare");
                            logg=true;
                        }
                        if(pass=="true" && email=="true")
                        {
                            out.println("login eseguito con successo");
                            logg=false;
                        }
                    }
                 }  
                 
                boolean risp=true;
                         if(risp=true)
                         {
                             System.out.println("inserire la tipologia degli oggetti a cui si è interessati");
                             String tipogg=scanner.nextLine();
                             out.println(tipogg);
                             
                             System.out.println("vuole partecipare a un'asta?");          
                             String partecipazione  = scanner.nextLine();
                             out.println(partecipazione);
                             if(partecipazione=="si")
                             {
                                 boolean b=true;
                                  while(b=true)
                                  {    
                                  System.out.println("inserire il proprio nominativo: "); 
                                  String nominativo=scanner.nextLine();
                                  out.println(nominativo);
                                  System.out.println("inserire l'oggeto a cui si è interessati "); 
                                  String ogg=scanner.nextLine();
                                  out.println(ogg);
                                  //if oggetto o nominativo diverso dai dati nel database richiede il nominativo 
                                  // o l'oggetto,oppure scrivere 0 per uscire
                                  }
                             }
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
