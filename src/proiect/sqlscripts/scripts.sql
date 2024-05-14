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

-- Inserting data into 'Angajat'
INSERT INTO Angajat (id, nume, prenume, nationalitate, varsta, salariu) VALUES
(1, 'Xavi', 'Hernandez', 'Spain', 45, 100000),
(2, 'Carlo', 'Ancelotti', 'Italy', 67, 250000),
(3, 'Yanis', 'Popescu', 'Romania', 20, 5000),
(4, 'Bobita', 'Mirel', 'Romania', 30, 100),
(5, 'Victor', 'Iacob', 'Romania', 20, 500),
(6, 'Thomas', 'Tuchel', 'Germany', 50, 150000),
(8, 'Lionel', 'Messi', 'Argentina', 36, 250000),
(9, 'Luis', 'Suarez', 'Uruguay', 37, 175000),
(10, 'Gerard', 'Pique', 'Spain', 37, 120000),
(11, 'Marc-Andre', 'Ter Stegen', 'Germany', 32, 200000);

-- Inserting data into 'Antrenor'
INSERT INTO Antrenor (id, angajatId, aniExperienta) VALUES
(1, 1, 5),
(2, 2, 37),
(3, 3, 0),
(4, 4, 10),
(5, 6, 15);

-- Inserting data into 'Stadion'
INSERT INTO Stadion (id, nume, capacitate, locatie) VALUES
(1, 'Camp Nou', 99000, 'Barcelona'),
(2, 'Santiago Bernabeu', 76000, 'Madrid'),
(3, 'Nicolae Dobrin', 15000, 'Pitesti'),
(4, 'Gloria Buzau', 1000, 'Fierbinti'),
(5, 'Allianz Arena', 65000, 'Munchen');

-- Inserting data into 'Echipa'
INSERT INTO Echipa (id, nume, antrenorId, stadionId) VALUES
(1, 'FC Barcelona', 1, 1),
(2, 'Real Madrid', 2, 2),
(3, 'FC Arges', 3, 3),
(8, 'FC Bayern Munchen', 5, 5),
(9, 'Gloria Buzau', 4, 4);

-- Inserting data into 'Jucator'
INSERT INTO Jucator (id, angajatId, numar, pozitie, echipaId) VALUES
(1, 5, 6, 'CB', 9),
(3, 8, 10, 'CF', 1),
(4, 9, 9, 'ST', 1),
(5, 10, 3, 'CB', 1),
(6, 11, 1, 'GK', 1);

-- Inserting data into 'Sponsor'
INSERT INTO Sponsor (id, nume, tara) VALUES
(1, 'Nike', 'USA'),
(3, 'Umbro', 'USA');

-- Inserting data into 'Contract'
INSERT INTO Contract (id, echipaId, sponsorId, duration, valoare) VALUES
(1, 1, 1, 10, 1200000);

-- Inserting data into 'Meci'
INSERT INTO Meci (id, data, echipa1, echipa2, stadionId, scorEchipa1, scorEchipa2) VALUES
(2, '2024-12-12', 2, 3, 2, 1, 1),
(3, '2024-11-11', 1, 2, 1, 3, 3),
(5, '2024-01-01', 1, 2, 1, 6, 2),
(6, '2023-07-07', 3, 1, 1, 1, 6),
(8, '2022-10-10', 8, 9, 5, 3, 0);