create table tb_cidade(
id bigint not null auto_increment,
nome varchar(30) not null,
estado_id bigint not null,

primary key (id),
constraint fk_estado foreign key(estado_id)
references tb_estado(id)
);