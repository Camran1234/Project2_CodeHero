CREATE SCHEMA IF NOT EXISTS CODEHERO;
USE CODEHERO;

CREATE TABLE IF NOT EXISTS HOSPITAL (
Registro_Propiedad VARCHAR(45) NOT NULL,
Nombre VARCHAR(50) NOT NULL,
Direccion VARCHAR(50) NOT NULL,
PRIMARY KEY(Registro_Propiedad)
);

CREATE TABLE IF NOT EXISTS PACIENTE (
Codigo VARCHAR(45) NOT NULL,
Password VARCHAR(100) NOT NULL,
Nombre VARCHAR(100) NOT NULL,
Sexo VARCHAR(25) NOT NULL,
Fecha_Nacimiento Varchar(12) NOT NULL,
DPI Varchar(15) NOT NULL,
Telefono Varchar(35) NOT NULL,
Peso Double NOT NULL,
Tipo_Sangre VARCHAR(10) NOT NULL,
Correo_Electronico Varchar(100) NOT NULL,
Codigo_Hospital Varchar(45),
PRIMARY KEY (CODIGO),
FOREIGN KEY(Codigo_Hospital) REFERENCES HOSPITAL(Registro_Propiedad)
);

CREATE TABLE IF NOT EXISTS MEDICO (
Codigo Varchar(45) NOT NULL,
Nombre Varchar(100) NOT NULL,
Password Varchar(100) NOT NULL,
Numero_Colegiado Varchar(30) NOT NULL,
DPI VARCHAR(15) NOT NULL,
Telefono VARCHAR (35) NOT NULL,
Correo_Electronico VARCHAR (100) NOT NULL,
Horario_Entrada Varchar(5) NOT NULL,
Horario_Salida Varchar(5) NOT NULL,
Fecha_Inicio Varchar(12) NOT NULL,
Codigo_Hospital Varchar(45),
PRIMARY KEY (Codigo),
FOREIGN KEY (Codigo_Hospital) REFERENCES HOSPITAL(Registro_Propiedad)
);

CREATE TABLE IF NOT EXISTS TITULO (
No_Registro INT NOT NULL AUTO_INCREMENT,
Codigo_Medico Varchar(45) NOT NULL,
Especialidad Varchar(150) NOT NULL,
PRIMARY KEY(No_Registro),
FOREIGN KEY(Codigo_Medico) REFERENCES MEDICO(Codigo)
);


CREATE TABLE IF NOT EXISTS LABORATORISTA (
Codigo Varchar(45) NOT NULL,
Nombre Varchar(100) NOT NULL,
Password Varchar(100) NOT NULL,
Numero_Registro_Ministerio_Salud Varchar(35) NOT NULL,
DPI Varchar(15) NOT NULL,
Telefono Varchar(35) NOT NULL,
Examen_trabajo Varchar(200) NOT NULL,
Dias_Trabajo Varchar(200) NOT NULL,
Correo_Electronico Varchar(200) NOT NULL,
Fecha_Inicio Varchar(12) NOT NULL,
Codigo_Hospital Varchar(45),
PRIMARY KEY (Codigo),
FOREIGN KEY (Codigo_Hospital) REFERENCES HOSPITAL(Registro_Propiedad)
);

CREATE TABLE IF NOT EXISTS ADMINISTRADOR (
Codigo Varchar(45) NOT NULL,
Nombre Varchar(100) NOT NULL,
DPI Varchar(15) NOT NULL,
Password Varchar(100) NOT NULL,
Codigo_Hospital Varchar(45),
PRIMARY KEY (Codigo),
FOREIGN KEY(Codigo_Hospital) REFERENCES HOSPITAL(Registro_Propiedad)
);

CREATE TABLE IF NOT EXISTS CONSULTA (
Codigo INT NOT NULL AUTO_INCREMENT,
Tipo Varchar(100) NOT NULL,
Costo Double NOT NULL,
PRIMARY KEY(Codigo)
);

CREATE TABLE IF NOT EXISTS EXAMENES_LABORATORIO (
Codigo Varchar(45) NOT NULL,
Nombre Varchar(200) NOT NULL,
Requerimiento_Orden BOOLEAN NOT NULL,
Descripcion TEXT NOT NULL,
Costo Double NOT NULL,
Informe VARCHAR(50) NOT NULL,
PRIMARY KEY(Codigo)
);

CREATE TABLE IF NOT EXISTS REGISTRO_EXAMEN (
No_Registro Varchar(45) NOT NULL,
Orden_Medico Varchar(500) NOT NULL,
Hora VARCHAR(5) NOT NULL,
Fecha VARCHAR(15) NOT NULL,
Codigo_Paciente VARCHAR(45) NOT NULL,
Codigo_Examen Varchar(45) NOT NULL,
Codigo_Medico Varchar(45) NOT NULl,
PRIMARY KEY(No_Registro),
FOREIGN KEY (Codigo_Paciente) REFERENCES PACIENTE(Codigo),
FOREIGN KEY (Codigo_Examen) REFERENCES EXAMENES_LABORATORIO(Codigo),
FOREIGN KEY (Codigo_Medico) REFERENCES MEDICO(Codigo)
);


CREATE TABLE IF NOT EXISTS RESULTADO (
No_Registro VARCHAR(45) NOT NULL,
Orden VARCHAR(1000),
Informe VARCHAR(1000) NOT NULL,
Fecha Varchar(15) NOT NULL,
Hora VARCHAR(5) NOT NULL,
Paciente VARCHAR(45) NOT NULL,
Medico Varchar(45),
Examen Varchar(45) NOT NULL,
Laboratorista VARCHAR(45) NOT NULL,		
PRIMARY KEY (No_Registro),
FOREIGN KEY (Paciente) REFERENCES PACIENTE(Codigo),
FOREIGN KEY (Medico) REFERENCES MEDICO(Codigo),
FOREIGN KEY (Laboratorista) REFERENCES LABORATORISTA(Codigo),
FOREIGN KEY (Examen) REFERENCES EXAMENES_LABORATORIO(Codigo)
);

CREATE TABLE IF NOT EXISTS INFORME_MEDICO (
No_Registro Varchar(45) NOT NULL,
Paciente VARCHAR(45) NOT NULL,
Medico VARCHAR(45) NOT NULL,
Informe TEXT NOT NULL,
Fecha Varchar(12) NOT NULL,
Hora VARCHAR(5) NOT NULL,
PRIMARY KEY(No_Registro),
FOREIGN KEY (Paciente) REFERENCES PACIENTE(Codigo),
FOREIGN KEY (Medico) REFERENCES MEDICO(Codigo)
);


CREATE TABLE IF NOT EXISTS REGISTRO_CITAS (
No_Registro Varchar(45) NOT NULL,
Fecha_Cita Varchar(12) NOT NULL,
Hora_Cita Varchar(5) NOT NULL,
Cita_Realizada BOOLEAN,
Codigo_Paciente Varchar(45) NOT NULL,
Codigo_Medico Varchar(45) NOT NULL,
Registro_Titulo TEXT,
Registro_Consulta INT,
Informe_Cita Varchar(45),
PRIMARY KEY (No_Registro),
FOREIGN KEY (Codigo_Paciente) REFERENCES PACIENTE (Codigo),
FOREIGN KEY (Codigo_Medico) REFERENCES MEDICO (Codigo),
FOREIGN KEY (Registro_Consulta) REFERENCES CONSULTA (Codigo),
FOREIGN KEY (Informe_Cita) REFERENCES INFORME_MEDICO (No_Registro)
);


