import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.net.*;
import java.io.*;
public class Client extends javax.swing.JFrame{
    
    BufferedReader in = null;
    PrintWriter out = null;
    
    public Client() throws Exception{
        initComponents();
            

        
        DefaultComboBoxModel model = new DefaultComboBoxModel(getProfs());
        comboProf.setModel(model);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel4.setText("[metti quale ora 0-24]");

        jLabel5.setText("[metti il giorno]");

        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        txtOutput.setEnabled(false);
        jScrollPane1.setViewportView(txtOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(53, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtOra, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel1))))
                    .addComponent(comboGiorni, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(comboProf, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVai))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVai)
                            .addComponent(comboProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(comboGiorni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
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
    private javax.swing.JButton btnVai;
    private javax.swing.JComboBox<String> comboGiorni;
    private javax.swing.JComboBox<String> comboProf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtOra;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}
