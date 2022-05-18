drop table jogador;
 create table zappts.jogador (
	id INT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(60) NOT NULL,
    email VARCHAR(30) NOT NULL
);

drop table idioma;
create table zappts.idioma (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL
);

drop table carta;
create table zappts.carta (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
	edition VARCHAR(50) NOT NULL,
    id_idioma INT NOT NULL,
    foil BOOLEAN NOT NULL,
    value FLOAT NOT NULL,
    FOREIGN KEY (id_idioma) REFERENCES idioma(id)
);

drop table deck;
create table zappts.deck (
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_jogador INT NOT NULL,
    id_carta INT NOT NULL,
	qtd INT NOT NULL,
    FOREIGN KEY (id_carta) REFERENCES carta(id),
    FOREIGN KEY (id_jogador) REFERENCES jogador(id)
);

drop table list;
create table zappts.list (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
	id_jogador INT NOT NULL,
    FOREIGN KEY (id_jogador) REFERENCES jogador(id)
);

drop table listCollection;
create table zappts.list_collection (
	id INT AUTO_INCREMENT PRIMARY KEY,
    id_carta INT NOT NULL,
    qtd_carta INT NOT NULL,
    id_list INT NOT NULL,
    FOREIGN KEY (id_carta) REFERENCES carta(id),
    FOREIGN KEY (id_list) REFERENCES list(id)
)

