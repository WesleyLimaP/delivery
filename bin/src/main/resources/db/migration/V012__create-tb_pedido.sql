create table tb_pedido(
    id BigInt auto_increment,
    sub_total double not null,
    taxa_frrete double not null,
    valor_total double not null,
	data_criacao datetime not null,
    data_confirmacao datetime,
    data_cancelamento datetime,
    data_entrega datetime,
    status_entrega varchar(15) not null,
    cliente_id BigInt not null,
    restaurante_id BigInt not null,
	cep varchar(9) NOT NULL,
    logradouro varchar(30) NOT NULL,
    numero int NOT NULL,
    complemento varchar(70) DEFAULT NULL,
    bairro varchar(30) DEFAULT NULL,
    cidade_id bigint NOT NULL,
    forma_de_pagamento_id BigInt not null,

    primary key (id),

    constraint fk_cliente_pedido foreign key(cliente_id)
    references tb_usuario(id),

    constraint  fk_restaurante_pedido foreign key(restaurante_id)
    references tb_restaurante(id),

    constraint  fk_forma_de_pagamento_pedido foreign key(forma_de_pagamento_id)
    references tb_forma_de_pagamento(id),

    constraint  fk_cidade_pedido foreign key(cidade_id)
    references tb_cidade(id)

)