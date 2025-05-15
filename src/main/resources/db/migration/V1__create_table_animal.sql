CREATE TABLE animal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    tipo_animal VARCHAR(50),
    raca VARCHAR(100),
    idade INT,
    status_adocao VARCHAR(50),
    imagem VARCHAR(255),
    descricao TEXT,
    ativo Boolean
);

INSERT INTO animal (nome, tipo_animal, raca, idade, status_adocao, imagem, descricao, ativo) VALUES
('Luna', 'Cachorro', 'Labrador', 3, 'DISPONIVEL', 'https://example.com/imagens/luna.jpg', 'Cachorra dócil e brincalhona.', true),
('Milo', 'Gato', 'Siamês', 2, 'ADOTADO', 'https://example.com/imagens/milo.jpg', 'Gato calmo, adora colo e carinho.', true),
('Thor', 'Cachorro', 'Husky Siberiano', 4, 'DISPONIVEL', 'https://example.com/imagens/thor.jpg', 'Husky enérgico, ideal para famílias ativas.', true),
('Bella', 'Gato', 'Persa', 5, 'DISPONIVEL', 'https://example.com/imagens/bella.jpg', 'Gata tranquila e silenciosa.', true),
('Max', 'Cachorro', 'Beagle', 3, 'ADOTADO', 'https://example.com/imagens/max.jpg', 'Beagle curioso e amigável.', true),
('Nina', 'Cachorro', 'Poodle', 6, 'DISPONIVEL', 'https://example.com/imagens/nina.jpg', 'Poodle muito inteligente e dócil.', true),
('Simba', 'Gato', 'Maine Coon', 2, 'DISPONIVEL', 'https://example.com/imagens/simba.jpg', 'Grande e carinhoso, ideal para lares com crianças.', false),
('Bob', 'Cachorro', 'Golden Retriever', 4, 'ADOTADO', 'https://example.com/imagens/bob.jpg', 'Muito companheiro e obediente.', true),
('Lili', 'Gato', 'Angorá', 1, 'DISPONIVEL', 'https://example.com/imagens/lili.jpg', 'Pequena e muito brincalhona.', false),
('Toby', 'Cachorro', 'Bulldog', 5, 'ADOTADO', 'https://example.com/imagens/toby.jpg', 'Bulldog calmo e carinhoso.', true);