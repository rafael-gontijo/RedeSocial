-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Criação da tabela de mensagens
CREATE TABLE IF NOT EXISTS mensagens (
    id SERIAL PRIMARY KEY,
    remetente_id INT REFERENCES usuarios(id),
    destinatario_id INT REFERENCES usuarios(id),
    conteudo TEXT NOT NULL
);

-- Criação da tabela de amigos
CREATE TABLE IF NOT EXISTS amigos (
    usuario_id INT REFERENCES usuarios(id),
    amigo_id INT REFERENCES usuarios(id),
    PRIMARY KEY (usuario_id, amigo_id)
);
