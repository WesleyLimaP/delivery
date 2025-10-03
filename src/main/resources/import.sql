INSERT INTO tb_cozinha (nome) VALUES ('Italiana');
INSERT INTO tb_cozinha (nome) VALUES ('Japonesa');
INSERT INTO tb_cozinha (nome) VALUES ('Brasileira');

-- Estados
INSERT INTO tb_estado (nome) VALUES ('São Paulo');
INSERT INTO tb_estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO tb_estado (nome) VALUES ('Minas Gerais');

-- tb_tb_cidades
INSERT INTO tb_cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Campinas', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Santos', 1);

-- Inserts para a tabela 'tb_restaurante'
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES ('Churrascaria Gaucha', 0.00, 3, '45678-123', 'Rodovia BR-101', 'KM 25', 'Próx. ao posto', 'Zona Rural', 3);
INSERT INTO tb_restaurante(nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES('Trattoria da Nonna', 5.00, 1, '12345-678', 'Rua das Flores', '100', 'Sala 2', 'Centro', 1);
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES ('Sushi Fusion', 8.50, 2, '98765-432', 'Avenida Paulista', '2000', 'Cobertura', 'Bela Vista', 2);
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES ('Pizzaria Bella Napoli', 7.50, 1, '12345-678', 'Av. das Flores', '120', 'Apto 201', 'Jardim Primavera', 1);



-- Inserts para a tabela 'tb_forma_de_pagamento'
-- A coluna 'restaurante_id' faz referência à ID dos restaurantes que inserimos acima.
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Crédito', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Pix', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Dinheiro', 2);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Débito', 3);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Pix', 4);

