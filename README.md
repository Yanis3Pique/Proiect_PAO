## CLASE:

### **Angajat**
- **Descriere**: Clasă abstractă care reprezintă un angajat dintr-o organizație sportivă.
#### Atribute:
  - id
  - nume
  - prenume
  - nationalitate
  - varsta
  - salariu
<hr/>

### **Antrenor**
- **Descriere**: Clasă derivată din `Angajat`, care reprezintă un antrenor al unei echipe.
#### Atribute:
  - angajatId
  - aniExperienta
<hr/>

### **Contract**
- **Descriere**: Clasă care leagă o echipă de un sponsor.
#### Atribute:
  - id
  - team
  - sponsor
  - durationYears
  - sumMoney
<hr/>

### **Echipa**
- **Descriere**: Clasă care reprezintă o echipă sportivă.
#### Atribute:
  - id
  - nume
  - antrenor
  - stadion
<hr/>

### **Jucator**
- **Descriere**: Clasă derivată din `Angajat`, care reprezintă un jucător dintr-o echipă sportivă.
#### Atribute:  
  - angajatId
  - id_echipa
  - pozitie
  - numarTricou
<hr/>

### **Meci**
- **Descriere**: Clasă care reprezintă un meci între două echipe.
#### Atribute:
  - id
  - echipa1
  - echipa2
  - data
  - scor1
  - scor2
  - stadion
<hr/>

### **Sponsor**
- **Descriere**: Clasă care reprezintă un sponsor al unei echipe sportive.
#### Atribute:
  - id
  - name
  - country
<hr/>

### **Stadion**
- **Descriere**: Clasă care reprezintă un stadion unde se desfășoară meciuri sportive.
#### Atribute:
  - id
  - nume
  - capacitate
  - locatie
<hr/>

## FUNCȚIONALITĂȚI:
1. Creează echipă
2. Vizualizează echipă
3. Actualizează echipă
4. Șterge echipă
5. Vezi jucătorii echipei
6. Programează meci
7. Vizualizează meci
8. Actualizează scor meci
9. Șterge meci
10. Angajează angajat
11. Vizualizează angajat
12. Actualizează angajat
13. Concediază angajat
14. Transferă jucător la o nouă echipă
15. Creează stadion
16. Vizualizează stadion
17. Actualizează stadion
18. Șterge stadion
19. Creează sponsor
20. Vizualizează sponsor
21. Actualizează sponsor
22. Șterge sponsor
23. Semnează contract
24. Vizualizează contract
25. Actualizează contract
26. Reziliază contract
27. Vizualizează clasament campionat
