package br.com.system.gestaoConstrucaoCivil.util.geradorCodigo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.system.gestaoConstrucaoCivil.repository.almoxarifado.ProdutoRepository;


@Service
public class GeraCodigoProduto extends GeraCodigo{

	@Autowired
	private ProdutoRepository produtoRepository;
	private Integer codigo = null;
    
	public GeraCodigoProduto() {
		
		super(100000,999999);
	
	}
    public Integer gerarCodigoProduto()
    {
     	for(Integer tentativa = 0 ; tentativa <= 99999; tentativa++)
    	{
    		 codigo  =  gerarNumero();
    		if(produtoRepository.existeCodigo(codigo) == false ){
    		   
    			
    			return codigo;
    		}
    	}
    	throw new GeraCodigoProdutoException("Não é possivel gerar um código para produto");
    	
    }
    public String gerarCodigoBarra()
	{
		return "0000000".concat(codigo.toString());
	}
   
    
}
