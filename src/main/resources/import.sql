INSERT INTO tb_cozinha (nome) VALUES ('Italiana');
INSERT INTO tb_cozinha (nome) VALUES ('Japonesa');
INSERT INTO tb_cozinha (nome) VALUES ('Brasileira');

-- Inserts para a tabela 'tb_restaurante'
-- A ID de cozinha (cozinha_id) corresponde aos registros que acabamos de inserir.
-- A ID da tabela 'tb_restaurante' será gerada automaticamente.
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id) VALUES ('Trattoria da Nonna', 5.00, 1);
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id) VALUES ('Sushi Fusion', 8.50, 2);
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id) VALUES ('Churrascaria Gaúcha', 0.00, 3);

-- Inserts para a tabela 'tb_forma_de_pagamento'
-- A coluna 'restaurante_id' faz referência à ID dos restaurantes que inserimos acima.
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Crédito', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Pix', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Dinheiro', 2);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Débito', 2);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Pix', 2);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Crédito', 3);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Dinheiro', 3);


-- Estados
INSERT INTO tb_estado (nome) VALUES ('São Paulo');
INSERT INTO tb_estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO tb_estado (nome) VALUES ('Minas Gerais');

-- tb_tb_cidades
INSERT INTO tb_cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Campinas', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Santos', 1);

INSERT INTO tb_cidade (nome, estado_id) VALUES ('Rio de Janeiro', 2);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Niterói', 2);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Petrópolis', 2);

INSERT INTO tb_cidade (nome, estado_id) VALUES ('Belo Horizonte', 3);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Uberlândia', 3);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Juiz de Fora', 3);
