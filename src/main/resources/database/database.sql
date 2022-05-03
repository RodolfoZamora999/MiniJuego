-- Nota: Esta base de datos está mal diseñada, pero se mantiene intacta por el momento para no
-- romper el proyecto. (No tenía mucha experiencia en aquel entonces cuando la diseñe)

CREATE TABLE IF NOT EXISTS registro (
    NOMBRE_USUARIO VARCHAR (30) PRIMARY KEY,
    NOMBRE VARCHAR (30),
    APELLIDO_P VARCHAR (30),
    APELLIDO_M VARCHAR (30),
    CORREO VARCHAR (50),
    EDAD INTEGER,
    PAIS VARCHAR (20),
    CONTRASENA VARCHAR (30)
);

CREATE TABLE IF NOT EXISTS datos_jugador (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    RECORD INTEGER,
    TIEMPO VARCHAR(45),
    JUGADOR VARCHAR(30),
    FOREIGN KEY (JUGADOR) REFERENCES registro(NOMBRE_USUARIO)
);