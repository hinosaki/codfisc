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

package codfisc2;

import java.sql.*;
import java.awt.Toolkit;

/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */
// classe che si occupa della generazione del codice
public class gencode {
  /*
  private String[] aConsonanti = {
  "B","C","D","F","G","H","J","K","L","M","N","P","Q","R","S","T","V","W","X","Y","Z"
  };
  private String[] aVocali = {
  "A","E","I","O","U"
  };
   */

  private String cConsonantiParola = "";
  private String cVocaliParola = "";

  // =========================================================================
  // Metodo per il controllo dei valori inseriti dall'utente
  // Il metodo ritorna "OK" se i parametri sono corretti, mentre ritorna "NO"
  // se uno dei parametri non � corretto.
  public String genCheckInput(String cVal_01, String cVal_02, String cVal_03, String cVal_04, String cVal_05, String cVal_06) {

    String cNome = cVal_01;
    String cCognome = cVal_02;
    String cDataNasc = cVal_03;
    String cCitta = cVal_04;
    String cProv = cVal_05;
    String cSesso = cVal_06;

    String cStatus = "NO";
    String cOk01 = "NO";
    String cOk02 = "NO";
    String cOk03 = "NO";
    String cOk04 = "NO";
    String cOk05 = "NO";
    String cOk06 = "NO";
    String cOk07 = "NO";
    String cOk08 = "NO";
    String cOk09 = "NO";

    String[] aDummy = {};
    int nDummy = 0;

    // NOME
    if (cNome.matches("[A-Z]*")) {
      cOk01 = "OK";
    }

    // COGNOME
    if (cCognome.matches("[A-Z]*")) {
      cOk02 = "OK";
    }

    // formattazione della data
    aDummy = cDataNasc.split("/");


    try {
      // giorno
      nDummy = Integer.parseInt(aDummy[0]);
      if (nDummy > 31) {
        //System.out.println("Errore nella data. Controllare il giorno.");
        //System.exit(1);
      } else
        cOk03 = "OK";

      // mese
      nDummy = Integer.parseInt(aDummy[1]);
      if (nDummy > 12) {
        //System.out.println("Errore nella data. Controllare il mese.");
        //System.exit(1);
      } else
        cOk04 = "OK";

      // anno
      nDummy = Integer.parseInt(aDummy[2]);
      if (nDummy < 1900) {
        //System.out.println("Errore nella data. Controllare l'anno.");
        //System.exit(1);
      } else
        cOk05 = "OK";
    } catch (Exception e) {
      System.out.println("Formato data non valido");
    }

    // controllo sul numero di caratteri della provincia
    if (cProv.length() == 2) {
      cOk06 = "OK";
    }

    // controllo sul sesso
    if (cSesso.length() == 1) {
      cOk07 = "OK";
    }
    if (cOk07.equals("OK")) {
      if ((cSesso.equals("M")) || (cSesso.equals("F"))) {
        cOk07 = "OK";
      } else {
        cOk07 = "NO";
      }
    }

    /*
    // DEBUG
    System.out.println(cNome);
    System.out.println(cCognome);
    System.out.println(cDataNasc);
    System.out.println(cCitta);
    System.out.println(cProv);
    System.out.println(cSesso);

    System.out.println(cOk01);
    System.out.println(cOk02);
    System.out.println(cOk03);
    System.out.println(cOk04);
    System.out.println(cOk05);
    System.out.println(cOk06);
    System.out.println(cOk07);
     */


    if ((cOk01.equals("OK")) && (cOk02.equals("OK")) && (cOk03.equals("OK")) && (cOk04.equals("OK")) && (cOk05.equals("OK")) && (cOk06.equals("OK")) && (cOk07.equals("OK"))) {
      cStatus = "OK";
    }

    return cStatus;
  }

  ;

