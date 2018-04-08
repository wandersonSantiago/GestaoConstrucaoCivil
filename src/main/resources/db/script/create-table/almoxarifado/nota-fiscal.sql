CREATE TABLE almoxarifado.nota_fiscal (
	id int8 NOT NULL,
	data_nota date NULL,
	data_vencimento date NULL,
	numero int8 NOT NULL,
	observacao varchar(255) NULL,
	situacao varchar(255) NULL,
	tipo_nota varchar(255) NULL,
	valor_total float8 NOT NULL,
	id_empreendimento int8 NULL,
	id_usuario_cadastro int8 NULL,
	CONSTRAINT nota_fiscal_pkey PRIMARY KEY (id),
	CONSTRAINT fkaop1c471vp2mvbmh5hnjy8qdf FOREIGN KEY (id_empreendimento) REFERENCES communs.empreendimento(id),
	CONSTRAINT fkcf6e8wurt93t0in22lb4yfxip FOREIGN KEY (id_usuario_cadastro) REFERENCES communs.usuario(id)
)