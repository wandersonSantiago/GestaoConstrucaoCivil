package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.IRequisicao;
import br.com.system.gestaoConstrucaoCivil.enuns.StatusRequisicao;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.InformacaoRequisicaoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoService {

	@Autowired
	private InformacaoRequisicaoRepository requisicaoRepository;
	@Autowired
	private BaixaEstoqueService baixaEstoque;
	
	
	@Transactional(readOnly = false)
	public void aceitar(IRequisicao requisicao)
	{
		if(requisicao.getInformacaoRequisicao().getStatusRequisicao() == StatusRequisicao.PENDENTE)
		{
		   baixaEstoque.baixar(requisicao);	
		   requisicao.getInformacaoRequisicao().statusAceito();
		   requisicaoRepository.save(requisicao.getInformacaoRequisicao());
		}
		
	}
	@Transactional(readOnly = false)
	public void rejeitar(IRequisicao requisicao)
	{
		requisicao.getInformacaoRequisicao().statusRejeitado();
		requisicaoRepository.save(requisicao.getInformacaoRequisicao());
	}
}
