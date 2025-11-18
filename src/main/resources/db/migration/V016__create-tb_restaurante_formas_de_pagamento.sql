create table tb_restaurante_forma_de_pagamento(

restaurante_id bigint not null,
forma_de_pagamento_id bigint not null,

primary key(restaurante_id, forma_de_pagamento_id),

constraint fk_restaurante foreign key (restaurante_id)
references tb_restaurante(id),

constraint fk_forma_de_pagamento foreign key (forma_de_pagamento_id)
references tb_forma_de_pagamento(id))

