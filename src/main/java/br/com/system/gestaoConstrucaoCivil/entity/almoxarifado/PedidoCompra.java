package br.com.system.gestaoConstrucaoCivil.entity.almoxarifado;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "pedido_compra_id_seq",
sequenceName = "pedido_compra_id_seq",
initialValue = 1,
allocationSize = 1)
@Table(name = "pedido_compra")
public class PedidoCompra implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "pedido_compra_id_seq")
	private Long id;
	
	@OneToMany(mappedBy = "pedidoCompra", cascade = CascadeType.ALL)
	private List<ItemPedido> itens;
	@ManyToOne
	private Fornecedor fornecedor;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date previsao;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    public List<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemPedido> itens) {
		this.itens = itens;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Date getPrevisao() {
		return previsao;
	}

	public void setPrevisao(Date previsao) {
		this.previsao = previsao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoCompra other = (PedidoCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
}