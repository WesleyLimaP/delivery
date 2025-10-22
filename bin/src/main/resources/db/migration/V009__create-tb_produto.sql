CREATE TABLE tb_produto (
  id bigint NOT NULL AUTO_INCREMENT,
  nome varchar(50) NOT NULL,
  descricao varchar(100) DEFAULT NULL,
  preco double NOT NULL,
  ativo tinyint(1) DEFAULT NULL,
  restaurante_id bigint NOT NULL,
  PRIMARY KEY (id),
  KEY fk_restaurante_prod (restaurante_id),
  CONSTRAINT fk_restaurante_prod FOREIGN KEY (restaurante_id) REFERENCES tb_restaurante (id)
)