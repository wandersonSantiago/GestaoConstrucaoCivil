package br.com.app.service.servicos;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.servicos.PrestadoraServico;
import br.com.app.repository.servicos.PrestadoraServicoRepository;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PrestadoraServicoService {

	@Autowired
	private PrestadoraServicoRepository prestadoraServicoRepository;

	public Page<PrestadoraServico> buscarTodos(PageRequest pages) {

		return prestadoraServicoRepository.findAll(pages);
	}

	@Transactional(readOnly = false)
	public void salvarOuEditar(PrestadoraServico prestadoraServico) {
		prestadoraServicoRepository.save(prestadoraServico);
	}

	public Optional<PrestadoraServico> buscarPorId(Long id) {

		return prestadoraServicoRepository.findById(id);
	}

	public Collection<PrestadoraServico> lista() {
		return prestadoraServicoRepository.findAll();
	}
}
