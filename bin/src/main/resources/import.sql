-- ========================
-- COZINHAS
-- ========================
INSERT INTO tb_cozinha (nome) VALUES ('Italiana');
INSERT INTO tb_cozinha (nome) VALUES ('Japonesa');
INSERT INTO tb_cozinha (nome) VALUES ('Brasileira');



-- ========================
-- ESTADOS
-- ========================
INSERT INTO tb_estado (nome) VALUES ('Rio de Janeiro');
INSERT INTO tb_estado (nome) VALUES ('São Paulo');

-- ========================
-- CIDADES
-- ========================
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Rio de Janeiro', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Niterói', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('São Paulo', 2);

-- ========================
-- RESTAURANTES
-- ========================
INSERT INTO tb_restaurante (nome, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES ('Restaurante Sabor Carioca', 5.00, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1), ('Churrascaria Boi na Brasa', 8.50, 2, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2), ('Pizzaria Bella Napoli', 6.75, 3, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- ========================
-- FORMAS DE PAGAMENTO
-- ========================
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Dinheiro', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Crédito', 1);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Pix', 2);
INSERT INTO tb_forma_de_pagamento (descricao, restaurante_id) VALUES ('Cartão de Débito', 2);

-- ========================
-- PRODUTOS
-- ========================
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pizza Margherita', 'Pizza com tomate e manjericão', 35.00, true, 1);

INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Lasanha Bolonhesa', 'Lasanha tradicional com molho de carne', 42.00, true, 1);

INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Sushi Combo', '12 peças variadas', 60.00, true, 2);

INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Picanha na Brasa', 'Acompanha arroz, farofa e vinagrete', 70.00, true, 2);

-- ========================
-- PERMISSÕES
-- ========================
INSERT INTO tb_permissao (descricao) VALUES ('Permite consultar restaurantes');
INSERT INTO tb_permissao (descricao) VALUES ('Permite gerenciar pedidos');
INSERT INTO tb_permissao (descricao) VALUES ('Permite gerenciar usuários');

-- ========================
-- GRUPOS
-- ========================
INSERT INTO tb_grupo (nome) VALUES ('Administrador');
INSERT INTO tb_grupo (nome) VALUES ('Gerente');
INSERT INTO tb_grupo (nome) VALUES ('Cliente');

-- ========================
-- GRUPO_PERMISSAO (relacionamento N:N)
-- ========================
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1, 3);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (2, 1);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (2, 2);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (3, 1);

-- ========================
-- USUÁRIOS
-- ========================
INSERT INTO tb_usuario (nome, senha, data_de_cadastro) VALUES ('João Silva', '123456', '2025-01-10');
INSERT INTO tb_usuario (nome, senha, data_de_cadastro) VALUES ('Maria Souza', '123456', '2025-01-12');
INSERT INTO tb_usuario (nome, senha, data_de_cadastro) VALUES ('Carlos Pereira', '123456', '2025-01-14');

-- ========================
-- USUARIO_GRUPO (relacionamento N:N)
-- ========================
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (3, 3);

-- ========================

-- ========================
-- PEDIDOS (tb_pedido)
-- ========================
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_cancelamento, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES (45.00, 5.00, 50.00, '2025-01-15', NULL, NULL, NULL, 'CRIADO', 1, 1, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Centro', 1), (82.00, 8.00, 90.00, '2025-01-16', '2025-01-16', NULL, NULL, 'CONFIRMADO', 2, 1, 2, '23090-120', 'Av. Atlântica', '2000', NULL, 'Copacabana', 2), (60.00, 5.00, 65.00, '2025-01-17', '2025-01-17', NULL, '2025-01-18', 'ENTREGUE', 3, 2, 1, '22790-400', 'Rua Maracanã', '55', NULL, 'Tijuca', 3);


-- ========================
-- ITENS DE PEDIDO (tb_item_pedido)
-- ========================
INSERT INTO tb_item_pedido (pedido_id, quantidade, preco_unitario, preco_total, observacao) VALUES (1, 2, 20.00, 40.00, 'Sem cebola'), (1, 1, 10.00, 10.00, 'Refrigerante 350ml'), (2, 3, 25.00, 75.00, 'Extra queijo'),(3, 1, 60.00, 60.00, 'Sem salada');
