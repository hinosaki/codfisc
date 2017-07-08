#!/usr/bin/env python
#-*- coding: iso8859-1 -*-

# Copyright (C) 2006-2011 - Riccardo Mattiuzzo

#    This program is free software: you can redistribute it and/or modify
#    it under the terms of the GNU General Public License as published by
#    the Free Software Foundation, either version 3 of the License, or
#    (at your option) any later version.

#    This program is distributed in the hope that it will be useful,
#    but WITHOUT ANY WARRANTY; without even the implied warranty of
#    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#    GNU General Public License for more details.

#    You should have received a copy of the GNU General Public License
#    along with this program.  If not, see <http://www.gnu.org/licenses/>.

import string


def latinToUTF8(stringa):
    return unicode(stringa, "latin-1").encode("utf-8")

file_italia_sql = open('italia.sql','w')
file_esteri_sql = open('esteri.sql','w')
file_italia_csv = open('italia.csv','r')
file_esteri_csv = open('esteri.csv','r')


elenco_italia = file_italia_csv.readlines()
elenco_esteri = file_esteri_csv.readlines()
riga = ""

for riga_corrente in elenco_italia:
  # pulisco ogni voce dal carattere \n
  riga = string.strip(riga_corrente,'\n')
  # splitto la stringa dal carattere ;
  riga = string.split(riga_corrente,";")
  # a causa degli apici per le parole accentate, HSQL accetta un apice se nella stringa SQL lo si indica come ''
  riga[3] = string.replace(riga[3],"'","''") 
  riga_file = ("INSERT INTO ITALIA(NAZIONALE, CATASTALE, PROVINCIA, COMUNE) VALUES('" + riga[0] + "','" + riga[1] + "','" + riga[2] + "','" + latinToUTF8(string.rstrip(riga[3])) + "');" )
  print riga[3]
  file_italia_sql.write(riga_file + '\n')

riga_corrente = ""
riga = ""

for riga_corrente in elenco_esteri:
  # pulisco ogni voce dal carattere \n
  riga = string.strip(riga_corrente,'\n')
  # splitto la stringa dal carattere ;
  riga = string.split(riga_corrente,";")
  riga[2] = string.replace(riga[2],"'","''") 
  riga_file = ("INSERT INTO ESTERO(CONTINENTE, SIGLA, DENOMINAZ, CODICE) VALUES('" + riga[0] + "','" + riga[1] + "','" + latinToUTF8(string.rstrip(riga[2])) + "','" + string.strip(riga[3],'\n') + "');")
  file_esteri_sql.write(riga_file + '\n')

riga_corrente = ""
riga = ""


