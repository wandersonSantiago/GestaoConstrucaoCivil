package br.com.system.gestaoConstrucaoCivil.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.system.gestaoConstrucaoCivil.entity.ConfigEmpreendimentoCasa;
import br.com.system.gestaoConstrucaoCivil.pojo.ConfigEmpreendimentoCasaPojo;
import br.com.system.gestaoConstrucaoCivil.service.ConfigEmpreendimentoCasaService;



@RestController
@RequestMapping("/rest/empreendimento/configuracao")
public class ConfigEmpreendimentoCasaRestController {

	@Autowired
    private ConfigEmpreendimentoCasaService configEmpreeendimentoCasaService;
	
	
	@PostMapping(value = "/salvaCasa")
    public ResponseEntity<ConfigEmpreendimentoCasa> salvar(@RequestBody ConfigEmpreendimentoCasa configEmpreendimentoCasa, UriComponentsBuilder ucBuilder) {
        
		configEmpreeendimentoCasaService.salvarOuEditar(configEmpreendimentoCasa);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("rest/empreendimento/configuracao/salvaCasa/{configEmpreendimento}").buildAndExpand(configEmpreendimentoCasa.getId()).toUri());
    
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
	
	@GetMapping(value = "/listaCasa/{id}")
    public ResponseEntity<ConfigEmpreendimentoCasa> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<ConfigEmpreendimentoCasa>(configEmpreeendimentoCasaService.buscarPorId(id), HttpStatus.OK);
    }
     

	@GetMapping(value = "/quantidadeCasa")
	public ResponseEntity<ConfigEmpreendimentoCasaPojo> configPojo() {
        
        return new ResponseEntity<ConfigEmpreendimentoCasaPojo>(configEmpreeendimentoCasaService.getConfig(),HttpStatus.OK);
	}
}
