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
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */

package codfisc2;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */
public class Supporto {

  /*
   * viene passato il contenuto completo della combobox e viene
   * ritornato il valore contenuto tra parentesi
   *
   **/
  public static String PulisciProvincia(String sProv) {
    String sProvincia = "";

    return sProvincia;
  }

  public static String NoAccento(String sParolaIN) {
    String sParolaOUT = "";

    sParolaOUT = sParolaIN.replaceAll("à", "a");
    sParolaOUT = sParolaIN.replaceAll("è", "e");
    sParolaOUT = sParolaIN.replaceAll("é", "e");
    sParolaOUT = sParolaIN.replaceAll("ì", "i");
    sParolaOUT = sParolaIN.replaceAll("ò", "o");
    sParolaOUT = sParolaIN.replaceAll("ù", "u");

    return sParolaOUT;
  }


public static boolean isValidDate(String inDate) {

    if (inDate == null)
      return false;

    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");

    dateFormat.setLenient(false);

    try {
      //parse the inDate parameter
      dateFormat.parse(inDate.trim());
    }
    catch (ParseException pe) {
      return false;
    }

    return true;
  }


/*
 * SplitCittaProv
 *
 * accetta la stringa:
 *
 * XXXXX (YY)
 *
 * xxxxx = citta'
 * YY = prov
 *
 * ritorna in array 2 elementi
 *
 * elemento 0 ---> citta'
 * elemento 1 ---> provincia
 *
 * */

public static String[] SplitCittaProv(String sCombo) {
  String[] asRisultato = new String[2];
  int nDimensione;
  int nIndice;

  nDimensione = sCombo.trim().length();
  /*
  * la provincia comincia a fine_stringa - 3
  * ricordarsi che si parte dal valore 0 per il calcolo della substring
  * es.: stringa = JESOLO (VE)
  *
  * 0 1 2 3 4 5 6 7 8 9 10   ---> LUNGHEZZA 11
  * J E S O L O   ( V E )
  *
  */
  
  nIndice = sCombo.indexOf("(");
  
  asRisultato[0] = sCombo.substring(0,(nIndice - 1));
  // indice di inizio e di fine della substring
  asRisultato[1] = sCombo.substring((nIndice + 1),(nDimensione -1) );

  // ###DEBUG
  //System.out.println(Integer.toString(nDimensione));
  //System.out.println(Integer.toString(nIndice));
  //System.out.println(sCombo);
  //System.out.println(asRisultato[0]);
  //System.out.println(asRisultato[1]);
  
  return asRisultato;

}



}
