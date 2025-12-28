alter table tb_item_pedido
add column produto_id bigint not null,

add constraint fk_produto_item_pedido
foreign key (produto_id)
references tb_produto(id)