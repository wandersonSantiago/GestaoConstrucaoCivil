package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.InformacaoRequisicao;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.RequisicaoCasa;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.RequisicaoCasaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoCasaService {


	@Autowired
	private RequisicaoCasaRepository requisicaoRepository;
	@Autowired
	private RequisicaoService requisicaoService; 
	
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoCasa requisicaoCasa){
		
		
		InformacaoRequisicao informacaoRequisicao = new InformacaoRequisicao();
		informacaoRequisicao.novaRequisicao();
		requisicaoCasa.setInformacaoRequisicao(informacaoRequisicao);
		requisicaoRepository.save(requisicaoCasa);
	}
	
	
	public Collection<RequisicaoCasa> buscarTodos(){
		return requisicaoRepository.buscarTodasRequisicoes(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());
	}
	
	public RequisicaoCasa buscarPorId(Long id){
		return requisicaoRepository.findOne(id);
	}
	@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao)
	{
		RequisicaoCasa requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
	    requisicaoService.aceitar(requisicao);
	}
	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao)
	{
		RequisicaoCasa requisicao = requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao);
		requisicaoService.rejeitar(requisicao);
	}


	public Page<RequisicaoCasa> buscarTodosComPaginacao(PageRequest pageRequest) {
		return requisicaoRepository.buscarTodasRequisicoesComPaginacao(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId(), pageRequest);
	}
	
	
}
