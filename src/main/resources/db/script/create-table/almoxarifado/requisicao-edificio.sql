CREATE TABLE almoxarifado.requisicao_edificio (
	id int8 NOT NULL,
	id_informacao_requisicao int8 NULL,
	CONSTRAINT requisicao_edificio_pkey PRIMARY KEY (id),
	CONSTRAINT fk615bgmifvj42uvesq9nuh45c6 FOREIGN KEY (id_informacao_requisicao) REFERENCES almoxarifado.informacao_requisicao(id)
)