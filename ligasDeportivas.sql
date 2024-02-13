DROP DATABASE IF EXISTS ligas_deportivas;

CREATE DATABASE ligas_deportivas;

USE ligas_deportivas;

CREATE TABLE Ligas (
    id_liga INT AUTO_INCREMENT PRIMARY KEY,
    nombre_liga VARCHAR(50),
    fecha_inicio VARCHAR(50),
    fecha_fin VARCHAR(50)
);

-- Crear la tabla Equipos
CREATE TABLE Equipos (
    id_equipo INT AUTO_INCREMENT PRIMARY KEY,
    nombre_equipo VARCHAR(50),
    ciudad VARCHAR(50),
    id_liga INT,
    FOREIGN KEY (id_liga) REFERENCES Ligas(id_liga)
);

-- Crear la tabla Partido
CREATE TABLE Partido (
    id_partido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_partido VARCHAR(50),
    goles_equipo_local INT,
    goles_equipo_visitante INT,
    id_equipo_local INT,
    id_equipo_visitante INT,
    id_liga INT,
    FOREIGN KEY (id_equipo_local) REFERENCES Equipos(id_equipo),
    FOREIGN KEY (id_equipo_visitante) REFERENCES Equipos(id_equipo),
    FOREIGN KEY (id_liga) REFERENCES Ligas(id_liga)
);