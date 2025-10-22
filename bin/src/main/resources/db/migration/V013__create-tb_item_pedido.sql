
create table tb_item_pedido(
	id bigInt not null auto_increment,
	pedido_id bigInt not null,
    quantidade int not null,
	preco_unitario double not null,
    preco_total double not null,
	observacao varchar(40),
	produto_id bigInt not null,

    primary key(id),

    constraint fk_pedido_item_pedido foreign key (pedido_id)
    references tb_pedido(id),

    constraint fk_produto_pedido foreign key (produto_id)
    references tb_produto(id)

    )