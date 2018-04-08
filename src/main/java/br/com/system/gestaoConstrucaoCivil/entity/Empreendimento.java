package br.com.system.gestaoConstrucaoCivil.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.system.gestaoConstrucaoCivil.entity.almoxarifado.View;
import br.com.system.gestaoConstrucaoCivil.entity.servicos.PrestadoraServico;
import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;


@Entity
@Table(name = "empreendimento" , schema = "communs")
public class Empreendimento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne(cascade = {CascadeType.MERGE ,CascadeType.PERSIST})
	@JoinColumn(name="id_endereco_empreendimento",nullable = false)
	private Endereco enderecoEmpreendimento;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name="id_engenheiro_responsavel_funcionario",nullable = true)
	private Funcionario engenheiroResponsavelFuncionario;
	
	@JsonView(View.Summary.class)
	@ManyToOne
	@JoinColumn(name="id_engenheiro_responsavel_terceiro",nullable = true)
	private PrestadoraServico engenheiroResponsavelTerceiro;
	
	@Enumerated(EnumType.STRING)
	private TipoEmpreendimentoEnum tipoEmpreendimento;
    
	//@JsonView(View.Summary.class)
	@Column(nullable = false,length = 50)
	private String descricao;
	
	
	@Column(nullable = false)
	private Double valorMaximoGastar;
	@Column(nullable = false)
	private Double valoresGastos = 0.0;
	@Column(nullable = false)
	private Double porcentagem =  0.0;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_abertura")
	private Date dataAbertura;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fechamento")
	private Date datafechamento;
	
    @Column(nullable = false)
	private boolean ativo;
	
    
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Endereco getEnderecoEmpreendimento() {
		return enderecoEmpreendimento;
	}
	public void setEnderecoEmpreendimento(Endereco enderecoEmpreendimento) {
		this.enderecoEmpreendimento = enderecoEmpreendimento;
	}
	public Funcionario getEngenheiroResponsavelFuncionario() {
		return engenheiroResponsavelFuncionario;
	}
	public void setEngenheiroResponsavelFuncionario(Funcionario engenheiroResponsavelFuncionario) {
		this.engenheiroResponsavelFuncionario = engenheiroResponsavelFuncionario;
	}
	public PrestadoraServico getEngenheiroResponsavelTerceiro() {
		return engenheiroResponsavelTerceiro;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public void setEngenheiroResponsavelTerceiro(PrestadoraServico engenheiroResponsavelTerceiro) {
		this.engenheiroResponsavelTerceiro = engenheiroResponsavelTerceiro;
	}

	public TipoEmpreendimentoEnum getTipoEmpreendimento() {
		return tipoEmpreendimento;
	}
	public void setTipoEmpreendimento(TipoEmpreendimentoEnum tipoEmpreendimento) {
		this.tipoEmpreendimento = tipoEmpreendimento;
	}
	public Double getValorMaximoGastar() {
		return valorMaximoGastar;
	}
	public void setValorMaximoGastar(Double valorMaximoGastar) {
		this.valorMaximoGastar = valorMaximoGastar;
	}
	
	public Double getValoresGastos() {
		return valoresGastos;
	}
	public void setValoresGastos(Double valoresGastos) {
		this.valoresGastos = valoresGastos;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDatafechamento() {
		return datafechamento;
	}
	public void setDatafechamento(Date datafechamento) {
		this.datafechamento = datafechamento;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public Double getPorcentagem() {
		return porcentagem;
	}
	public void setPorcentagem(Double porcentagem) {
		this.porcentagem = porcentagem;
	}

	
}
