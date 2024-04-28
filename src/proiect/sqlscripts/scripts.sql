-- Abstract, will be used as a base for Jucator and Antrenor
CREATE TABLE Angajat (
            id INT PRIMARY KEY,
            nume VARCHAR(255),
            prenume VARCHAR(255),
            nationalitate VARCHAR(255),
            varsta INT,
            salariu DOUBLE
);


CREATE TABLE Antrenor (
            id INT PRIMARY KEY,
            aniExperienta INT,
            FOREIGN KEY (id) REFERENCES Angajat(id)
);


CREATE TABLE Jucator (
            id INT PRIMARY KEY,
            pozitie VARCHAR(255),
            numarTricou INT,
            FOREIGN KEY (id) REFERENCES Angajat(id)
);


CREATE TABLE Sponsor (
            id INT PRIMARY KEY,
            name VARCHAR(255),
            country VARCHAR(255)
);


CREATE TABLE Stadion (
            id INT PRIMARY KEY,
            nume VARCHAR(255),
            capacitate INT,
            locatie VARCHAR(255)
);


CREATE TABLE Echipa (
            id INT PRIMARY KEY,
            nume VARCHAR(255),
            antrenor_id INT,
            stadion_id INT,
            FOREIGN KEY (antrenor_id) REFERENCES Antrenor(id),
            FOREIGN KEY (stadion_id) REFERENCES Stadion(id)
);


CREATE TABLE Contract (
            id INT PRIMARY KEY,
            team_id INT,
            sponsor_id INT,
            durationYears INT,
            sumMoney DOUBLE,
            FOREIGN KEY (team_id) REFERENCES Echipa(id),
            FOREIGN KEY (sponsor_id) REFERENCES Sponsor(id)
);


CREATE TABLE Meci (
            id INT PRIMARY KEY,
            echipa1_id INT,
            echipa2_id INT,
            data VARCHAR(255),
            scor1 INT,
            scor2 INT,
            stadion_id INT,
            FOREIGN KEY (echipa1_id) REFERENCES Echipa(id),
            FOREIGN KEY (echipa2_id) REFERENCES Echipa(id),
            FOREIGN KEY (stadion_id) REFERENCES Stadion(id)
);