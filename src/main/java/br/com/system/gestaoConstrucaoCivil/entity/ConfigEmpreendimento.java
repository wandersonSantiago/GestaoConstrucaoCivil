package br.com.system.gestaoConstrucaoCivil.entity;
 
import java.io.Serializable;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.system.gestaoConstrucaoCivil.enuns.TipoEmpreendimentoEnum;

 
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "config_empreendimento" , schema = "communs")

public  class ConfigEmpreendimento  implements Serializable {
 
   	private static final long serialVersionUID = 1L;

   	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
   	
	@ManyToOne
    @JoinColumn(name="id_empreendimento",nullable = true)
    private Empreendimento empreendimento;
     
    @Enumerated(EnumType.ORDINAL)
    private TipoEmpreendimentoEnum.Tipo tipo;
     
     
  
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Empreendimento getEmpreendimento() {
         
        return empreendimento;
    }
 
    public void setEmpreendimento(Empreendimento empreendimento) {
        this.empreendimento = empreendimento;
    }
 
    public TipoEmpreendimentoEnum.Tipo getTipo() {
        return tipo;
    }
 
    public void setTipo(TipoEmpreendimentoEnum.Tipo tipo) {
        this.tipo = tipo;
    }
     
     
}