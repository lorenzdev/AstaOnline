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
            
           
                 System.out.println("SI DEVE REGISTRARE?"); 
                 
                 String registrazione = scanner.nextLine();
                out.println(registrazione);
                 if(registrazione.equals("si"))
                     
                   
                {   
                    boolean regg=true;
                    
                    while(regg==true)
                    {
                     System.out.println("//INSERIRE L'EMAIL//");
                     String email=scanner.nextLine();  
                     out.println(email);
                     System.out.println("//INSERIRE LA PASSWORD//");
                     String pass=scanner.nextLine();
                     out.println(pass);
                     System.out.println("*****************************");
                     System.out.println("INSERIRE I DATI PERSONALI");
                     System.out.println("*****************************");
                     System.out.println("//inserisci la citta di residenza//");
                     String citta_residenza=scanner.nextLine();
                     out.println(citta_residenza);
                      System.out.println("//INSERIRE L'INDIRIZZO//");
                     String indirizzo=scanner.nextLine();
                     out.println(indirizzo);
                      System.out.println("//INSERIRE LA DATA DI NASCITA//");
                     String data_nascita=scanner.nextLine();
                     out.println(data_nascita);
                      System.out.println("//INSERIRE IL NUMERO DI CELLULARE//");
                     String nr_cell=scanner.nextLine();
                     out.println(nr_cell);
                      System.out.println("//INSERIRE IL NOME//");
                     String nome=scanner.nextLine();
                     out.println(nome);
                      System.out.println("//INSERIRE IL COGNOME//");
                     String cognome=scanner.nextLine();
                     out.println(cognome);
                        String reg=in.readLine();
                          
                        if(reg.equals("false"))
                        {
                             System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                            System.out.println("EMAIL GIA USATA,REINSERIRE I DATI");
                             System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
                            regg=true;
                         
                        }
                        else
                        {
                             System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                             System.out.println("REGISTRAZIONE ESEGUITA CON SUCCESSO");
                             regg=false;
                              System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                              
                        }    
                    }
                    
                    
                   }  
          
                  
                     
                 
                 
                 {
                     
                    boolean logg=true;
                    
                    while(logg==true)
                    {  System.out.println("__________________");
                     System.out.println("LOGIN");
                      
                      System.out.println("//INSERIRE L'EMAIL//");
                      String email_log=scanner.nextLine();
                      out.println(email_log);
                      System.out.println("//INSERIRE LA PASSWORD//");
                      String pass_log=scanner.nextLine();
                      out.println(pass_log);
                      
                      String log=in.readLine();
                     
                        if(log.equals("false"))
                        {
                              System.out.println("*******************************************");
                            System.out.println("PASSWORD O EMAIL SBAGLIATA,REINSERIRE I DATI:");
                              System.out.println("********************************************");
                            logg=true;
                            
                        }
                       
                        else{                        
                            System.out.println("login eseguito con successo");
                            logg=false;
                            
                        }
                    }
                 }  
                 
                
                         
                             System.out.println("inserire la tipologia degli oggetti a cui si è interessati (inserisci tutto se vuoi visualizzare tutti gli oggetti)");
                               
                             String tipogg=scanner.nextLine();
                             out.println(tipogg);
                             String risposta=in.readLine();
                            System.out.println(risposta);
                                                           
   
                             System.out.println("vuole partecipare a un'asta?");          
                             String partecipazione  = scanner.nextLine();
                             out.println(partecipazione);
                             if(partecipazione.equals("si"))
                             {
                                 boolean b=true;
                                  while(b==true)
                                  {    
                                  System.out.println("inserire il proprio nominativo: "); 
                                  String nominativo=scanner.nextLine();
                                  out.println(nominativo);
                                  String nom=in.readLine();
                                  if(nom.equals("false")){
                                      System.out.println("nominativo errato,reinserirlo: ");
                                       String nominativo2=scanner.nextLine();
                                       out.println(nominativo2);
                                  }
                                  else{
                                      b=false;
                                  }
                                  
                                  }
                                  boolean c=true;
                                  while(c==true)
                                  {    
                                  System.out.println("inserire l'oggeto a cui si è interessati "); 
                                  String ogg=scanner.nextLine();
                                  out.println(ogg);
                                  String oggetto=in.readLine();
                                  if(oggetto.equals("false")){
                                      System.out.println("oggetto non presente,reinserirlo  ");
                                       String oggetto2=scanner.nextLine();
                                       out.println(oggetto2);
                                  }
                                  else{
                                      c=false;
                                  
                                  
                                  }
                                  }
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
