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

/**
 * @author Riccardo MATTIUZZO - <hinosaki@hinosaki.org>
 * @version 2.2
 */

// информацја је овде:
// http://www.roseindia.net/java/beginners/OSInformation.shtml
public class Sistema {

  public String getOS() {
    String sNameOS = System.getProperty("os.name");
    String sArchOS = System.getProperty("os.arch");
    String sVerOS = System.getProperty("os.version");
    String sOS = (sNameOS+ " " +sArchOS + " " + sVerOS);
    return sOS;
  }

  public String getUser() {
    String sUserName = System.getProperty("user.name");
    return sUserName;
  }

  public String getJREVersion() {
    String sJREVer = System.getProperty("java.version");
    return sJREVer;
  }

}
