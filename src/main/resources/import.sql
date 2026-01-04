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
INSERT INTO tb_restaurante (nome, aberto, taxa_frete, cozinha_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES ('Restaurante Sabor Carioca', true, 5.00, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1), ('Churrascaria Boi na Brasa', true,  8.50, 2, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2), ('Pizzaria Bella Napoli', true,  6.75, 3, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- ========================
-- FORMAS DE PAGAMENTO
-- ========================
INSERT INTO tb_forma_de_pagamento (descricao) VALUES ('Dinheiro');
INSERT INTO tb_forma_de_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO tb_forma_de_pagamento (descricao) VALUES ('Pix');
INSERT INTO tb_forma_de_pagamento (descricao) VALUES ('Cartão de Débito');

-- ========================
-- RESTAURANTE_FORMA_PAGAMENTO
-- ========================

insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (1, 1)
insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (1, 2)
insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (2, 3)
insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (2, 4)
insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (3, 1)
insert INTO tb_restaurante_forma_pagamento(restaurante_id, forma_pagamento_id) VALUES (3, 4)

-- ========================
-- PRODUTOS
-- ========================
-- Produtos para restaurante_id 1
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pizza Margherita', 'Pizza com tomate e manjericão', 35.00, true, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pizza Calabresa', 'Pizza com calabresa e cebola', 42.00, true, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pizza Quatro Queijos', 'Pizza com mussarela, parmesão, gorgonzola e provolone', 48.00, true, 1);

-- Produtos para restaurante_id 2
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Hambúrguer Clássico', 'Pão, carne, queijo e alface', 25.00, true, 2);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Hambúrguer Bacon', 'Hambúrguer com bacon crocante', 32.00, true, 2);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Batata Frita', 'Porção de batata frita crocante', 15.00, true, 2);

-- Produtos para restaurante_id 3
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Sushi Salmão', 'Sushi tradicional com salmão fresco', 28.00, true, 3);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Temaki Atum', 'Temaki recheado com atum e cream cheese', 22.00, true, 3);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Yakissoba', 'Massa oriental com legumes e carne', 35.00, true, 3);


