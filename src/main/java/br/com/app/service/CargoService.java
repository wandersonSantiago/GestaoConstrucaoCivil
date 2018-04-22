package br.com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.entity.Cargo;
import br.com.app.repository.CargoRepository;
import br.com.app.service.interfaceservice.ICargoService;

@Service
@EnableCaching(proxyTargetClass = true)
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class CargoService implements ICargoService<Cargo> {

	@Autowired
	private CargoRepository cargoRepository;

	@Transactional(readOnly = false)
	public void salvarOuEditar(Cargo cargo) {
		cargoRepository.save(cargo);
	}

	public List<Cargo> buscarTodos() {

		return cargoRepository.findAll();
	}

	public Page<Cargo> listaComPaginacao(PageRequest pageRequest) {
		return cargoRepository.findAll(pageRequest);
	}

	@Override
	public Optional<Cargo> findById(Long id) {

		return cargoRepository.findById(id);
	}
}