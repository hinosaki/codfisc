/**
 * 
 * Copyright (C) 2006-2011 - Riccardo Mattiuzzo
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

/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */

package codfisc2;

// programma principale di avvio del programma
import java.util.*;
import java.text.*;
import java.awt.Toolkit;

/*
class Persona {
      
    private String cNome; 
    private String cCognome; 
    private String cCitta; 
    private String cProv;
    private String cSesso;
    private Date dData;
    
    private String result;
    private DateFormat formatter1;
    private SimpleDateFormat formatter2;
    
    private Locale localizzazione = new Locale("it","IT@euro");
    private int Stile = DateFormat.SHORT;
    
    
    public Persona(String cVal_01, String cVal_02, String cVal_03, String cVal_04, String cVal_05, Date dVal_06) {
        cNome = cVal_01;
        cCognome = cVal_02;
        cCitta = cVal_03;
        cProv = cVal_04;
        cSesso = cVal_05;
        dData = dVal_06;
    }
    
    
    public void Stampa() {
        System.out.println(cNome + " " + cCognome);
    }
    
    public void MostraLocale() {
        System.out.println("Impostazione di Sistema Locale: ----> " + localizzazione.toString());
    }
    
    public void MostraData1() {
        formatter1 = DateFormat.getDateInstance(Stile, localizzazione);
        result = formatter1.format(dData);
        System.out.println(result);
    }
}
*/

/*
class Utility {
    public void paint(Graphics g)
    {
        int width = getWidth();
        int height = getHeight();

        g.setColor(WHITE);
        g.fillRect(0, 0, width, height);
    };


};
*/

public class codfisc {
  public static void main(String[] args) 
  
  {
      // abilito il locale per l'Italia
      Locale.setDefault( new Locale("it", "IT") );
      
      String cVersione = "0.1.3-1";
  
      if (args.length == 0) {
          System.out.println("\nCodice Fiscale GPL. versione " + cVersione + ", Copyright (C) 2006 Riccardo Mattiuzzo");
          System.out.println("Codice Fiscale GPL comes with ABSOLUTELY NO WARRANTY; for details");
          System.out.println("type `codfisc -V'.  This is free software, and you are welcome.\n\n");
          System.out.println("Sintassi: codfisc [NOME] [COGNOME] [DATA DI NASCITA] [CITTA] [PROV] [SESSO]\n");
          System.out.println("NOME ..........: Nome della Persona.");
          System.out.println("                 (Se esiste un doppio nome, scrivere i due nomi attaccati)");
          System.out.println("COGNOME: ..... : Cognome della persona.");
          System.out.println("                 (Scritto senza eventuali apostrofi/accenti)");
          System.out.println("DATA DI NASCITA: Data di nascita.");
          System.out.println("                 (Scritta nel formato gg/mm/aaaa. es.: 01/04/1960.)");
          System.out.println("CITTA' ........: Citt� di nascita.");
          System.out.println("                 (Se contiene il carattere ' scrivere la citt� tra doppi apici,");
          System.out.println("                  Se contiene una vocale accentata, scrivere la vocale senza");
          System.out.println("                  accento seguita dal carattere ', il tutto scritto tra doppi apici.");
          System.out.println("                  es.: Canicatt� ----> \"Canicatti'\" )");
          System.out.println("                  Se stato estero, inserire lo stato)");
          System.out.println("PROV ..........: Provincia citta' di nascita.");
          System.out.println("                 (Scrivere 2 lettere)");
          System.out.println("                 Se stato estero, indicare la provincia con EE");
          System.out.println("SESSO .........: Sesso della persona.");
          System.out.println("                 (Scritto nel formato M o F)\n");
          System.out.println("Opzioni: codfisc -V                   Informazioni sul Programma.\n");
          
          System.exit(0);
      };
      
      // primo argomento
      if (args.length == 1) {
          if (args[0].equals("-V")) {
              System.out.println("\nCodice Fiscale GPL. versione " + cVersione + ", Copyright (C) 2006 Riccardo Mattiuzzo");
              System.out.println("Codice Fiscale GPL comes with ABSOLUTELY NO WARRANTY.\n");
              System.out.println("Si rammenta che il codice generato � da considerarsi indicativo.");
              System.out.println("L'unico codice con validit� legale � quello rilasciato dall'Agenzie delle Entrate.");
              System.out.println(args[0]);
              System.exit(0);
          };
      };
      
      
      // ci sono tutti i parametri
      if (args.length == 6) {
          
          // controllo sulla validit� dei valori inseriti.
      
          String cControllo = "";
          String[] acParte1 = {};
          String[] acParte2 = {};
          String[] acParte3 = {};
          String[] acParametro = {"","","","","",""};
          
          String cDummy = "";
          
          String cValidazione = "";
          
          gencode Codice = new gencode(); 
          
          // formattazione delle parole per evitare che abbiano vocali accentate
          // sostituisco la vocale accentata con una vocale senza accento
          for (int nVoce = 0;nVoce < 5; nVoce++) {
              
              cDummy = args[nVoce];
              
              // escludo la sostituzione per il sesso
              for (int i = 1; i < 5; i++) {
                  cDummy = cDummy.replaceAll("à", "a");
                  cDummy = cDummy.replaceAll("è", "e");
                  cDummy = cDummy.replaceAll("é", "e");
                  //cDummy = cDummy.replaceAll("�", "e");
                  cDummy = cDummy.replaceAll("ì", "i");
                  cDummy = cDummy.replaceAll("ò", "o");
                  cDummy = cDummy.replaceAll("ù", "u");
              };
              
              acParametro[nVoce] = cDummy;
          };
          
          String cInput01 = acParametro[0].toUpperCase(); // nome
          String cInput02 = acParametro[1].toUpperCase(); // cognome
          String cInput03 = args[2];                // data di nascita
          String cInput04 = acParametro[3].toUpperCase(); //citt�
          String cInput05 = acParametro[4].toUpperCase(); //provincia
          String cInput06 = args[5].toUpperCase(); //sesso
          
          /*
          // debug
          System.out.println(cInput01);
          System.out.println(cInput02);
          System.out.println(cInput03);
          System.out.println(cInput04);
          System.out.println(cInput05);
          System.out.println(cInput06);
          */
          
          String cCodice = Codice.genCheckInput(cInput01, cInput02, cInput03, cInput04, cInput05, cInput06);
          
          
          if (cCodice.equals("OK")) {
              
              acParte1 = Codice.genCognomeNome(cInput01,cInput02);
              acParte2 = Codice.genData(cInput03,cInput06);
              acParte3 = Codice.genCodCitta(cInput04,cInput05);
      
              cDummy = (acParte1[0] + acParte1[1] + acParte2[0] + acParte2[1] + acParte2[2] + acParte3[0]);
              
              ///DEBUG
              //System.out.println("---" + acParte3[0] + "---");
              
              
              cControllo = Codice.genCodControllo(cDummy);
              System.out.print("\n\nCodice Fiscale GPL - Versione " + cVersione );
              System.out.println("\n");
              System.out.println("Il codice fiscale generato �:\n");
              System.out.println("========================");
              System.out.println("|   " + (cDummy+cControllo) + "   |");
              System.out.println("========================\n");
              //System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
              
              System.exit(0);
          } else {
              Toolkit.getDefaultToolkit().beep();
              System.out.println("\nErrore nell'inserimento dei dati");
              System.exit(0);
          };
      } else {
              Toolkit.getDefaultToolkit().beep();
              System.out.println("\n\nControllare il numero di patrametri passati al programma.\n\n");
              System.exit(0);
      };
      
  };
}