  // =========================================================================
  // Metodo per il calcolo del numero delle consonanti/vocali.
  // Ritorna un array che indica il numero di vocali/consonanti)
  // e associa il gruppo di vocali/consonanti alle variabili:
  // cConsonantiParola
  // cVocaliParola
  // 0 = Vocale
  // 1 = Consonante
  private int[] CountConsonantiVocali(String cVal_01) {

    // passo in maiuscolo e rimuovo eventuali spazi all'interno.
    // un nome composto deve essere considerato come una sequenza
    // continua di caratteri
    String cParola = cVal_01.toUpperCase();
    cParola = cParola.replaceAll(" ", "");
    
    String cVocale = "";
    String cConsonante = "";
    String sLetteraCorrente = "";

    int nContatoreVocale = 0;
    int nContatoreConsonante = 0;
    int nLunghezza = 0;

    // il primo valore � una vocale, il secondo � una consonante
    int aParola[] = {0, 0};


    for (int i = 0; i <= (cParola.length() - 1); i++) {
      // DEBUG
      //System.out.println(i);
      //System.out.println("SUBSTR ---> " + cParola.substring(i,(i+1)));

      sLetteraCorrente = cParola.substring(i, (i + 1));
      
      // devo mettere +1 altrimenti mi viene ritornato un valore vuoto
      if (sLetteraCorrente.equals("A") || sLetteraCorrente.equals("E") || 
              sLetteraCorrente.equals("I") || sLetteraCorrente.equals("O")
              || sLetteraCorrente.equals("U")) {
        cVocale = cVocale + sLetteraCorrente;
        nContatoreVocale++;
      } else {
        cConsonante = cConsonante + sLetteraCorrente;
        nContatoreConsonante++;
      }
      
    }

    aParola[0] = nContatoreVocale;
    aParola[1] = nContatoreConsonante;

    // assegno alle variabili superiori
    cConsonantiParola = cConsonante;
    cVocaliParola = cVocale;

    return aParola;
  }

  ;

  //==========================================================================
  // Metodo per la generazione delle prime 3 lettere legate al cognome
  public String[] genCognomeNome(String cVal_01, String cVal_02) {

    /*
    SOLO PER NOME - CASO 0 - se il nome ha 4 o pi� consonanti:              1-3-4 consonante
    COGNOME/NOME  - CASO 1 - se il cognome/nome ha 3 o pi� consonanti:      1-2-3 consonante
    COGNOME/NOME  - CASO 2 - se il cognome/nome ha 2 consonanti:            1-2 consonante e 1 vocale
    COGNOME/NOME  - CASO 3 - se il cognome/nome ha 1 consonante e 2 vocali: 1 consonante e 2-3 vocale
    COGNOME/NOME  - CASO 4 - se il cognome/nome ha 1 consonante e 1 vocale: 1 consonante 1 vocale e carattere X
    COGNOME/NOME  - CASO 5 - se il cognome/nome ha 2 vocali:                2 vocale e carattere X
     */
    String cNome = cVal_01;
    String cCognome = cVal_02;
    String cGruppoCognome = "";
    String cGruppoNome = "";
    String cConsonantiCognome = "";
    String cVocaliCognome = "";
    String cConsonantiNome = "";
    String cVocaliNome = "";

    String[] aGruppoCognomeNome = {"", ""};

    int[] aDimensioneNome = {0, 0};
    int[] aDimensioneCognome = {0, 0};

    aDimensioneCognome = CountConsonantiVocali(cCognome);
    cConsonantiCognome = cConsonantiParola;
    cVocaliCognome = cVocaliParola;

    aDimensioneNome = CountConsonantiVocali(cNome);
    cConsonantiNome = cConsonantiParola;
    cVocaliNome = cVocaliParola;


    // effettuo il controllo sul numero di consonanti del nome/cognome
    // NOME
    // CASO 0
    if (aDimensioneNome[1] >= 4) {
      cGruppoNome = (cConsonantiNome.substring(0, 1) + cConsonantiNome.substring(2, 3) + cConsonantiNome.substring(3, 4));
    }

    // CASO 1
    if (aDimensioneNome[1] == 3) {
      cGruppoNome = cConsonantiNome.substring(0, 3);
    }

    // CASO 2
    if (aDimensioneNome[1] == 2) {
      cGruppoNome = ((cConsonantiNome.substring(0, 2)) + (cVocaliNome.substring(0, 1)));
    }

    // CASO 3
    if ((aDimensioneNome[1] == 1) && (aDimensioneNome[0] == 2)) {
      cGruppoNome = ((cConsonantiNome.substring(0, 1)) + (cVocaliNome.substring(0, 2)));
    }

    // CASO 4
    if ((aDimensioneNome[1] == 0) && (aDimensioneNome[0] == 2)) {
      cGruppoNome = ((cVocaliNome.substring(0, 2)) + "X");
    }


    // COGNOME
    // CASO 1
    if (aDimensioneCognome[1] >= 3) {
      cGruppoCognome = cConsonantiCognome.substring(0, 3);
    }

    // CASO 2
    if (aDimensioneCognome[1] == 2) {
      cGruppoCognome = ((cConsonantiCognome.substring(0, 2)) + (cVocaliCognome.substring(0, 1)));
    }

    // CASO 3
    if ((aDimensioneCognome[1] == 1) && (aDimensioneCognome[0] == 2)) {
      cGruppoCognome = ((cConsonantiCognome.substring(0, 1)) + (cVocaliCognome.substring(0, 2)));
    }

    // CASO 4
    if ((aDimensioneCognome[1] == 0) && (aDimensioneCognome[0] == 2)) {
      cGruppoCognome = ((cVocaliCognome.substring(0, 2)) + "X");
    }

    // DEBUG
        /*
    System.out.println("Utente: " + cCognome + " " + cNome);
    System.out.println("Dimensione vocali Nome ........ " + aDimensioneNome[0]);
    System.out.println("Dimensione consonanti Nome .... " + aDimensioneNome[1]);
    System.out.println("Dimensione vocali Cognome ........ " + aDimensioneCognome[0]);
    System.out.println("Dimensione consonanti Cognome .... " + aDimensioneCognome[1]);
    System.out.println("Consonanti Nome: " + cConsonantiNome);
    System.out.println("Vocali Nome: " + cVocaliNome);
    System.out.println("Consonanti Cognome: " + cConsonantiCognome);
    System.out.println("Vocali Cognome: " + cVocaliCognome);
    System.out.println("Gruppo Cognome: " + cGruppoCognome);
    System.out.println("Gruppo Nome: " + cGruppoNome);
     */

    // ritorno l'array col gruppo nome/cognome
    // 0 = cognome
    // 1 = nome
    aGruppoCognomeNome[0] = cGruppoCognome;
    aGruppoCognomeNome[1] = cGruppoNome;
    return aGruppoCognomeNome;
  };

