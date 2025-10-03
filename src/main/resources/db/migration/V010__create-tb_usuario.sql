  create table tb_usuario(
	id bigint auto_increment not null,
    nome varchar(50) not null,
    senha varchar(20) not null,
	data_cadastro datetime not null,

    primary key (id)

    )