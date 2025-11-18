alter table tb_forma_de_pagamento
drop foreign key fk_restaurante;

alter table tb_forma_de_pagamento
drop column  restaurante_id;