  //==========================================================================
  // Metodo per la generazione della data nel formato del codice fiscale
  // anno-mese-giorno (AA-M-GG)
  public String[] genData(String cVal_01, String cVal_02) {

    String cData = cVal_01;
    String cSesso = cVal_02;
    String cMese = "";
    String cGiorno = "";
    String cAnno = "";

    String[] aData = {"", "", ""};
    String[] aDataCodFisc = {"", "", ""};

    aData = cData.split("/");


    cGiorno = aData[0];
    int nGiorno = 0;

    // calcolo del giorno.
    // se si � una donna il giorno viene aumentata di 40 unit�
    // l'ordine dell'array � anno-mese-giorno
    if (cSesso.equals("F")) {
      nGiorno = Integer.parseInt(cGiorno);
      nGiorno = nGiorno + 40;
      cGiorno = Integer.toString(nGiorno);
    }
    

    if (aData[1].equals("01")) {
      cMese = "A";
    } else if (aData[1].equals("02")) {
      cMese = "B";
    } else if (aData[1].equals("03")) {
      cMese = "C";
    } else if (aData[1].equals("04")) {
      cMese = "D";
    } else if (aData[1].equals("05")) {
      cMese = "E";
    } else if (aData[1].equals("06")) {
      cMese = "H";
    } else if (aData[1].equals("07")) {
      cMese = "L";
    } else if (aData[1].equals("08")) {
      cMese = "M";
    } else if (aData[1].equals("09")) {
      cMese = "P";
    } else if (aData[1].equals("10")) {
      cMese = "R";
    } else if (aData[1].equals("11")) {
      cMese = "S";
    } else if (aData[1].equals("12")) {
      cMese = "T";
    }

    cAnno = aData[2];
    aDataCodFisc[0] = cAnno.substring(2, 4);
    aDataCodFisc[1] = cMese;
    aDataCodFisc[2] = cGiorno;

    // DEBUG
        /*
    System.out.println("L'anno associato �: " + aDataCodFisc[0]);
    System.out.println("Il giorno associato �: " + aDataCodFisc[2]);
    System.out.println("Il mese associato �: " + aDataCodFisc[1]);
    System.out.println("Codice completo: ....-> " + aDataCodFisc[0] + aDataCodFisc[1] + aDataCodFisc[2]);
     */

    return aDataCodFisc;
  };