-- Produto inativo para exemplo
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Prato do Dia Especial', 'Prato especial temporariamente indisponível', 30.00, false, 2);

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
INSERT INTO tb_usuario (nome, senha, email, data_de_cadastro) VALUES ('wesley','123456','wesleylima029@gmail.com', '2025-01-10');
INSERT INTO tb_usuario (nome, senha,email, data_de_cadastro) VALUES ('Maria Souza', '123456', 'MariaSouza@gmail.com', '2025-01-12');
INSERT INTO tb_usuario (nome, senha,email, data_de_cadastro) VALUES ('Carlos Pereira','123456', 'CarlosPereira@gmail.com','2025-01-14');

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
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_cancelamento, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES (45.00, 5.00, 50.00, '2025-01-15', NULL, NULL, NULL, 'CRIADO', 1, 1, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Centro', 1), (82.00, 8.00, 90.00, '2025-01-16', NULL, NULL, NULL, 'CONFIRMADO', 2, 1, 2, '23090-120', 'Av. Atlântica', '2000', NULL, 'Copacabana', 2), (60.00, 5.00, 65.00, '2025-01-17', NULL, NULL, NULL, 'ENTREGUE', 3, 2, 1, '22790-400', 'Rua Maracanã', '55', NULL, 'Tijuca', 3);


-- ========================
-- ITENS DE PEDIDO (tb_item_pedido)
-- ========================
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES (1, 2, 2, 20.00, 40.00, 'Sem cebola');
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES (1, 1, 1, 10.00, 10.00, 'Refrigerante 350ml');
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES (2, 3, 4, 25.00, 75.00, 'Extra queijo');
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES (3, 1, 7, 60.00, 60.00, 'Sem salada');
-- USUARIO_RESTAURANTE (relacionamento N:N)
-- ========================
INSERT INTO tb_usuario_restaurante (usuario_id, restaurante_id) VALUES (1, 1);
INSERT INTO tb_usuario_restaurante (usuario_id, restaurante_id) VALUES (2, 2);
INSERT INTO tb_usuario_restaurante (usuario_id, restaurante_id) VALUES (3, 3);
INSERT INTO tb_usuario_restaurante (usuario_id, restaurante_id) VALUES (3, 2);

-- ========================
-- PEDIDOS COM STATUS VÁLIDOS
-- ========================

-- ========================
-- PEDIDOS RESTAURANTE 1 (Restaurante Sabor Carioca - Italiana)
-- ========================

-- Pedido 4: CRIADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(70.00, 5.00, 75.00, '2025-01-18', 'CRIADO', 1, 1, 2, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1);

-- Pedido 5: CONFIRMADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(48.00, 5.00, 53.00, '2025-01-19', '2025-01-19', 'CONFIRMADO', 2, 1, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1);

-- Pedido 6: ENTREGUE
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(35.00, 5.00, 40.00, '2025-01-20', '2025-01-20', '2025-01-20 11:45:00', 'ENTREGUE', 3, 1, 2, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1);

-- Pedido 7: CANCELADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_cancelamento, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(42.00, 5.00, 47.00, '2025-01-21', '2025-01-21', 'CANCELADO', 1, 1, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1);

-- ========================
-- PEDIDOS RESTAURANTE 2 (Churrascaria Boi na Brasa - Japonesa)
-- ========================

-- Pedido 8: CRIADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(32.00, 8.50, 40.50, '2025-01-18', 'CRIADO', 2, 2, 3, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2);

-- Pedido 9: CONFIRMADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(64.00, 8.50, 72.50, '2025-01-19', '2025-01-19', 'CONFIRMADO', 3, 2, 4, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2);

-- Pedido 10: ENTREGUE
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(75.00, 8.50, 83.50, '2025-01-20, '2025-01-20 18:35:00', '2025-01-20 19:20:00', 'ENTREGUE', 1, 2, 3, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2);

-- Pedido 11: CANCELADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_cancelamento, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(50.00, 8.50, 58.50, '2025-01-21', '2025-01-21', 'CANCELADO', 2, 2, 4, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2);

-- ========================
-- PEDIDOS RESTAURANTE 3 (Pizzaria Bella Napoli - Brasileira)
-- ========================

-- Pedido 12: CRIADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(35.00, 6.75, 41.75, '2025-01-18', 'CRIADO', 3, 3, 4, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- Pedido 13: CONFIRMADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(60.00, 6.75, 66.75, '2025-01-19', '2025-01-19', 'CONFIRMADO', 1, 3, 1, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- Pedido 14: ENTREGUE
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(28.00, 6.75, 34.75, '2025-01-20', '2025-01-20', '2025-01-20', 'ENTREGUE', 2, 3, 4, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- Pedido 15: CANCELADO
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_cancelamento, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(22.00, 6.75, 28.75, '2025-01-21', '2025-01-21', 'CANCELADO', 3, 3, 1, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- ========================
-- PEDIDOS ADICIONAIS PARA ESTATÍSTICAS (todos ENTREGUE)
-- ========================

-- Mais pedidos ENTREGUE para estatísticas
INSERT INTO tb_pedido (sub_total, taxa_frete, valor_total, data_criacao, data_confirmacao, data_entrega, status, cliente_id, restaurante_id, formas_de_pagamento_id, cep, logradouro, numero, complemento, bairro, cidade_id) VALUES(48.00, 5.00, 53.00, '2025-01-15', '2025-01-15', '2025-01-15', 'ENTREGUE', 3, 1, 2, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1),(35.00, 5.00, 40.00, '2025-01-16', '2025-01-16', '2025-01-16', 'ENTREGUE', 2, 1, 1, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1),(42.00, 5.00, 47.00, '2025-01-17', '2025-01-17', '2025-01-17', 'ENTREGUE', 1, 1, 2, '22290-240', 'Rua das Laranjeiras', '102', 'Apto 301', 'Laranjeiras', 1),(25.00, 8.50, 33.50, '2025-01-15', '2025-01-15', '2025-01-15', 'ENTREGUE', 1, 2, 3, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2),(32.00, 8.50, 40.50, '2025-01-16', '2025-01-16', '2025-01-16', 'ENTREGUE', 2, 2, 4, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2),(50.00, 8.50, 58.50, '2025-01-17', '2025-01-17', '2025-01-17', 'ENTREGUE', 3, 2, 3, '23090-120', 'Av. Atlântica', '2000', 'Loja A', 'Copacabana', 2),(22.00, 6.75, 28.75, '2025-01-15', '2025-01-15', '2025-01-15', 'ENTREGUE', 2, 3, 1, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3),(35.00, 6.75, 41.75, '2025-01-16', '2025-01-16', '2025-01-16', 'ENTREGUE', 3, 3, 4, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3),(28.00, 6.75, 34.75, '2025-01-17', '2025-01-17', '2025-01-17', 'ENTREGUE', 1, 3, 4, '22790-400', 'Rua Maracanã', '55', 'Casa 2', 'Tijuca', 3);

-- ========================
-- ITENS DE PEDIDO
-- ========================

-- Pedido 4 (Restaurante 1 - CRIADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(4, 1, 1, 35.00, 35.00, 'Borda recheada'),(4, 1, 2, 42.00, 42.00, 'Sem cebola');

-- Pedido 5 (Restaurante 1 - CONFIRMADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(5, 1, 3, 48.00, 48.00, 'Extra orégano');

-- Pedido 6 (Restaurante 1 - ENTREGUE)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(6, 1, 2, 42.00, 42.00, 'Borda fina');

-- Pedido 7 (Restaurante 1 - CANCELADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(7, 1, 1, 35.00, 35.00, 'Sem azeitonas');

-- Pedido 8 (Restaurante 2 - CRIADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(8, 1, 4, 25.00, 25.00, 'Sem maionese');

-- Pedido 9 (Restaurante 2 - CONFIRMADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(9, 2, 5, 32.00, 64.00, 'Bem passado');

-- Pedido 10 (Restaurante 2 - ENTREGUE)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(10, 1, 6, 15.00, 15.00, 'Com cheddar');

-- Pedido 11 (Restaurante 2 - CANCELADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(11, 2, 4, 25.00, 50.00, 'Um sem picles');

-- Pedido 12 (Restaurante 3 - CRIADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(12, 1, 8, 22.00, 22.00, 'Com gergelim');

-- Pedido 13 (Restaurante 3 - CONFIRMADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(13, 1, 9, 35.00, 35.00, 'Com frango');

-- Pedido 14 (Restaurante 3 - ENTREGUE)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(14, 1, 7, 28.00, 28.00, 'Molho shoyu extra');

-- Pedido 15 (Restaurante 3 - CANCELADO)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(15, 1, 8, 22.00, 22.00, 'Sem cream cheese');

-- Itens para pedidos adicionais (16-24 - todos ENTREGUE)
INSERT INTO tb_item_pedido (pedido_id, quantidade, produto_id, preco_unitario, preco_total, observacao) VALUES(16, 1, 3, 48.00, 48.00, 'Borda catupiry'),(17, 1, 1, 35.00, 35.00, 'Extra tomate'),(18, 1, 2, 42.00, 42.00, 'Sem azeitonas'),(19, 1, 4, 25.00, 25.00, 'Com queijo extra'),(20, 1, 5, 32.00, 32.00, 'Sem bacon'),(21, 2, 6, 15.00, 30.00, 'Extra sal'),(22, 1, 7, 28.00, 28.00, 'Com wasabi'),(23, 1, 8, 22.00, 22.00, 'Com gergelim torrado'),(24, 1, 9, 35.00, 35.00, 'Com carne bovina');