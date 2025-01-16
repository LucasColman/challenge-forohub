CREATE TABLE usuarios (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          password VARCHAR(255) NOT NULL

);

CREATE TABLE cursos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL,
                        categoria VARCHAR(255) NOT NULL
);

create table topicos (
                         id BIGINT not null auto_increment primary key,
                         titulo varchar(255) not null,
                         mensaje varchar(255) not null unique,
                         fechaCreacion DATETIME not null DEFAULT CURRENT_TIMESTAMP,
                         status enum('ABIERTO', 'CERRADO') not null,
                         autor BIGINT NOT NULL,
                         respuestas BIGINT DEFAULT 0,
                         curso bigint not null,

                         FOREIGN KEY (curso) REFERENCES cursos(id),
                         FOREIGN KEY (autor) REFERENCES usuarios(id)

);

CREATE TABLE respuestas (
                            id BIGINT AUTO_INCREMENT PRIMARY KEY,
                            mensaje TEXT NOT NULL,
                            fechaCreacion DATETIME NOT NULL,
                            autor BIGINT NOT NULL,
                            solucion BOOLEAN DEFAULT FALSE,
                            topico BIGINT NOT NULL,
                            FOREIGN KEY (topico) REFERENCES topicos(id),
                            FOREIGN KEY (autor) REFERENCES usuarios(id)
);