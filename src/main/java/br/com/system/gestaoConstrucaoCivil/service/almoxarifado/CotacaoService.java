package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.Cotacao;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoRepository;

@Service
@Transactional(readOnly=true, propagation = Propagation.REQUIRED)
public class CotacaoService {
	
	@Autowired
	private CotacaoRepository cotacaoRepository;
	@Autowired
	private VerificaItensGanhadores verificarItens;
	
	@Transactional(readOnly = false)
	public void salvaAltera(Cotacao cotacao){
		
		cotacao.abrir();
		cotacaoRepository.save(cotacao);
	}
	public Collection<Cotacao> buscarTodos(){
		return cotacaoRepository.findAll();
	}
	public Cotacao buscaPorId(Long id){
		return cotacaoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void fecharCotacao(Long idCotacao)
	{
		Cotacao cotacao = cotacaoRepository.findOne(idCotacao);
		cotacao.fechar();
		verificarItens.verificarGanhadores(cotacao);
		cotacaoRepository.save(cotacao);
	}

}
