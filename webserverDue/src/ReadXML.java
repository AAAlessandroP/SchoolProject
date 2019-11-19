import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.util.Set;
import java.util.Hashtable;


public class ReadXML {
    
    private NodeList profs ;
    
    public ReadXML(String nome) {

        try {

            File file = new File(nome);

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                                 .newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {
                    profs = doc.getChildNodes().item(0).getChildNodes();
                    //printNote(doc.getDocumentElement().getChildNodes()); 
                    //System.out.println(doc.getChildNodes().item(0).getChildNodes().item(0).getNodeName());
            }

        }catch (Exception e) {System.out.println(e.getMessage());}

    }
///RICORDA : MAI GETNODEVALUE() MA SEMPRE getTextContent()
///RICORDA : MAI GETNODEVALUE() MA SEMPRE getTextContent()
///RICORDA : MAI GETNODEVALUE() MA SEMPRE getTextContent()
///RICORDA : MAI GETNODEVALUE() MA SEMPRE getTextContent()

    public String tuttiDocenti(){
        
        String s="";
        for (int i = 0; i < profs.getLength(); i++) {
            
            Node docente = profs.item(i);
            if (docente.getNodeType() == Node.ELEMENT_NODE){// e non tag di fine
                
                //System.out.println("docente.getNodeName()"+docente.getNodeName());
                
                for (int j = 0; j < docente.getChildNodes().getLength(); j++) {
                    if (docente.getNodeType() == Node.ELEMENT_NODE){
                        Node attr = docente.getChildNodes().item(j);
                        //System.out.println("attr.getNodeName()"+attr.getNodeName());
                        if(attr.getNodeName().equals("nome"))
                            s+=attr.getTextContent()+"|";
                    }
                }
            }
        }
        return s;
    }
    
    public String infoDocente(String paramDocente, boolean settimanale){
        //codice che serializza secondo sintassi legacy per rispettare quella del client js per non dover implementare un parser xml anche lì, e tanto poi un metodo che leggesse da xml doveva esserci
        String s="";
        for (int i = 0; i < profs.getLength(); i++) {//ciclo sui prof
            
            Node docente_ = profs.item(i);
            
            if (docente_.getNodeType() == Node.ELEMENT_NODE){// e non tag di fine (</a>)
                
                Node nome = docente_.getChildNodes().item(1);
                Node orario = docente_.getChildNodes().item(3);
                System.out.println("nome.getTextContent()"+nome.getTextContent());
                if ( nome.getTextContent().equals(paramDocente)){//quel prof
                    System.out.println("orario.getNodeName()"+orario.getNodeName());
                    NodeList ore = orario.getChildNodes();
                    boolean singleton_=true;
                    for (int k = 0; k < ore.getLength(); k++) {//ciclo sulle ore dell'orario
                        Node ora = ore.item(k);
                        System.out.println("ora.getNodeName()"+ora.getNodeName());
                        if (ora.getNodeType() == Node.ELEMENT_NODE){
                            if(singleton_)
                                singleton_=false;
                            else
                                s+=";";//tra le ore ci va ;

                            NodeList attrs = ora.getChildNodes();
                            boolean singleton=true;
                            System.out.println("attrs.getLength()"+attrs.getLength());
                            for (int kk = 0; kk < attrs.getLength(); kk++) {// ciclo su ora,gg,classe,Compresenza ecc...
                                Node attr_ = attrs.item(kk);
                                System.out.println("attr_.getNodeName()"+attr_.getNodeName());
                                if (attr_.getNodeType() == Node.ELEMENT_NODE){
                                    if(singleton){
                                        s+=attrs.item(kk).getTextContent();//gli attributi di un ora van separati da , le ore con ;
                                        singleton=false;
                                    }else
                                        s+=","+ attrs.item(kk).getTextContent();//gli attributi di un ora van separati da , le ore con ;                                                
                                }
                            }
                        }
                    }

                    
                }
            }
        }
        

        return s;
    }
    
