package br.com.system.gestaoConstrucaoCivil.entity.servicos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import br.com.system.gestaoConstrucaoCivil.entity.Empresa;


@Entity
@Table(name = "prestadora_servico" , schema="servicos")
public class PrestadoraServico extends AbstractPersistable<Long>{

	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_dado_empresa",nullable = false)
	private Empresa dadoEmpresa;
	
	@OneToMany(mappedBy = "prestadoraServico", cascade = CascadeType.ALL)
	//@ManyToOne usar
	private List<ServicoEmpresa> servicos;
	
	public Empresa getDadoEmpresa() {
		return dadoEmpresa;
	}
	public void setDadoEmpresa(Empresa dadoEmpresa) {
		this.dadoEmpresa = dadoEmpresa;
	}
	
}
