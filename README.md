# Asta online

## Verifica TePSIT

### Gruppo 3

Fontanini
Levrini  
Gherardi

### Docente

prof. Vitale Lorenzo

### Descrizione
**Obiettivo minimo (80 punti)**  
Realizzare un programma Client-Server che implementi un’asta on line in cui viene gestita la banca
dati dei clienti e degli oggetti messi all’asta.
Ogni utente ha un account composto dalla propria e-mail e da una password in aggiunta ad una serie
di informazioni personali come nome,cognome, numero di cell, data di nascita, indirizzo e città di
residenza.
Gli oggetti all’asta sono composti da una tipologia (strumenti musicali, automobili, appartamenti,
etc.), prezzo, un nome, una descrizione, una data, l’e-mail dell’autore che ha inserito l’oggetto.
Il Server offre tre servizi ai Client:  
**1.** Permette ad un nuovo utente di iscriversi inviando i propri dati al Server che procede a
memorizzarli nella propria banca dati.  
**2.** Gli utenti iscritti, dopo essersi loggati inviando la propria e-mail e password di accesso,
possono richiedere l’elenco degli oggetti all’asta indicando la tipologia a cui si è interessati.   
**3.** Gli utenti iscritti possono, inoltre, partecipare all’asta di un determinato oggetto inserendo il
suo identificativo;  
Gli oggetti all’asta mostrati all’utente, devono essere visualizzate secondo l’ordine di inserimento,
dalla meno recente alla più recente, comprensivo del numero corrente di partecipanti all’asta su
quell’oggetto.  

**Obiettivo avanzato (20 punti)**  
Implementare un ulteriore servizio di multicasting che prevede che i Client si mettano in ascolto.
Il Server, quando riceve una nuova partecipazione all’asta di un oggetto da un Client, prima di
salvarla nel database invia al Client connesso a cui è attribuito l’inserimento dell’oggetto, un
messaggio che lo informa del nome e cognome dell’utente che parteciperà all’asta.

*SUGGERIMENTO: Per semplificare la risoluzione di questo punto, prevedere che i Client, una volta in modalità
di ricezione multicasting, non ricevano comandi di input dall’utente ma restano esclusivamente in
ascolto.*


### Usage

Prima operazione da effettuare è ottenere il progetto dal repository lanciando il seguente comando dalla bash di git
```
git clone https://github.com/lorenzdev/AstaOnline.git
```
Successivamente lanciare i seguenti comandi per ottenere le modifiche:

```
git pull origin master
```

e per pubblicare le proprie modifiche:

```
git add .
```  
```
git commit -m "descrizione del commit"  
```  
```
git push origin master  
```  

### Consegna
Entro il 31/05/2019
