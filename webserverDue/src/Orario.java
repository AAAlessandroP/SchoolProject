import java.util.*;
import java.io.*;
import java.nio.file.Files;


class Orario
{
    Hashtable ht = new Hashtable();
    
    public Orario()
    {
        String dati = "";   
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("dati").getFile());// MAI MAI INIDIZIZZO ASS CON SPAZI(-JAVA 2020)
        try (Scanner scanner = new Scanner(file)) 
        {
            dati = scanner.nextLine();
            scanner.close();    
        }
        catch (Exception ex) 
        {ex.printStackTrace();}
        
        //System.out.println("dati"+dati);
        
        String righe[] = dati.split("§");//!!
                
        for(int i = 0; i < righe.length; i++)
        {
            String parti[] = righe[i].split(":");
            ht.put(parti[0], parti[1]);
        }    
    }
    
    public String tuttiDocenti(){
        String s="";
        Set<String> keys = ht.keySet();
        for(String key: keys)
        {
          //System.out.println(key);
            s+= key+"|";
        }
        return s.substring(0, s.length()); // "prof1|p2|p3"
    }
    public String infoDocente(String docente, boolean settimanale)
    {
        System.out.println("infoDocente interrogata con :"+docente+"*");

        Set<String> keys = ht.keySet();
        for(String key: keys)
        {
            if(key.toLowerCase().contains(docente.toLowerCase()))//quel prof
            {
                return ht.get(key).toString(); 
            }
        }
        return "docente non in orario";
    
    }
    
    public String infoDocente(String docente, String ora, String giorno)
    {
        System.out.println("infoDocente interrogata con :"+docente+"*"+ora+"*"+giorno+"*");

        Set<String> keys = ht.keySet();
        for(String key: keys)
        {
            if(key.toLowerCase().contains(docente.toLowerCase()))//quel prof
            {
                String fascia[] =  ht.get(key).toString().split(";");
                for(int i = 0; i < fascia.length; i++)
                {
                    if(fascia[i].startsWith(ora + "," + giorno))
                    {
                        System.out.println(fascia[i]);
                        String componenti[] = fascia[i].split(",");
                        String mess = "Il prof. " + key + " svolge ";
                        if(Character.isDigit(componenti[2].charAt(0)))
                        {
                            if(componenti[2].endsWith("."))
                            {
                                mess += "una lezione in " + componenti[2].substring(0, componenti[2].length() - 1);
                                mess += " di " + componenti[4];
                                mess += " in compresenza con il prof. " + componenti[3];
                                mess += " nell'aula " + componenti[5];
                            }
                            else
                            {
                                mess += "una lezione in " + componenti[2];
                                mess += " di " + componenti[3];
                                mess += " nell'aula " + componenti[4];
                            }
                        }
                        else
                        {
                            mess += componenti[2];
                            mess += (componenti.length == 4 ? " nell'aula " + componenti[3] : "");
                        }

                        return mess;
                    }    
               }
            }
        }

        return "docente non in orario";
    }
        
}