    Hashtable getAule(){
        Hashtable ht = new Hashtable();
        String fileContent="";
        String fileName = "E:\\TEP\\webserverDue\\src\\aule";//PATH ASS NECESSARIO (-JAVA 2020)
            File file = new File(fileName);
            ArrayList righe=new ArrayList();
            try{
              FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line = br.readLine()) != null){
                    //process the line
                    righe.add(line);
                }
            }catch(Exception e){e.printStackTrace();}
            for(int i=0;i<righe.toArray().length;i++){
                System.out.println(righe.get(i));
                ht.put(righe.get(i).toString().split("\\|")[0],righe.get(i).toString().split("\\|")[1]);
                System.out.println("righe[i].split(\"|\")[0]"+righe.get(i).toString().split("\\|")[0]);
                System.out.println("righe[i].split(\"|\")[1]"+righe.get(i).toString().split("\\|")[1]);
            }
        return ht;
    }
    
    public String infoDocente(String paramDocente, String paramOra, String paramGiorno)
    {        
        Hashtable aulePos = getAule();
        String s="";
        for (int i = 0; i < profs.getLength(); i++) {//ciclo sui prof
            
            Node docente_ = profs.item(i);
            
            if (docente_.getNodeType() == Node.ELEMENT_NODE){// e non tag di fine (</a>)
                
                Node nome = docente_.getChildNodes().item(1);
                Node orario = docente_.getChildNodes().item(3);
                System.out.println("nome.getTextContent()"+nome.getTextContent());
                if ( nome.getTextContent().equals(paramDocente)){//quel prof
                    System.out.println("orario.getNodeName()"+orario.getNodeName());
                    NodeList ore = orario.getChildNodes();
                    for (int k = 0; k < ore.getLength(); k++) {//ciclo sulle ore dell'orario
                        Node ora = ore.item(k);
                        //System.out.println("ora.getNodeName()"+ora.getNodeName());
                        if (ora.getNodeType() == Node.ELEMENT_NODE ){


                            NodeList attrs = ora.getChildNodes();//attributi di un ora
                            System.out.println("attrs.getLength()"+attrs.getLength());
                            Node hh = attrs.item(1);
                            Node gg = attrs.item(3);
                            
                            System.out.println("paramOra"+paramOra); 
                            System.out.println("paramGiorno"+paramGiorno);                              
                            System.out.println("hh.getTextContent()"+hh.getTextContent());
                            System.out.println("gg.getTextContent()"+gg.getTextContent());
                            
                            if(Integer.parseInt(hh.getTextContent())==Integer.parseInt(paramOra)&& Integer.parseInt(gg.getTextContent())==Integer.parseInt(paramGiorno)){
                                System.out.println("questa ora");
                                for (int kk = 0; kk < attrs.getLength(); kk++) {// ciclo su ora,gg,classe,Compresenza ecc...
                                    Node attr_ = attrs.item(kk);
                                    System.out.println("attr_.getNodeName()"+attr_.getNodeName());
                                    System.out.println("kk"+kk);


                                    if (attr_.getNodeType() == Node.ELEMENT_NODE){                                        
                                        s+=" "+attrs.item(kk).getTextContent();//gli attributi di un ora van separati da , le ore con ;  
                                        if(attrs.item(kk).getNodeName().equals("Aula")){//è aula, bene dov'è?
                                            s+="POS:"+aulePos.get(attrs.item(kk).getTextContent());
                                        }
                                    }
                                }
                                return s;
                            }
                        }
                    }
                }
            }
        }
        

        return "docente non occupato in quell'ora";
    }
    
    public String aulaOccupata(String paramAula, String paramOra, String paramGiorno){
    //  System.out.println("paramAula"+paramAula);
  //      System.out.println("paramOra"+paramOra);
//        System.out.println("paramGiorno"+paramGiorno);
        paramAula = paramAula.toUpperCase();
        
        for (int i = 0; i < profs.getLength(); i++) {//ciclo sui prof
            
            Node docente_ = profs.item(i);
            
            if (docente_.getNodeType() == Node.ELEMENT_NODE){// e non tag di fine (</a>)
                
                Node nome = docente_.getChildNodes().item(1);
                Node orario = docente_.getChildNodes().item(3);
                System.out.println("nome.getTextContent()"+nome.getTextContent());
                System.out.println("orario.getNodeName()"+orario.getNodeName());
                NodeList ore = orario.getChildNodes();
                for (int k = 0; k < ore.getLength(); k++) {//ciclo sulle ore dell'orario
                    Node ora = ore.item(k);
                    //System.out.println("ora.getNodeName()"+ora.getNodeName());
                    if (ora.getNodeType() == Node.ELEMENT_NODE ){


                        NodeList attrs = ora.getChildNodes();//attributi di un ora
                        System.out.println("attrs.getLength()"+attrs.getLength());
                        Node hh = attrs.item(1);
                        Node gg = attrs.item(3);
                        Node aula = attrs.item(11);

                        System.out.println("paramOra"+paramOra); 
                        System.out.println("paramGiorno"+paramGiorno);                              
                        System.out.println("hh.getTextContent()"+hh.getTextContent());
                        System.out.println("gg.getTextContent()"+gg.getTextContent());
                        System.out.println("aula.getTextContent()"+aula.getTextContent());
                        System.out.println("paramAula"+paramAula);
                        
/*TODO COMBOBOX DI AULE ANCHE SU CLIENT JAVA*/
                        if(Integer.parseInt(hh.getTextContent())==Integer.parseInt(paramOra) &&
                                Integer.parseInt(gg.getTextContent())==Integer.parseInt(paramGiorno) && 
                                aula.getTextContent().toUpperCase().equals(paramAula)
                            ){
                            return "aula "+paramAula+ "occupata da " +nome.getTextContent();
                        }
                    }
                }
            }
        }
        
        return "non saprei";
    }
    
    public String oreBucheComuni(String doc1, String doc2){
        doc1 = doc1.toUpperCase();
        doc2 = doc2.toUpperCase();
        Hashtable orarioDoc1 = new Hashtable();
        Hashtable orarioDoc2 = new Hashtable();
        
        for (int i = 0; i < profs.getLength(); i++) {//ciclo sui prof
            
            Node docente_ = profs.item(i);
            
            if (docente_.getNodeType() == Node.ELEMENT_NODE){//<docente> e non tag di fine (</a>)
                
                Node nome = docente_.getChildNodes().item(1);
                if(nome.getTextContent().equals(doc1)){ //su doc1
                                                System.out.println("su doc1");

                    Node orario = docente_.getChildNodes().item(3);

                    NodeList ore = orario.getChildNodes();
                    for (int k = 0; k < ore.getLength(); k++) {//ciclo sulle ore dell'orario
                        Node ora = ore.item(k);
                        if (ora.getNodeType() == Node.ELEMENT_NODE ){


                            NodeList attrs = ora.getChildNodes();//attributi di un ora
                            Node hh = attrs.item(1);
                            Node gg = attrs.item(3);
                            orarioDoc1.put(hh.getTextContent()+" "+gg.getTextContent(),"occupato adesso");



                        }

                    }
                }else if (nome.getTextContent().equals(doc2)){//su doc
                    Node orario = docente_.getChildNodes().item(3);
                            System.out.println("su doc2");

                    NodeList ore = orario.getChildNodes();
                    for (int k = 0; k < ore.getLength(); k++) {//ciclo sulle ore dell'orario
                        Node ora = ore.item(k);
                        if (ora.getNodeType() == Node.ELEMENT_NODE ){
                            System.out.println("su doc2");


                            NodeList attrs = ora.getChildNodes();//attributi di un ora
                            Node hh = attrs.item(1);
                            Node gg = attrs.item(3);
                            orarioDoc2.put(hh.getTextContent()+" "+gg.getTextContent(),"occupato adesso");



                        }
                    }
                }
            }
        }
        
        Set ore1 = orarioDoc1.keySet();
        Set ore2 = orarioDoc2.keySet();
        Object[] a1 = ore1.toArray();//array tipo ["hh gg", "hh gg"] ovvero le ore buche di un prof
        Object[] a2 = ore2.toArray();//array tipo ["hh gg", "hh gg"] ovvero le ore buche di un altro prof

        for(int i=0;i<a1.length;i++){
            for (int j = 0; j < a2.length; j++) {
                if(a1[i].equals(a2[j]))
                     return "potrebbero incontrarsi in :" +(Integer.parseInt(a1[i].toString().split(" ")[0])+1)+ " ora del " +(Integer.parseInt(a1[i].toString().split(" ")[1])+1) +" giorno.";
            }
        }
        
        return "non si potrebbero incontrare";
    }
    
  private static void printNote(NodeList nodeList) {

    for (int count = 0; count < nodeList.getLength(); count++) {
        
	Node tempNode = nodeList.item(count);
	// make sure it's element node.
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
		// get node name and value
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
                /*if(tempNode.getNodeName().equals("nome"))
                    s+*/
		System.out.println("Node Value =" + tempNode.getTextContent());
                
		if (tempNode.hasChildNodes()) {
			printNote(tempNode.getChildNodes());
		}
		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	}
    }
  }

}
