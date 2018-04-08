package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresa;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.CotacaoEmpresaItem;
import br.com.system.gestaoConstrucaoCivil.enuns.CotacaoEmpresaItemStatus;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.CotacaoEmpresaRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CotacaoEmpresaService {

	@Autowired
	private CotacaoEmpresaRepository cotacaoEmpresaRepository;
	
	@Transactional(readOnly = false)
	public void salvarOuEditar(CotacaoEmpresa cotacaoEmpresa) {

		for (CotacaoEmpresaItem item : cotacaoEmpresa.getItens()) {
			item.setId(null);
			item.setStatus(CotacaoEmpresaItemStatus.PENDENTE);
			item.setCotacaoEmpresa(cotacaoEmpresa);
		}
		cotacaoEmpresaRepository.save(cotacaoEmpresa);
	}

	public List<CotacaoEmpresa> buscarTodos() {

		return cotacaoEmpresaRepository.findAll();
	}

	public CotacaoEmpresa buscarPorId(Long id) {

		return cotacaoEmpresaRepository.findOne(id);
	}
	public List<CotacaoEmpresa> ganhadores(Long idCotacao)
	{
		List<CotacaoEmpresa>  cotacoes = cotacaoEmpresaRepository.buscarGanhadores(idCotacao);
		for(CotacaoEmpresa item :  cotacoes)
		{
			item.removerItensPerdedores();
		}
		return  cotacoes;
	
	}
	
	public List<CotacaoEmpresa> concorrentes(Long idCotacao)
	{
		return cotacaoEmpresaRepository.buscarPorCotacao(idCotacao);
	}
	
}
