import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.net.*;
import java.io.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Client extends javax.swing.JFrame{
    
    BufferedReader in = null;
    PrintWriter out = null;
    
    public Client() throws Exception{
        initComponents();
            
        
        DefaultComboBoxModel model = new DefaultComboBoxModel(getProfs());
        comboProf.setModel(model);//riempi la combo con i prof

    }

    private String[] getProfs(){
        
        String [] a = getThisUrl("http://localhost:3000/all").split("\\|");// la pipe è char speciale!
        log(getThisUrl("http://localhost:3000/all"));
        for (int i = 0; i < a.length; i++) {
            log(a[i]);
        }
        return a;
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtOra = new javax.swing.JTextField();
        comboGiorni = new javax.swing.JComboBox<>();
        btnVai = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboProf = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        btnGetSettimanale = new javax.swing.JButton();
        txtAula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnAula = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        txtOra.setText("ORA");

        comboGiorni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "OGGI", "LUNEDI", "MARTEDI", "MERCOLEDI", "GIOVEDI", "VENERDI", "SABATO", "DOMENICA" }));

        btnVai.setText("vai");
        btnVai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaiActionPerformed(evt);
            }
        });

        jLabel1.setText("RISPOSTA:");

        jLabel3.setText("APP CLIENT");

        comboProf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("[metti quale ora 1-23]");

        jLabel5.setText("[metti il giorno]");

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        txtOutput.setEnabled(false);
        jScrollPane1.setViewportView(txtOutput);

        btnGetSettimanale.setText("orario settimanale");
        btnGetSettimanale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetSettimanaleHandler(evt);
            }
        });

        txtAula.setText("aula");

        jLabel6.setText("cerca disponibilità aula con hh e gg");

        btnAula.setText("cerca aula");
        btnAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAulaHandler(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap(133, Short.MAX_VALUE)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5)))
                .addGap(36, 36, 36)
                .addComponent(txtOra, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btnGetSettimanale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(31, 31, 31)
                .addComponent(txtAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnAula)
                .addGap(49, 49, 49))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboGiorni, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(183, 183, 183)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(123, 123, 123)
                                .addComponent(comboProf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnVai)))
                        .addGap(59, 59, 59)))
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVai)
                    .addComponent(comboProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboGiorni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGetSettimanale)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtAula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAula)))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getThisUrl(String _url){
        log("URL interrogato: "+_url);
        try{
            URL url = new URL(_url);//e.g. "http://localhost:3000/search?nome=SAMBITO%20CAROLA"
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    yc.getInputStream()));
            String inputLine;

            if ((inputLine = in.readLine()) != null) 
                return inputLine;
            in.close();
        }catch(Exception e){e.printStackTrace(); JOptionPane.showMessageDialog(this,"CONNESSIONE FALLITA");}
        return "";
    }
    
    private void btnVaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaiActionPerformed
        log("comboProf.getSelectedItem().toString()"+comboGiorni.getSelectedItem().toString());
        String url = "http://localhost:3000/search?nome=";
        url += comboProf.getSelectedItem().toString().replaceAll(" ","%20");
        if(!comboGiorni.getSelectedItem().toString().equals("OGGI")){
            int i = comboGiorni.getSelectedIndex()-1;
            url += "&giorno="+i;
        }
        if(!txtOra.getText().equals("ORA")){
            int i = Integer.parseInt(txtOra.getText())-1;
            url += "&ora="+i;
        }
        txtOutput.setText(getThisUrl(url));
    }//GEN-LAST:event_btnVaiActionPerformed

    private void btnGetSettimanaleHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetSettimanaleHandler
       
        log("comboProf.getSelectedItem().toString()"+comboGiorni.getSelectedItem().toString());
        String url = "http://localhost:3000/search?nome=";
        url += comboProf.getSelectedItem().toString().replaceAll(" ","%20");
        url += "&settimanale=true";
        String column[]={"LUN","MAR","MER","GIO","VEN","SAB"};         

        String data = getThisUrl(url);
        String ore[]= data.split(";");
        String grid[][] = new String[6][8];
        for (int i = 0; i < ore.length; i++) {
            int hh = Integer.parseInt(ore[i].split(",")[0]);
            int gg = Integer.parseInt(ore[i].split(",")[1]);                       
            grid[hh][gg] = ore[i].substring(4);
            log(ore[i]+"   "+hh+gg);
        }
        JTable jt = new JTable(grid,column);
        jt.setBounds(50,300,400,3000);
        this.add(jt);
        this.getContentPane().repaint();
        
    }//GEN-LAST:event_btnGetSettimanaleHandler

    private void btnAulaHandler(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAulaHandler
        log("comboProf.getSelectedItem().toString()"+comboGiorni.getSelectedItem().toString());
        String url = "http://localhost:3000/aula?aula=";
        url += txtAula.getText().replaceAll(" ","%20");
        if(!comboGiorni.getSelectedItem().toString().equals("OGGI")){
            int i = comboGiorni.getSelectedIndex()-1;
            url += "&giorno="+i;
        }
        if(!txtOra.getText().equals("ORA")){
            int i = Integer.parseInt(txtOra.getText())-1;
            url += "&ora="+i;
        }
        txtOutput.setText(getThisUrl(url));
        
    }//GEN-LAST:event_btnAulaHandler

    void log(String s){
        System.out.println("> "+s);
    }
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                  new Client().setVisible(true);
                }catch(Exception e){}
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAula;
    private javax.swing.JButton btnGetSettimanale;
    private javax.swing.JButton btnVai;
    private javax.swing.JComboBox<String> comboGiorni;
    private javax.swing.JComboBox<String> comboProf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtAula;
    private javax.swing.JTextField txtOra;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
