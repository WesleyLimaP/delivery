  create table tb_usuario_grupo(
	usuario_id bigint not null,
	grupo_id bigint not null,

    primary key (usuario_id, grupo_id),

    constraint fk_usuario foreign key(usuario_id)
    references tb_usuario(id),

	constraint fk_grupo_usuario foreign key(grupo_id)
    references tb_grupo(id)

    )