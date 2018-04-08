package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.system.gestaoConstrucaoCivil.service.DadoEmpresaService;

@RestController
@RequestMapping("/rest/dadoEmpresa")
public class DadoEmpresaRestController {

	@Autowired
	private DadoEmpresaService dadoEmpresaService;

	@GetMapping(value = "/existeCnpj/{cnpj}")
	public ResponseEntity existeCnpj(@PathVariable String cnpj) {

		return new ResponseEntity(dadoEmpresaService.existeCnpjCadastrado(cnpj), HttpStatus.OK);

	}

	@GetMapping(value = "/existeIe/{ie}")
	public ResponseEntity existeIe(@PathVariable String ie) {

		return new ResponseEntity(dadoEmpresaService.existeIeCadastrado(ie), HttpStatus.OK);

	}
}