  //==========================================================================
  // metodo per la generazione del codice di controllo
  public String genCodControllo(String cVal_01) {

    String cStringa = cVal_01;
    String cCodControllo = "";
    int nValoreLettera = 0;
    int nValoreIntermedio = 0;
    int nValoreCodiceControllo = 0;
    int nCodiceControllo = 0;

    for (int i = 0; i <= (cStringa.length() - 1); i++) {

      // se il valore � dispari
      if (i % 2 == 0) {
        if ((cStringa.substring(i, (i + 1)).equals("A")) || (cStringa.substring(i, (i + 1)).equals("0"))) {
          nValoreLettera = (nValoreLettera + 1);
        }
        if ((cStringa.substring(i, (i + 1)).equals("B")) || (cStringa.substring(i, (i + 1)).equals("1"))) {
          nValoreLettera = (nValoreLettera + 0);
        }
        if ((cStringa.substring(i, (i + 1)).equals("C")) || (cStringa.substring(i, (i + 1)).equals("2"))) {
          nValoreLettera = (nValoreLettera + 5);
        }
        if ((cStringa.substring(i, (i + 1)).equals("D")) || (cStringa.substring(i, (i + 1)).equals("3"))) {
          nValoreLettera = (nValoreLettera + 7);
        }
        if ((cStringa.substring(i, (i + 1)).equals("E")) || (cStringa.substring(i, (i + 1)).equals("4"))) {
          nValoreLettera = (nValoreLettera + 9);
        }
        if ((cStringa.substring(i, (i + 1)).equals("F")) || (cStringa.substring(i, (i + 1)).equals("5"))) {
          nValoreLettera = (nValoreLettera + 13);
        }
        if ((cStringa.substring(i, (i + 1)).equals("G")) || (cStringa.substring(i, (i + 1)).equals("6"))) {
          nValoreLettera = (nValoreLettera + 15);
        }
        if ((cStringa.substring(i, (i + 1)).equals("H")) || (cStringa.substring(i, (i + 1)).equals("7"))) {
          nValoreLettera = (nValoreLettera + 17);
        }
        if ((cStringa.substring(i, (i + 1)).equals("I")) || (cStringa.substring(i, (i + 1)).equals("8"))) {
          nValoreLettera = (nValoreLettera + 19);
        }
        if ((cStringa.substring(i, (i + 1)).equals("J")) || (cStringa.substring(i, (i + 1)).equals("9"))) {
          nValoreLettera = (nValoreLettera + 21);
        }
        if ((cStringa.substring(i, (i + 1)).equals("K"))) {
          nValoreLettera = (nValoreLettera + 2);
        }
        if ((cStringa.substring(i, (i + 1)).equals("L"))) {
          nValoreLettera = (nValoreLettera + 4);
        }
        if ((cStringa.substring(i, (i + 1)).equals("M"))) {
          nValoreLettera = (nValoreLettera + 18);
        }
        if ((cStringa.substring(i, (i + 1)).equals("N"))) {
          nValoreLettera = (nValoreLettera + 20);
        }
        if ((cStringa.substring(i, (i + 1)).equals("O"))) {
          nValoreLettera = (nValoreLettera + 11);
        }
        if ((cStringa.substring(i, (i + 1)).equals("P"))) {
          nValoreLettera = (nValoreLettera + 3);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Q"))) {
          nValoreLettera = (nValoreLettera + 6);
        }
        if ((cStringa.substring(i, (i + 1)).equals("R"))) {
          nValoreLettera = (nValoreLettera + 8);
        }
        if ((cStringa.substring(i, (i + 1)).equals("S"))) {
          nValoreLettera = (nValoreLettera + 12);
        }
        if ((cStringa.substring(i, (i + 1)).equals("T"))) {
          nValoreLettera = (nValoreLettera + 14);
        }
        if ((cStringa.substring(i, (i + 1)).equals("U"))) {
          nValoreLettera = (nValoreLettera + 16);
        }
        if ((cStringa.substring(i, (i + 1)).equals("V"))) {
          nValoreLettera = (nValoreLettera + 10);
        }
        if ((cStringa.substring(i, (i + 1)).equals("W"))) {
          nValoreLettera = (nValoreLettera + 22);
        }
        if ((cStringa.substring(i, (i + 1)).equals("X"))) {
          nValoreLettera = (nValoreLettera + 25);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Y"))) {
          nValoreLettera = (nValoreLettera + 24);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Z"))) {
          nValoreLettera = (nValoreLettera + 23);
        }
      }
      

      // se il valore � pari
      if (i % 2 != 0) {
        if ((cStringa.substring(i, (i + 1)).equals("A")) || (cStringa.substring(i, (i + 1)).equals("0"))) {
          nValoreLettera = (nValoreLettera + 0);
        }
        if ((cStringa.substring(i, (i + 1)).equals("B")) || (cStringa.substring(i, (i + 1)).equals("1"))) {
          nValoreLettera = (nValoreLettera + 1);
        }
        if ((cStringa.substring(i, (i + 1)).equals("C")) || (cStringa.substring(i, (i + 1)).equals("2"))) {
          nValoreLettera = (nValoreLettera + 2);
        }
        if ((cStringa.substring(i, (i + 1)).equals("D")) || (cStringa.substring(i, (i + 1)).equals("3"))) {
          nValoreLettera = (nValoreLettera + 3);
        }
        if ((cStringa.substring(i, (i + 1)).equals("E")) || (cStringa.substring(i, (i + 1)).equals("4"))) {
          nValoreLettera = (nValoreLettera + 4);
        }
        if ((cStringa.substring(i, (i + 1)).equals("F")) || (cStringa.substring(i, (i + 1)).equals("5"))) {
          nValoreLettera = (nValoreLettera + 5);
        }
        if ((cStringa.substring(i, (i + 1)).equals("G")) || (cStringa.substring(i, (i + 1)).equals("6"))) {
          nValoreLettera = (nValoreLettera + 6);
        }
        if ((cStringa.substring(i, (i + 1)).equals("H")) || (cStringa.substring(i, (i + 1)).equals("7"))) {
          nValoreLettera = (nValoreLettera + 7);
        }
        if ((cStringa.substring(i, (i + 1)).equals("I")) || (cStringa.substring(i, (i + 1)).equals("8"))) {
          nValoreLettera = (nValoreLettera + 8);
        }
        if ((cStringa.substring(i, (i + 1)).equals("J")) || (cStringa.substring(i, (i + 1)).equals("9"))) {
          nValoreLettera = (nValoreLettera + 9);
        }
        if ((cStringa.substring(i, (i + 1)).equals("K"))) {
          nValoreLettera = (nValoreLettera + 10);
        }
        if ((cStringa.substring(i, (i + 1)).equals("L"))) {
          nValoreLettera = (nValoreLettera + 11);
        }
        if ((cStringa.substring(i, (i + 1)).equals("M"))) {
          nValoreLettera = (nValoreLettera + 12);
        }
        if ((cStringa.substring(i, (i + 1)).equals("N"))) {
          nValoreLettera = (nValoreLettera + 13);
        }
        if ((cStringa.substring(i, (i + 1)).equals("O"))) {
          nValoreLettera = (nValoreLettera + 14);
        }
        if ((cStringa.substring(i, (i + 1)).equals("P"))) {
          nValoreLettera = (nValoreLettera + 15);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Q"))) {
          nValoreLettera = (nValoreLettera + 16);
        }
        if ((cStringa.substring(i, (i + 1)).equals("R"))) {
          nValoreLettera = (nValoreLettera + 17);
        }
        if ((cStringa.substring(i, (i + 1)).equals("S"))) {
          nValoreLettera = (nValoreLettera + 18);
        }
        if ((cStringa.substring(i, (i + 1)).equals("T"))) {
          nValoreLettera = (nValoreLettera + 19);
        }
        if ((cStringa.substring(i, (i + 1)).equals("U"))) {
          nValoreLettera = (nValoreLettera + 20);
        }
        if ((cStringa.substring(i, (i + 1)).equals("V"))) {
          nValoreLettera = (nValoreLettera + 21);
        }
        if ((cStringa.substring(i, (i + 1)).equals("W"))) {
          nValoreLettera = (nValoreLettera + 22);
        }
        if ((cStringa.substring(i, (i + 1)).equals("X"))) {
          nValoreLettera = (nValoreLettera + 23);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Y"))) {
          nValoreLettera = (nValoreLettera + 24);
        }
        if ((cStringa.substring(i, (i + 1)).equals("Z"))) {
          nValoreLettera = (nValoreLettera + 25);
        }
      }
      

    }
    

    nValoreIntermedio = (nValoreLettera / 26);
    nValoreCodiceControllo = (26 * nValoreIntermedio);
    nCodiceControllo = (nValoreLettera - nValoreCodiceControllo);

    // il codice di controllo viene trasformato in lettera

    if (nCodiceControllo == 0) {
      cCodControllo = "A";
    }
    if (nCodiceControllo == 1) {
      cCodControllo = "B";
    }
    if (nCodiceControllo == 2) {
      cCodControllo = "C";
    }
    if (nCodiceControllo == 3) {
      cCodControllo = "D";
    }
    if (nCodiceControllo == 4) {
      cCodControllo = "E";
    }
    if (nCodiceControllo == 5) {
      cCodControllo = "F";
    }
    if (nCodiceControllo == 6) {
      cCodControllo = "G";
    }
    if (nCodiceControllo == 7) {
      cCodControllo = "H";
    }
    if (nCodiceControllo == 8) {
      cCodControllo = "I";
    }
    if (nCodiceControllo == 9) {
      cCodControllo = "J";
    }
    if (nCodiceControllo == 10) {
      cCodControllo = "K";
    }
    if (nCodiceControllo == 11) {
      cCodControllo = "L";
    }
    if (nCodiceControllo == 12) {
      cCodControllo = "M";
    }
    if (nCodiceControllo == 13) {
      cCodControllo = "N";
    }
    if (nCodiceControllo == 14) {
      cCodControllo = "O";
    }
    if (nCodiceControllo == 15) {
      cCodControllo = "P";
    }
    if (nCodiceControllo == 16) {
      cCodControllo = "Q";
    }
    if (nCodiceControllo == 17) {
      cCodControllo = "R";
    }
    if (nCodiceControllo == 18) {
      cCodControllo = "S";
    }
    if (nCodiceControllo == 19) {
      cCodControllo = "T";
    }
    if (nCodiceControllo == 20) {
      cCodControllo = "U";
    }
    if (nCodiceControllo == 21) {
      cCodControllo = "V";
    }
    if (nCodiceControllo == 22) {
      cCodControllo = "W";
    }
    if (nCodiceControllo == 23) {
      cCodControllo = "X";
    }
    if (nCodiceControllo == 24) {
      cCodControllo = "Y";
    }
    if (nCodiceControllo == 25) {
      cCodControllo = "Z";
    }

    return cCodControllo;
  }

