package br.com.system.gestaoConstrucaoCivil.web.controller.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEdificio;
import br.com.system.gestaoConstrucaoCivil.entity.servicos.ServicoEmpresa;
import br.com.system.gestaoConstrucaoCivil.service.servicos.ServicoEmpresaService;

@RestController
@RequestMapping("/rest/servico/vincular")
public class ServicoEmpresaRestController {

	@Autowired
	 private ServicoEmpresaService servicoService;
	
	 
	@GetMapping(value = "/lista")
	public ResponseEntity<Iterable<ServicoEmpresa>> buscarTodos() {
		return new ResponseEntity<Iterable<ServicoEmpresa>>(servicoService.lista(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/prestadora/{id}/pagamentos")
	public ResponseEntity<Iterable<ServicoEmpresa>> buscarServicosDaPrestadora(@PathVariable Long id) {
		return new ResponseEntity<Iterable<ServicoEmpresa>>(servicoService.buscarServicosDaPrestadora(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/prestadora/{id}/pagamentos/liberado")
	public ResponseEntity<Iterable<ServicoEmpresa>> buscarServicosPagamentoLiberadoDaPrestadora(@PathVariable Long id) {
		return new ResponseEntity<Iterable<ServicoEmpresa>>(servicoService.buscarServicosPagamentoLiberadoDaPrestadora(id), HttpStatus.OK);
	}
	
	 @GetMapping
		public ResponseEntity<Page<ServicoEmpresa>> lista(@RequestParam(defaultValue="0", required=false) int page
				,@RequestParam(defaultValue="0", required=false) int maxResults) {
			Page<ServicoEmpresa> objeto = servicoService.buscarTodos(new PageRequest(page, maxResults));
			return new ResponseEntity<Page<ServicoEmpresa>>(objeto, HttpStatus.OK);
		}
	 
	 @PostMapping( value="/salvar")
	 public ResponseEntity<ServicoEmpresa> salvar(@RequestBody ServicoEmpresa servico,UriComponentsBuilder ucBuilder)
	 {
		 servicoService.salvarOuEditar(servico);
		 HttpHeaders headers = new HttpHeaders();
		 return new ResponseEntity<>(headers, HttpStatus.CREATED);
	 }
	 @PostMapping( value="/prestadora/{id}/pagamentos/efetuar")
	 public ResponseEntity<Iterable<ServicoEmpresa>> efetuarPagamento(@PathVariable Long id) {
			return new ResponseEntity<Iterable<ServicoEmpresa>>(servicoService.efetuarPagamento(id), HttpStatus.OK);
		}
	 
	 @GetMapping(value = "/buscarPorId/{id}")
		public ResponseEntity<ServicoEmpresa> buscarPorId(@PathVariable Long id) {
			return new ResponseEntity<ServicoEmpresa>(servicoService.buscarPorId(id), HttpStatus.OK);
		}
	 @PostMapping(value = "/pagamento/edificio/salvar")
		public ResponseEntity<ServicoEdificio> salvarPagamentoEdificio(@RequestBody ServicoEdificio servico,
				UriComponentsBuilder ucBuilder) {
		 servicoService.salvarPagamento(servico);
			HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
	 @PostMapping(value = "/pagamento/casa/salvar")
		public ResponseEntity<ServicoEdificio> salvarPagamentoCasa(@RequestBody ServicoEdificio servico,
				UriComponentsBuilder ucBuilder) {
		 servicoService.salvarPagamento(servico);
			HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
	 @PostMapping(value = "/pagamento/outros/salvar")
		public ResponseEntity<ServicoEdificio> salvarPagamentoOutros(@RequestBody ServicoEdificio servico,
				UriComponentsBuilder ucBuilder) {
		 servicoService.salvarPagamento(servico);
			HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		}
	 

}
