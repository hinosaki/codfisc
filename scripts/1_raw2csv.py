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
import sys

def latinTolatin(cStringa):
    check1 = string.replace(cStringa,"a'","à")
    check2 = string.replace(check1,"e'","è")
    check3 = string.replace(check2,"i'","ì")
    check4 = string.replace(check3,"o'","ò")
    check5 = string.replace(check4,"u'","ù")
    check6 = string.replace(check5,"^","'")
    return (check6)

def latinToUTF8(stringa):
    return unicode(stringa, "latin-1").encode("utf-8")


# il formato file sono:
# listacomuni.txt
# Istat;Comune;Provincia;Regione;Prefisso;CAP;CodFisco;Abitanti;Link
          
# ITALIA -> NAZIONALE;CATASTALE,PROVINCIA;COMUNE
# ESTERO -> CONTINENTE;SIGLA;DENOMINAZ;CODICE

file_italia_csv = open('italia.csv','w')
file_esteri_csv = open('esteri.csv','w')
file_italia = open('listacomuni.txt','r')
file_esteri = open('esteri.txt','r')

elenco_italia = file_italia.readlines()
elenco_esteri = file_esteri.readlines()
riga = ""

for riga_corrente in elenco_italia:
  riga = string.split(riga_corrente,";")
  riga_file = (riga[6] + ';--;' + riga[2] + ';' + riga[1])
  print "Elaborazione ---> " + riga[1]
  file_italia_csv.write(riga_file + '\n')

for riga_corrente in elenco_esteri:
  riga = string.split(riga_corrente,";")
  riga_file = (riga[0] + ';' + riga[1] + ';' + riga[2] + ';' + riga[3])
  riga_file = riga_file.replace("\r\n","")
  file_esteri_csv.write(riga_file + '\n')
  
      
      
