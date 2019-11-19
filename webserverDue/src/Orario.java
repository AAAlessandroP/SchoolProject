import java.util.*;

class Orario
{
    Hashtable ht = new Hashtable();
    ReadXML rxml;
    
    public Orario()
    {
        rxml = new ReadXML("E:\\TEP\\webserverDue\\src\\prof.xml");
    }
    
    public String tuttiDocenti(){

        return rxml.tuttiDocenti();
    }
    
    public String infoDocente(String docente, boolean settimanale)
    {
        System.out.println("infoDocente interrogata con :"+docente+"*");

        return rxml.infoDocente(docente.toUpperCase(), true);
    }
    
    public String aulaOccupata(String aula, String ora, String giorno)
    {
        return rxml.aulaOccupata(aula, ora, giorno);
    }
    
    public String infoDocente(String docente, String ora, String giorno)
    {
        System.out.println("infoDocente interrogata con :"+docente+"*"+ora+"*"+giorno+"*");
        return rxml.infoDocente(docente.toUpperCase(),ora,giorno);
    }

     public String oreBucheComuni(String doc1, String doc2)
    {

        return rxml.oreBucheComuni(doc1,doc2);
    }
    
}
