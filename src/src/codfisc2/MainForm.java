/**
 * 
 * Copyright (C) 2006-2017 - Riccardo Mattiuzzo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */


// https://blogs.oracle.com/geertjan/validating-a-form-in-the-netbeans-platform

package codfisc2;

import java.awt.Color;
import java.sql.*;
import java.util.Locale;
import javax.swing.text.MaskFormatter;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import org.netbeans.validation.api.ui.ValidationGroup;
import org.netbeans.validation.api.ui.swing.ValidationPanel;




/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */
public class MainForm extends javax.swing.JFrame {

  String[] aElenco = new String[startup.aiDimensione[0]];
  int nContatore = 0;

  /** Creates new form MainForm */
  public MainForm() {
    initComponents();

    // centro la finestra dell'utente/password'
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension size = this.getSize();
    screenSize.height = screenSize.height / 2;
    screenSize.width = screenSize.width / 2;
    size.height = size.height / 2;
    size.width = size.width / 2;
    int y = screenSize.height - size.height;
    int x = screenSize.width - size.width;
    this.setLocation(x, y);


    // effettuo il caricamento dell'array da passare alla combobox delle citta

    try {
      Statement stmt = startup.conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COMUNE,PROVINCIA FROM ITALIA ORDER BY COMUNE,PROVINCIA;");

      while (rs.next()) {
        cbCitta.addItem((rs.getString("COMUNE") + " (" + rs.getString("PROVINCIA") + ")"));
      }

    } catch (Exception e) {
      System.out.println("ERRORE Ricezione Dati =====> " + e);
      System.exit(0);
    }


    cbCitta.setSelectedIndex(0);

  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgSesso = new javax.swing.ButtonGroup();
        bgStato = new javax.swing.ButtonGroup();
        pnlAnagrafica = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edCognome = new javax.swing.JTextField();
        edNome = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnGenera = new javax.swing.JButton();
        btnCancella = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblCF = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        cbCitta = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        rbItalia = new javax.swing.JRadioButton();
        rbEstero = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        rbSessoM = new javax.swing.JRadioButton();
        rbSessoF = new javax.swing.JRadioButton();
        jPanel7 = new javax.swing.JPanel();
        MaskFormatter mf = null;
        try {
            mf = new MaskFormatter("##/##/####");
        } catch (Exception p) {
            p.printStackTrace();
        }
        edDataNasc = new javax.swing.JFormattedTextField(mf);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CODFISC - Codice Fiscale GPL");
        setResizable(false);

        pnlAnagrafica.setBorder(javax.swing.BorderFactory.createTitledBorder("[ Anagrafica ]"));

        jLabel1.setText("Cognome:");

        jLabel2.setText("Nome:");

        edCognome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edCognomeActionPerformed(evt);
            }
        });
        edCognome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                edCognomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                edCognomeFocusLost(evt);
            }
        });

        edNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edNomeActionPerformed(evt);
            }
        });
        edNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edNomeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnlAnagraficaLayout = new javax.swing.GroupLayout(pnlAnagrafica);
        pnlAnagrafica.setLayout(pnlAnagraficaLayout);
        pnlAnagraficaLayout.setHorizontalGroup(
            pnlAnagraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnagraficaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edCognome, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(edNome, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlAnagraficaLayout.setVerticalGroup(
            pnlAnagraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnagraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1)
                .addComponent(jLabel2)
                .addComponent(edNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(edCognome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ValidationPanel panel = new ValidationPanel();
        panel.setInnerComponent(pnlAnagrafica);
        ValidationGroup group = panel.getValidationGroup();
        group.add(edCognome,
            Validators.REQUIRE_NON_EMPTY_STRING
        );
        ValidationPanel panel2 = new ValidationPanel();
        panel.setInnerComponent(pnlAnagrafica);
        ValidationGroup group2 = panel2.getValidationGroup();
        group2.add(edNome,
            Validators.REQUIRE_NON_EMPTY_STRING
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGenera.setMnemonic('G');
        btnGenera.setText("Genera");
        btnGenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeneraActionPerformed(evt);
            }
        });

        btnCancella.setMnemonic('A');
        btnCancella.setText("Annulla");
        btnCancella.setDefaultCapable(false);
        btnCancella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancellaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGenera)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancella)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenera)
                    .addComponent(btnCancella))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCF.setFont(new java.awt.Font("Courier 10 Pitch", 1, 24));
        lblCF.setForeground(new java.awt.Color(42, 110, 43));
        lblCF.setText("----------------");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCF, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCF)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("[ Stato - Citta' ] "));

        jLabel4.setText("Citta':");

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bgStato.add(rbItalia);
        rbItalia.setSelected(true);
        rbItalia.setText("Italia");
        rbItalia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbItaliaMouseClicked(evt);
            }
        });

        bgStato.add(rbEstero);
        rbEstero.setText("Estero");
        rbEstero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbEsteroMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(rbItalia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbEstero)
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(rbItalia)
                .addComponent(rbEstero))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCitta, 0, 223, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCitta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("[ Sesso ]"));

        bgSesso.add(rbSessoM);
        rbSessoM.setSelected(true);
        rbSessoM.setText("Maschile");

        bgSesso.add(rbSessoF);
        rbSessoF.setText("Femminile");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbSessoM)
                    .addComponent(rbSessoF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(rbSessoM)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbSessoF))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("[ Data Nascita ]"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(edDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(edDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAnagrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAnagrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edNomeActionPerformed
    }//GEN-LAST:event_edNomeActionPerformed

    private void rbItaliaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbItaliaMouseClicked
      try {
        Statement stmt = startup.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COMUNE,PROVINCIA FROM ITALIA ORDER BY COMUNE,PROVINCIA;");

        cbCitta.removeAllItems();

        while (rs.next()) {
          cbCitta.addItem((rs.getString("COMUNE") + " (" + rs.getString("PROVINCIA") + ")"));
        }

      } catch (Exception e) {
        System.out.println("ERRORE Ricezione Dati =====> " + e);
        System.exit(0);
      }


      cbCitta.setSelectedIndex(0);


    }//GEN-LAST:event_rbItaliaMouseClicked

    private void rbEsteroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbEsteroMouseClicked
      try {
        Statement stmt = startup.conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT DENOMINAZ FROM ESTERO ORDER BY DENOMINAZ;");

        cbCitta.removeAllItems();

        while (rs.next()) {
          cbCitta.addItem((rs.getString("DENOMINAZ") + " (EE)"));
        }

      } catch (Exception e) {
        System.out.println("ERRORE Ricezione Dati =====> " + e);
        System.exit(0);
      }
      cbCitta.setSelectedIndex(0);
    }//GEN-LAST:event_rbEsteroMouseClicked

  @SuppressWarnings("empty-statement")
    private void btnGeneraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeneraActionPerformed

    //String sCognome = "mattiuzzo";
    //String sNome = "riccardo";

    String sCognome = null;
    String sNome = null;
    String sSesso = "M";
    String sDataNasc = "";
    String[] asLocalita = new String[2];
    String sCitta = "";
    String sProv = "";


    // imposta un valore di default su Mario
    if (edNome.getText().trim().equals("")) {
      sNome = "Mario";
      edNome.setText("Mario");
    } else
      sNome = edNome.getText().trim().replaceAll(" ", "");

    if (edCognome.getText().trim().equals("")) {
      sCognome = "ROSSI";
      edCognome.setText("ROSSI");
    } else
      sCognome = edCognome.getText().trim().replaceAll(" ", "");

    if (rbSessoM.isSelected())
      sSesso = "M";
    else
      sSesso = "F";

    
    ;
    if ( Supporto.isValidDate(edDataNasc.getText()) ){
      sDataNasc = edDataNasc.getText();
    }
    else
    {
      sDataNasc = "04/05/1911";
    }
      
    asLocalita = new String[2];
    asLocalita = Supporto.SplitCittaProv(cbCitta.getSelectedItem().toString());
    sCitta = asLocalita[0];
    sProv = asLocalita[1];

    // abilito il locale per l'Italia
    Locale.setDefault(new Locale("it", "IT"));

    // controllo sulla validita' dei valori inseriti.
    String cControllo = "";
    String[] acParte1 = {};
    String[] acParte2 = {};
    String[] acParte3 = {};
    String[] acParametro = {"", "", "", "", "", ""};

    String cDummy = "";
    String cValidazione = "";

    gencode Codice = new gencode();

    // formattazione delle parole per evitare che abbiano vocali accentate
    // sostituisco la vocale accentata con una vocale senza accento
    acParametro[0] = sNome.toUpperCase(); // nome
    acParametro[1] = sCognome.toUpperCase(); // cognome
    acParametro[2] = sDataNasc; // data di nascita
    acParametro[3] = sCitta; //citta'
    acParametro[4] = sProv; //provincia
    acParametro[5] = sSesso;

    
    // debug
    /*
    System.out.println(acParametro[0]);
    System.out.println(acParametro[1]);
    System.out.println(acParametro[2]);
    System.out.println(acParametro[3]);
    System.out.println(acParametro[4]);
    System.out.println(acParametro[5]);
    */

    String cCodice = Codice.genCheckInput(acParametro[0], acParametro[1], acParametro[2], acParametro[3], acParametro[4], acParametro[5]);


    if (cCodice.equals("OK")) {

      acParte1 = Codice.genCognomeNome(acParametro[0], acParametro[1]);
      acParte2 = Codice.genData(acParametro[2], acParametro[5]);
      acParte3 = Codice.genCodCitta(acParametro[3], acParametro[4]);

      cDummy = (acParte1[0] + acParte1[1] + acParte2[0] + acParte2[1] + acParte2[2] + acParte3[0]);

      ///DEBUG
      //System.out.println("---" + acParte3[0] + "---");


      cControllo = Codice.genCodControllo(cDummy);

      lblCF.setText((cDummy + cControllo));

      //System.exit(0);
      } else {
      //Toolkit.getDefaultToolkit().beep();
      System.out.println("\nErrore nell'inserimento dei dati");
      System.exit(0);
    }
    ;




    }//GEN-LAST:event_btnGeneraActionPerformed

  private void btnCancellaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancellaActionPerformed

    // azzeramnto dei valori
    edNome.setText("");
    edCognome.setText("");
    edDataNasc.setText("  /  /    ");
    cbCitta.setSelectedIndex(0);
    rbSessoM.setSelected(true);
    rbItalia.setSelected(true);
  }//GEN-LAST:event_btnCancellaActionPerformed

  private void edCognomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edCognomeActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_edCognomeActionPerformed

  private void edCognomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edCognomeFocusLost
    
    // rimuove qualsiasi numero inserito
    // return str.replaceAll("/[^0-9]/g", "");
    String sDirtyString = edCognome.getText();
    sDirtyString = sDirtyString.replaceAll("\\d", "");
    edCognome.setText(sDirtyString);
    
  }//GEN-LAST:event_edCognomeFocusLost

  private void edNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edNomeFocusLost

    String sDirtyString = edNome.getText();
    sDirtyString = sDirtyString.replaceAll("\\d", "");
    edNome.setText(sDirtyString);
    
  }//GEN-LAST:event_edNomeFocusLost

  private void edCognomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edCognomeFocusGained
    //edCognome.setBackground(startup.coloreSelezione);
  }//GEN-LAST:event_edCognomeFocusGained

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {

      public void run() {
        new MainForm().setVisible(true);
      }
    });
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgSesso;
    private javax.swing.ButtonGroup bgStato;
    private javax.swing.JButton btnCancella;
    private javax.swing.JButton btnGenera;
    private javax.swing.JComboBox cbCitta;
    private javax.swing.JTextField edCognome;
    private javax.swing.JFormattedTextField edDataNasc;
    private javax.swing.JTextField edNome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblCF;
    private javax.swing.JPanel pnlAnagrafica;
    private javax.swing.JRadioButton rbEstero;
    private javax.swing.JRadioButton rbItalia;
    private javax.swing.JRadioButton rbSessoF;
    private javax.swing.JRadioButton rbSessoM;
    // End of variables declaration//GEN-END:variables
}
