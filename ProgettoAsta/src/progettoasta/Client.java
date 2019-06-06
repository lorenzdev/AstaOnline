
package progettoasta;



import java.net.*;
import java.io.*;
import java.util.*;



public class Client {
    
  
    
    public Client(){
        
        try{
            Scanner scanner = new Scanner(System.in);
          /*  
            System.out.println("Qual è l'indirizzo del server?");          
            String address = scanner.nextLine();

            System.out.println("Qual è la porta su cui il server offre il servizio?");
            String porta = scanner.nextLine();
            
            int port = Integer.parseInt(porta);
*/
          
            Socket client = new Socket("127.0.0.1", 1234);
            
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
           String controllo = "si";
                
                 
         
                while(controllo.equals("si")){
                 
                System.out.println("1 REGISTRAZIONE \n2 LOGIN \n3 VISUALIZZA/REGISTRAZIONE ASTE \n4 ESCI"); 
                int menu = Integer.parseInt(scanner.nextLine());
                out.println(Integer.toString(menu));
                
                switch(menu){
                    
                    case 1:{   
                    boolean regg=true;
                    
                    while(regg==true)
                    {System.out.println("______________________________________________________");
                    System.out.println("#####   #####   ####  ###  #### ##### #####      #    ##### ###   ###   ##  ### ##### \n" +
" #   #   #  #  #   #   #  #   # # # #  #   #     #    #   #  #   #   #   ##  #   #  # \n" +
" #   #   #    #        #  ##      #    #   #    # #      #   #  #     #  ##  #   #    \n" +
" ####    ###  #        #   ###    #    ####     ###     #    #  #     #  # # #   ###  \n" +
" #  #    #    #   ###  #     ##   #    #  #    #   #   #     #  #     #  # # #   #    \n" +
" #   #   #  #  #   #   #  #   #   #    #   #   #   #  #   #  #   #   #   #  ##   #  # \n" +
"###  ## #####   ####  ### ####   ###  ###  ## ### ### ##### ###   ###   ###  #  ##### ");
                     System.out.println("//INSERIRE L'EMAIL//");
                     String email=scanner.nextLine();  
                     out.println(email);
                     System.out.println("//INSERIRE LA PASSWORD//");
                     String pass=scanner.nextLine();
                     out.println(pass);
                     System.out.println("*****************************");
                     System.out.println("INSERIRE I DATI PERSONALI");
                     System.out.println("*****************************");
                     System.out.println("//INSERISCI LA CITTA DI RESIDENZA//");
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
                   break;}
                        
                    case 2: {
                     
                    boolean logg=true;
                    
                    while(logg==true)
                    {  System.out.println("______________________________________________________");
                     System.out.println("###     ###     ####  ### ##  ### \n" +
" #     #   #   #   #   #   ##  #  \n" +
" #    #     # #        #   ##  #  \n" +
" #    #     # #        #   # # #  \n" +
" #    #     # #   ###  #   # # #  \n" +
" #  #  #   #   #   #   #   #  ##  \n" +
"#####   ###     ####  ### ###  #  ");
                      
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
                              
                            System.out.println("*******************************************");
                            System.out.println("LOGIN ESEGUITO CON SUCCESSO");
                              System.out.println("********************************************");
                          
                            logg=false;  
                        }
                    }
                    break;}
                    
                    case 3:{
                        String loggato=in.readLine();
                                
                        if(loggato.equals("loggato")){
                           
                         System.out.println("//INSERIRE LA TIPOLOGIA DEGLI OGGETTI A CUI SI E' INTERESSATI (INSERISCI \" - \" SE VUOI VISUALIZZARE TUTTI GLI OGGETTI)//");
                               
                             String tipogg=scanner.nextLine();
                             out.println(tipogg);
                             String risposta=in.readLine();
                             while(!risposta.equals("000")){
                                 System.out.println(risposta);
                                 risposta=in.readLine();
                             }
                             
                             System.out.println("\n //VUOLE PARTECIPARE A UN'ASTA? (-SI- PER PARTECIPARE *QUALSIASI ALTRO CARATTERE* PER TORNARE AL MENU)//");          
                             String partecipazione  = scanner.nextLine();
                             out.println(partecipazione);
                             if(partecipazione.equals("si"))
                             {   boolean fine=false;
                              System.out.println("//INSERISCI IDENTIFICATIVO DELL'OGGETTO (ID OGGETTO)//");  
                                 while(fine==false)
                                 {
                                 String id  = scanner.nextLine();
                             out.println(id);
                             String conferma= in.readLine();
                             if(conferma.equals("true")){
                                 
                        
                                 System.out.println("********************************************");
                                  System.out.println("ISCRIZIONE ALL'ASTA ESEGUITA");  
                                  System.out.println("********************************************");
                                  fine=true;
                             }else{
                           
                                 System.out.println("******************************************************************************************");
                                 System.out.println("IDENTIFICATIVO NON PRESENTE O GIA' ISCRITTO ALL'ASTA, ISNERISCI UN ATRO IDENTIFICATIVO OGGETTO (ID OGGETTO)");  
                                 System.out.println("********************************************************************************************");
                                  fine=false;
                             }}
                    }
                        }else{
                            
                            System.out.println("*******************************************");
                            System.out.println("EFFETTUARE PRIMA LOGIN");
                            System.out.println("*******************************************");
                        }
                break;}  
                    
                    case 4: {
                        
                       
                        System.out.println("*******************************************");
                        System.out.println("CHIUSURA IN CORSO");
                        System.out.println("*******************************************");
                        client.close();
                        controllo="chiudi";
                        break;
                    }
                    
                    default: {
                          
                             
                        System.out.println("*******************************************");
                        System.out.println("SERVIZIO NON DISPONIBILE");
                        System.out.println("*******************************************");
                        break;
                    }
                }
                }
   
        
            }catch(Exception ex){
            ex.printStackTrace();
        }
   
    
    }    
    public static void main(String args[]){
       
        new Client();
        
    }
    
}
