package br.com.app.service.almoxarifado;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.almoxarifado.InformacaoRequisicao;
import br.com.app.entity.almoxarifado.RequisicaoEdificio;
import br.com.app.pojo.SessionUsuario;
import br.com.app.repository.almoxarifado.RequisicaoEdificioRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class RequisicaoEdificioService {

	@Autowired
	private RequisicaoEdificioRepository requisicaoRepository;
	@Autowired
	private RequisicaoService requisicaoService;

	@Transactional(readOnly = false)
	public void salvarOuEditar(RequisicaoEdificio requisicaoEdificio) {

		InformacaoRequisicao informacaoRequisicao = new InformacaoRequisicao();
		informacaoRequisicao.novaRequisicao();
		requisicaoEdificio.setInformacaoRequisicao(informacaoRequisicao);
		requisicaoRepository.save(requisicaoEdificio);

	}

	public Collection<RequisicaoEdificio> buscarTodos() {

		return requisicaoRepository
				.buscarTodasRequisicoes(SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId());
	}

	public Optional<RequisicaoEdificio> buscarPorId(Long id) {
		return requisicaoRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public void aceitar(Integer numeroRequisicao) {
		requisicaoService.aceitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}

	@Transactional(readOnly = false)
	public void rejeitar(Integer numeroRequisicao) {
		requisicaoService.rejeitar(requisicaoRepository.buscarPorNumeroRequisicao(numeroRequisicao));
	}

	public Page<RequisicaoEdificio> buscarTodosComPaginacao(PageRequest pageRequest) {
		return requisicaoRepository.buscarTodasRequisicoesComPaginacao(
				SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId(), pageRequest);
	}

}