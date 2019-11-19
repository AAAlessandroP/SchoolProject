import java.io.*;
import java.net.*;
import java.util.*;

public class JavaHTTPServer implements Runnable //istanziata quando c'è una nuova conn
{ 
	static final int PORT = 3000;
	
	private Socket connect;        
	
	public JavaHTTPServer(Socket c) 
        {
		connect = c;
	}
	
	public static void main(String[] args) 
        {
		try 
                {
			ServerSocket serverConnect = new ServerSocket(PORT);
			System.out.println("Server avviato sulla porta : " + PORT + " ...\n");
			
			while (true) 
                        {
                            Socket c = serverConnect.accept();
                            JavaHTTPServer myServer = new JavaHTTPServer(c);
                            Thread thread = new Thread(myServer);
                            thread.start();
			}
		} 
                catch (IOException ex) 
                {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() 
        {
		BufferedReader in = null; 
                PrintWriter out = null; 

		try {
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			out = new PrintWriter(connect.getOutputStream());
			
			String input = in.readLine();
                        System.out.println("ARRIVATOCI: '" + input + "'");
                        
			StringTokenizer parse = new StringTokenizer(input);
			String method = parse.nextToken().toUpperCase();
			String request = parse.nextToken().toLowerCase();
                        String response = "";
                        
                        //if (request.contains("?"))
                        //    request = request.substring(request.indexOf("?") + 1);
                        if(input.equals("GET /favicon.ico HTTP/1.1"))
                             response = "";
                        else response = processRequest(request);
                        
                        
			out.println("HTTP/1.1 200 OK");
			out.println("Server: Java HTTP Server : 1.0");
			out.println("Date: " + new Date());
			out.println("Content-type: text/html");
			out.println("Content-length: " + response.length());
			out.println();  
			out.flush();  
                                        
                        out.write(response);
			out.flush();
		} 
                catch (Exception ex) 
                {
                        ex.printStackTrace();
		} 
                finally 
                {
			try 
                        {
				in.close();
				out.close();
				connect.close();
			} 
                        catch (Exception ex) 
                        {
				ex.printStackTrace();
			} 
		}
	}
        
        public String processRequest(String s)// processa richieste da web e da app
        {
            String response = "";
            
            if(s.equals("/")){// RISPONDO CON IL FRONTEND PRINCIPALE
                
                System.out.println("RISPONDO CON IL FRONTEND PRINCIPALE");
                String fileName = "E:\\TEP\\webserverDue\\src\\index.html";//PATH ASS NECESSARIO (-JAVA 2020)
                File file = new File(fileName);
                try{
                  FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while((line = br.readLine()) != null){
                        //process the line
                        response+=line;
                    }
                }catch(Exception e){e.printStackTrace();}
            }
            
            if(s.equals("/script.js")){// RISPONDO CON script.js
                
                System.out.println("RISPONDO CON script.js");
                String fileName = "E:\\TEP\\webserverDue\\src\\script.js";//PATH ASS NECESSARIO (-JAVA 2020)
                File file = new File(fileName);
                try{
                  FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while((line = br.readLine()) != null){
                        //process the line
                        response+=line;
                    }
                }catch(Exception e){e.printStackTrace();}
            }
            
            if(s.contains("/search")){// RISPONDO alla richiesta x 1 doc
                
                Orario o = new Orario();
                
                System.out.println("RISPONDO alla richiesta x 1 doc "+s);
                // search=NOME[&giorno=G][&ora=O]
                s= s.replace("/search?nome=","nome=");
                s= s.replaceAll("%20"," ");             

                String requestPart[] = s.split("&");
                Properties pr = new Properties();
                    for(int i = 0; i < requestPart.length; i++)
                    {
                        StringTokenizer st = new StringTokenizer(requestPart[i], "=");
                        String ss = st.nextToken();       
                        pr.setProperty(ss, st.nextToken());//Calls the Hashtable method put.
                        //aggiunge a pr le le coppie key-value che ci sono nella richiesta
                    }   

                    GregorianCalendar gc = new GregorianCalendar();
                    int giorno = gc.get(Calendar.DAY_OF_WEEK) -2; // giorno della settimana: 0 lun, 1 mar...
                    if(giorno<0) giorno =6;// se dom=6
                    int ora = gc.get(Calendar.HOUR_OF_DAY) - 8; // ora attuale: 0 per le 8, 1 per le 9 ... 

                    if(pr.getProperty("settimanale","").equals("true")){
                        System.out.println("richiestoci il settimanale");
                        response = o.infoDocente(pr.getProperty("nome"),true);
                    }else{
                        System.out.println("richiestoci un ora precisa");
                        
                        if(pr.getProperty("giorno", "") == "")// se non c'era è oggi
                            pr.setProperty("giorno", String.valueOf(giorno));
                        if(pr.getProperty("ora", "") == "")
                            pr.setProperty("ora", String.valueOf(ora));                    
                        response = o.infoDocente(pr.getProperty("nome"), pr.getProperty("ora"), pr.getProperty("giorno"));     
                    }
            }
            
            if(s.contains("/aula")){
                
                Orario o = new Orario();
                s= s.replaceAll("%20"," ");
                s= s.replace("/aula?aula=","aula=");
                String requestPart[] = s.split("&");
                Properties pr = new Properties();
                for(int i = 0; i < requestPart.length; i++)
                {
                    StringTokenizer st = new StringTokenizer(requestPart[i], "=");
                    String ss = st.nextToken();       
                    pr.setProperty(ss, st.nextToken());//Calls the Hashtable method put.
                    //aggiunge a pr le le coppie key-value che ci sono nella richiesta
                }   
                GregorianCalendar gc = new GregorianCalendar();
                int giorno = gc.get(Calendar.DAY_OF_WEEK) -2; // giorno della settimana: 0 lun, 1 mar...
                if(giorno<0) giorno =6;// se dom=6
                int ora = gc.get(Calendar.HOUR_OF_DAY) - 8; // ora attuale: 0 per le 8, 1 per le 9 ... 
                if(pr.getProperty("giorno", "") == "")// se non c'era è oggi
                    pr.setProperty("giorno", String.valueOf(giorno));
                if(pr.getProperty("ora", "") == "")
                    pr.setProperty("ora", String.valueOf(ora));
                
                response = o.aulaOccupata(pr.getProperty("aula"), pr.getProperty("ora"), pr.getProperty("giorno"));
            }
            
            if(s.contains("/orabuca")){
                
                Orario o = new Orario();
                s= s.replaceAll("%20"," ");
                s= s.replace("/orabuca?doc1=","doc1=");
                String requestPart[] = s.split("&");
                Properties pr = new Properties();
                for(int i = 0; i < requestPart.length; i++)
                {
                    StringTokenizer st = new StringTokenizer(requestPart[i], "=");
                    String ss = st.nextToken();       
                    pr.setProperty(ss, st.nextToken());//Calls the Hashtable method put.
                    //aggiunge a pr le le coppie key-value che ci sono nella richiesta
                }   
                System.out.println("pr.getProperty(\"doc1\")"+pr.getProperty("doc1"));
                System.out.println("pr.getProperty(\"doc2\")"+pr.getProperty("doc2"));
                
                response = o.oreBucheComuni(pr.getProperty("doc1"), pr.getProperty("doc2"));
            }else 
            
            if(s.equals("/all")){// RISPONDO alla richiesta x tutti
                
                Orario o = new Orario();
                response = o.tuttiDocenti();
            }
            
            System.out.println("mando response "+response);
            return response;
        }
}