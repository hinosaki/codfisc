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

import java.awt.Color;
import java.sql.*;

/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */
public class startup {

  public static Connection conn;
  // effettuo il controllo sulla dimensione degli array
  static int[] aiDimensione = new int[2];
  // colore selezionato dal sito http://www.colorpicker.com/
  //final static Color coloreSelezione = new Color(246,252,177);
  
  public static void main(String[] args) {

    
    String sQuerySQL = "";

    try {
      Class.forName("org.h2.Driver");
      // la directory db/codfisc deve trovarsi all'interno della directory principale
      // del programma e NON nella directory /src dei sorgenti
      conn = DriverManager.getConnection("jdbc:h2:file:db/codfisc", "sa", "");

    } catch (Exception e) {
      System.out.println("ERRORE Caricamento Driver");
      System.exit(0);
    }

    try {

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM ITALIA;");
      // mi posiziono sul primo record
      rs.next();

      aiDimensione[0] = Integer.parseInt(rs.getString(1));
      // ###DEBUG
      //System.out.println(rs.getString(1));
      //System.exit(0);

      //System.out.println(rs.getString(1));


      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT COUNT(*) FROM ESTERO;");
      // mi posiziono sul primo record
      rs.next();
      aiDimensione[1] = Integer.parseInt(rs.getString(1));

    } catch (Exception e) {
      System.out.println("ERRORE Ricezione Dati =====> " + e);

      System.exit(0);

    }

    MainForm frmMain = new MainForm();
    frmMain.setVisible(true);
  }
}