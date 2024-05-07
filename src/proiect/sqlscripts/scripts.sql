DROP TABLE IF EXISTS MECI, CONTRACT, SPONSOR, JUCATOR, ECHIPA, STADION, ANTRENOR, ANGAJAT CASCADE;

-- Table creation for 'Angajat'
CREATE TABLE Angajat (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nume VARCHAR(100),
            prenume VARCHAR(100),
            nationalitate VARCHAR(100),
            varsta INT,
            salariu DOUBLE
);

-- Table creation for 'Antrenor'
CREATE TABLE Antrenor (
            id INT AUTO_INCREMENT PRIMARY KEY,
            angajatId INT NOT NULL,
            aniExperienta INT,
            FOREIGN KEY (angajatId) REFERENCES Angajat(id)
);

-- Table creation for 'Stadion'
CREATE TABLE Stadion (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nume VARCHAR(100),
            capacitate INT,
            locatie VARCHAR(100)
);

-- Table creation for 'Echipa'
CREATE TABLE Echipa (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nume VARCHAR(100),
            antrenorId INT,
            stadionId INT,
            FOREIGN KEY (antrenorId) REFERENCES Antrenor(id),
            FOREIGN KEY (stadionId) REFERENCES Stadion(id)
);

-- Table creation for 'Jucator'
CREATE TABLE Jucator (
            id INT AUTO_INCREMENT PRIMARY KEY,
            angajatId INT NOT NULL,
            numar INT,
            pozitie VARCHAR(100),
            echipaId INT,
            FOREIGN KEY (angajatId) REFERENCES Angajat(id),
            FOREIGN KEY (echipaId) REFERENCES Echipa(id)
);


-- Table creation for 'Sponsor'
CREATE TABLE Sponsor (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nume VARCHAR(100),
            tara VARCHAR(100)
);

-- Table creation for 'Contract'
CREATE TABLE Contract (
            id INT AUTO_INCREMENT PRIMARY KEY,
            echipaId INT,
            sponsorId INT,
            duration INT,
            valoare DOUBLE,
            FOREIGN KEY (echipaId) REFERENCES Echipa(id),
            FOREIGN KEY (sponsorId) REFERENCES Sponsor(id)
);


-- Table creation for 'Meci'
CREATE TABLE Meci (
            id INT AUTO_INCREMENT PRIMARY KEY,
            data DATE,
            echipa1 INT,
            echipa2 INT,
            stadionId INT,
            scorEchipa1 INT,
            scorEchipa2 INT,
            FOREIGN KEY (echipa1) REFERENCES Echipa(id),
            FOREIGN KEY (echipa2) REFERENCES Echipa(id),
            FOREIGN KEY (stadionId) REFERENCES Stadion(id)
);