  ;

  //==========================================================================
  // metodo per la ricerca di una citt� nel database locale
  // ritorna il codice della citt�/nazione + eventuale provincia
  public String[] genCodCitta(String cVal_01, String cVal_02) {

    String[] acCodCitta = {"", ""};
    String cCitta = cVal_01;
    String cProv = cVal_02;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    String cQuerySQL = "";

    if (cProv.equals("EE")) {
      cQuerySQL = ("SELECT CODICE FROM ESTERO WHERE DENOMINAZ LIKE '" + cCitta + "%';");
    } else {
      cQuerySQL = ("SELECT NAZIONALE,PROVINCIA FROM ITALIA WHERE COMUNE LIKE '" + cCitta + "%';");
    }

    try {
      stmt = startup.conn.createStatement();

      //System.out.println(cQuerySQL);
      rs = stmt.executeQuery(cQuerySQL);

      // si posiziona sul record
      rs.next();

      // DEBUG
      //System.out.println(rs.getString(1));
      //System.out.println(rs.getString(2));

      acCodCitta[0] = rs.getString(1);

      if (cProv.equals("EE")) {
        acCodCitta[1] = "EE";
      } else {
        acCodCitta[1] = rs.getString(2);
      }

    } catch (Exception e) {
      System.out.println("\n================================================================");
      e.printStackTrace();
      Toolkit.getDefaultToolkit().beep();
      Toolkit.getDefaultToolkit().beep();
      System.out.println("\n\nATTENZIONE: Errore nel caricamento dei driver JDBC di HSQLDB (vedi sopra).");
      System.out.println("Segnalare il problema al supporto tecnico includendo l'errore generato presso il seguente indirizzo:\n");
      System.out.println("http://sourceforge.net/tracker/?group_id=158623&atid=808573");
      System.out.println("\n\n================================================================");
      System.exit(0);
    }

    return acCodCitta;
  }

  ;
};





