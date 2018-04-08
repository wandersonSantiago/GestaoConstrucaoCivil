package br.com.system.gestaoConstrucaoCivil.repository.almoxarifado;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.NotaFiscalProduto;

public interface NotaFiscalProdutoRepository extends JpaRepository<NotaFiscalProduto,Long> {

    @Deprecated
	@Query("FROM NotaFiscalProduto")
	public List<NotaFiscalProduto> buscarNota();
	
    @EntityGraph(value = "NotaFiscalProduto.detail", type = EntityGraphType.LOAD)
    @Query("FROM NotaFiscalProduto notaProduto  WHERE notaProduto.notaFiscal.empreendimento.id = ?1")
	public List<NotaFiscalProduto> buscarNotaPorEmpreendimento(Long idEmpreendimento);
	
	@Query("FROM NotaFiscalProduto nota WHERE nota.notaFiscal.numero = ?1")
	public NotaFiscalProduto buscarPorNumero(Long numero);
}
