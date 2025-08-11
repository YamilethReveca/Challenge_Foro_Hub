create table topicos(
    id bigint not null auto_increment,
    titulo VARCHAR(100) not null UNIQUE,
    mensaje VARCHAR(100) not null UNIQUE,
    fechaDeCreacion DATE not null,
    status VARCHAR(100) not null,
    autor VARCHAR(50) not null,
    curso VARCHAR(100) not null,
    respuesta VARCHAR(100) not null,

    primary key(id)

);