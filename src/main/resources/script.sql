-- Tabela Aluno
CREATE TABLE aluno (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
matricula VARCHAR(20) NOT NULL,
data_nascimento DATE NOT NULL
);
-- Tabela Professor
CREATE TABLE professor (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
email VARCHAR(100) NOT NULL UNIQUE,
disciplina VARCHAR(50) NOT NULL
);
-- Tabela Curso
CREATE TABLE curso (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
codigo VARCHAR(20) NOT NULL UNIQUE
);
-- Tabela Turma
CREATE TABLE turma (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(50) NOT NULL,
curso_id BIGINT NOT NULL,
professor_id BIGINT NOT NULL,
FOREIGN KEY (curso_id) REFERENCES curso(id),
FOREIGN KEY (professor_id) REFERENCES professor(id)
);
-- Tabela Turma_Aluno (N:N)
CREATE TABLE turma_aluno (

turma_id BIGINT NOT NULL,
aluno_id BIGINT NOT NULL,
PRIMARY KEY (turma_id, aluno_id),
FOREIGN KEY (turma_id) REFERENCES turma(id),
FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);
-- Tabela Aula
CREATE TABLE aula (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
turma_id BIGINT NOT NULL,
data_hora DATETIME NOT NULL,
assunto VARCHAR(100) NOT NULL,
FOREIGN KEY (turma_id) REFERENCES turma(id)
);
-- Tabela Nota
CREATE TABLE nota (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
aluno_id BIGINT NOT NULL,
aula_id BIGINT NOT NULL,
valor DOUBLE NOT NULL CHECK(valor >= 0 AND valor <= 10),
FOREIGN KEY (aluno_id) REFERENCES aluno(id),
FOREIGN KEY (aula_id) REFERENCES aula(id)
);