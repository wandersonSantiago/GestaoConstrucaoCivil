package br.com.system.gestaoConstrucaoCivil.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.ClienteMorador;
import br.com.system.gestaoConstrucaoCivil.entity.Usuario;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.ClienteMoradorRepository;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED)
public class ClienteMoradorService {

	@Autowired
	private ClienteMoradorRepository clienteMoradorRepository;
	
	public Page<ClienteMorador> buscarTodos(PageRequest page) {
		
		return  clienteMoradorRepository.findAll(page);
	}	
	public ClienteMorador buscarPorId(Long id) {
		return clienteMoradorRepository.findOne(id);
	}
	public ClienteMorador buscarPorCpf(String cpf) {
		return clienteMoradorRepository.findByCpf(cpf);
	}
	public Iterable<ClienteMorador> lista() {		
		return clienteMoradorRepository.findAll();
	}
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(ClienteMorador cliente)
	{ 
		salvaAtributosCliente(cliente);		
		cliente.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
		clienteMoradorRepository.save(cliente);
	}
	
	public ClienteMorador salvaAtributosCliente(ClienteMorador cliente){
			Boolean ativo = true;
			Usuario user =  new Usuario();
			user.setAtivo(ativo);
			user.setDataCadastro(new Date());
			user.setEmpreendimento(SessionUsuario.getInstance().getUsuario().getEmpreendimento());
			user.setNome(cliente.getNomeCompleto());
			user.setLogin(cliente.getCpf());
			user.setEmail(cliente.getEmail());
			String hash = new BCryptPasswordEncoder().encode(cliente.getCpf());
			user.setSenha(hash);
			cliente.setUsuario(user);			
		return cliente;
	}
}
