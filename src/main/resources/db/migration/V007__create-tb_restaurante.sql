
create table tb_restaurante(
	id bigint auto_increment not null,
    nome varchar(50) not null,
	taxaFrete double,
	cozinha_id bigint not null,
    cep varchar(9) not null,
	logradouro varchar (30) not null,
    numero int not null,
    complemento varchar(70),
    bairro varchar(30),
    cidade_id bigint not null,

    primary key (id),

    constraint fk_cozinha foreign key(cozinha_id)
    references tb_cozinha(id),

	constraint fk_cidade foreign key(cidade_id)
    references tb_cidade(id)
    )