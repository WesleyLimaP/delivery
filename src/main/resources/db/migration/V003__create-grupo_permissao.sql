create table tb_grupo_permissao(
grupo_id bigint not null,
permissao_id bigint not null,

primary key(grupo_id, permissao_id),

constraint fk_grupo foreign key(grupo_id)
references tb_grupo(id),

constraint fk_permissao foreign key(permissao_id)
references tb_permissao(id))
