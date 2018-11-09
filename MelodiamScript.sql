CREATE DATABASE melodiam;
USE melodiam;

CREATE TABLE Usuario (
	id_usuario BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    login_usuario VARCHAR(20) NOT NULL UNIQUE,
    senha_usuario VARCHAR(30) NOT NULL
);

CREATE TABLE Album (
	id_spotify VARCHAR(22) NOT NULL UNIQUE, -- É String(VARCHAR) porque pegaremos o ID do BD do Spotify. EX: 6rqhFgbbKwnb9MLmUQDhG6
    id_album BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE Avaliacao_Album (
	id_avaliacao_album BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    avaliacao_album FLOAT NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_album BIGINT NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY(id_album) REFERENCES Album(id_album)
);

CREATE TABLE Comentario (
	id_comentario BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    texto VARCHAR(200) NOT NULL,
    data_hora DATETIME NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_album BIGINT NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY(id_album) REFERENCES Album(id_album)
);

CREATE TABLE Lista (
	id_lista BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    nome_lista VARCHAR(25) NOT NULL,
    descricao_lista VARCHAR(100) NOT NULL,
    id_usuario BIGINT NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Avaliacao_Lista (
	id_avaliacao_lista BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    avaliacao_lista FLOAT NOT NULL,
    id_usuario BIGINT NOT NULL,
    id_lista BIGINT NOT NULL,
    
    FOREIGN KEY(id_usuario) REFERENCES Usuario(id_usuario),
    FOREIGN KEY(id_lista) REFERENCES Lista(id_lista)
);

CREATE TABLE Album_Lista (
	id_album_lista BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
	id_album BIGINT NOT NULL, 
	id_lista BIGINT NOT NULL,
    
    FOREIGN KEY(id_album) REFERENCES Album(id_album),
    FOREIGN KEY(id_lista) REFERENCES Lista(id_lista)    
);

CREATE TABLE Amizade (
	id_amizade BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    status_amizade BOOLEAN NOT NULL,
    
    id_usuario1 BIGINT NOT NULL,
    FOREIGN KEY(id_usuario1) REFERENCES Usuario(id_usuario),
    
    id_usuario2 BIGINT NOT NULL,
    FOREIGN KEY(id_usuario2) REFERENCES Usuario(id_usuario)
);

CREATE TABLE Amizade_Lista(
	id_amizade_lista BIGINT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
    id_amizade BIGINT NOT NULL,
    id_lista BIGINT NOT NULL,
    
    FOREIGN KEY(id_amizade) REFERENCES Amizade(id_amizade),
    FOREIGN KEY(id_lista) REFERENCES Lista(id_lista)
);

