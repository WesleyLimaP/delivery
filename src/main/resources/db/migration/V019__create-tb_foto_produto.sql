create table tb_foto_produto (
produtoId bigint unique not null,
nomeArquivo varchar(50) unique not null,
descricao varchar(30),
contentType varchar(30) not null,
tamanho integer not null,

primary key (produtoId),
constraint fk_produto_foto_produto 
foreign key(produtoId)
references tb_produto(id)
);