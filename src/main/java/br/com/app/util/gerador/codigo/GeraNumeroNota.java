package br.com.app.util.gerador.codigo;

import org.springframework.stereotype.Service;

@Service
public class GeraNumeroNota extends GeraCodigo {

	public GeraNumeroNota() {
		super(100000, 999999);
		// TODO Auto-generated constructor stub
	}

	public Integer gerarNumeroNota() {
		return gerarNumero();
		 
	}
}
