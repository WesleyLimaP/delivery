create table tb_forma_de_pagamento(
	id bigint auto_increment not null,
    descricao varchar(50) not null,
    restaurante_id bigint not null,


    primary key (id),

	constraint fk_restaurante foreign key(restaurante_id)
    references tb_restaurante(id)
    )
