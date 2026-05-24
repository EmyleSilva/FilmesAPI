CREATE TABLE diretor (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    data_nascimento DATE,
    nacionalidade VARCHAR
);

CREATE TABLE filme (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    data_lancamento DATE NOT NULL,
    diretor_id INT NOT NULL REFERENCES diretor(id)
);
