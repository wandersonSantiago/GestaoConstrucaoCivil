package br.com.system.gestaoConstrucaoCivil.service.almoxarifado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.system.gestaoConstrucaoCivil.entity.Empreendimento;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.ItemNotaFiscal;
import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;
import br.com.system.gestaoConstrucaoCivil.enuns.Situacao;
import br.com.system.gestaoConstrucaoCivil.pojo.EntradaOuBaixa;
import br.com.system.gestaoConstrucaoCivil.pojo.InformacaoEntradaProduto;
import br.com.system.gestaoConstrucaoCivil.pojo.SessionUsuario;
import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.NotaFiscalProdutoRepository;
import br.com.system.gestaoConstrucaoCivil.service.EmpreendimentoService;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class NotaFiscalProdutoService {

	@Autowired
	private NotaFiscalProdutoRepository notaFiscalProdutoRepository;
	@Autowired
	private EstoqueEmpreendimentoService estoque;
    @Autowired
    private EmpreendimentoService empreendimentoService;
    
    
	public List<NotaFiscalProduto> buscarTodos() {

		return notaFiscalProdutoRepository.findAll();
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(NotaFiscalProduto notaFiscalProduto) {

		adicionarNotaProdutoItens(notaFiscalProduto);
		Empreendimento empreendimentoDoUsuario = SessionUsuario.getInstance().getUsuario().getEmpreendimento(); 
		notaFiscalProduto.getNotaFiscal().setEmpreendimento(empreendimentoDoUsuario);
		notaFiscalProdutoRepository.save(notaFiscalProduto);
		
	    empreendimentoService.adcionarValorGasto(notaFiscalProduto.getNotaFiscal().getValorTotal());
       
        for(ItemNotaFiscal itemNota : notaFiscalProduto.getItens())
        {
        	 EntradaOuBaixa entrada = new EntradaOuBaixa(itemNota.getProduto(), itemNota.getQuantidade(),empreendimentoDoUsuario);
        	estoque.entradaEstoque(entrada); 
        }
       
     }

	public NotaFiscalProduto buscarPorId(Long id) {

		return notaFiscalProdutoRepository.findOne(id);
	}

	private void adicionarNotaProdutoItens(NotaFiscalProduto nota) {
		for (int i = 0; i < nota.getItens().size(); i++) {
			nota.getItens().get(i).setNotaFiscalProduto(nota);
		}
	}

	public InformacaoEntradaProduto getInformacaoProduto(Long idProduto) {

		return gerarInformacao(idProduto);
	}

	private InformacaoEntradaProduto gerarInformacao(Long idProduto) {

		Long idEmpreendimento = SessionUsuario.getInstance().getUsuario().getEmpreendimento().getId();
		List<NotaFiscalProduto> notas = notaFiscalProdutoRepository.buscarNotaPorEmpreendimento(idEmpreendimento);
		Double valorTotal = 0.0;
		Integer quantidadeTotal = 0;
		for (NotaFiscalProduto notaFiscal : notas) {

			for (ItemNotaFiscal item : notaFiscal.getItens()) {
				
				if (item.getProduto().getId().equals(idProduto)) {
					
					valorTotal += item.getValorTotal();
					quantidadeTotal += item.getQuantidade();
				}
			}
		}
		return new InformacaoEntradaProduto(valorTotal, quantidadeTotal);
	}
	@Transactional(readOnly = false)
	public void cancelarEntrada(Long numeroEntrada)
	{
		NotaFiscalProduto nota = notaFiscalProdutoRepository.buscarPorNumero(numeroEntrada);
		for(ItemNotaFiscal item : nota.getItens())
		{
			EntradaOuBaixa baixa = new EntradaOuBaixa(item.getProduto(), item.getQuantidade(), nota.getNotaFiscal().getEmpreendimento());
			estoque.baixar(baixa);
		}
		empreendimentoService.removerValorGasto(nota.getNotaFiscal().getValorTotal());
		nota.getNotaFiscal().setSituacao(Situacao.CANCELADA);
		notaFiscalProdutoRepository.save(nota);
	}
}
