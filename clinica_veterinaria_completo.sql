
DROP DATABASE IF EXISTS clinica_veterinaria;
CREATE DATABASE clinica_veterinaria;
USE clinica_veterinaria;

CREATE TABLE propietario (
    id_propietario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    domicilio TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raza VARCHAR(50),
    sexo VARCHAR(10),
    edad INT,
    color VARCHAR(50),
    peso DOUBLE,
    sena_particular TEXT,
    id_propietario INT,
    creado TIMESTAMP NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    eliminado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_propietario) REFERENCES propietario(id_propietario)
) ENGINE=InnoDB;

CREATE TABLE medicoveterinario (
    id_medico INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    especialidad VARCHAR(100),
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE historiaclinica (
    folio_hc INT AUTO_INCREMENT PRIMARY KEY,
    id_paciente INT NOT NULL,
    id_medico INT NOT NULL,
    fecha DATE,
    motivo_consulta TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente),
    FOREIGN KEY (id_medico) REFERENCES medicoveterinario(id_medico)
) ENGINE=InnoDB;

CREATE TABLE anamnesis (
    id_anamnesis INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE antecedente (
    id_antecedente INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE diagnosticointegral (
    id_diagnostico INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    diagnostico TEXT,
    pronostico TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE enfermedad (
    id_enfermedad INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    descripcion TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE examencomplementario (
    id_examen INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    resultado TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE examenfisico (
    id_examen INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    temperatura DECIMAL(4,1),
    peso DECIMAL(5,2),
    frecuencia_cardiaca INT,
    frecuencia_respiratoria INT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE observacion (
    id_observacion INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    fecha DATE,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE procedimiento (
    id_procedimiento INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    costo DECIMAL(10,2),
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE signofisicoanormal (
    id_signo INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    descripcion TEXT,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE tratamiento (
    id_tratamiento INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    medicamento VARCHAR(100),
    dosis VARCHAR(50),
    duracion VARCHAR(50),
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);

CREATE TABLE vacunacion (
    id_vacuna INT AUTO_INCREMENT PRIMARY KEY,
    folio_hc INT,
    vacuna VARCHAR(100),
    fecha_aplicacion DATE,
    proxima_dosis DATE,
    eliminado BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (folio_hc) REFERENCES historiaclinica(folio_hc)
);
