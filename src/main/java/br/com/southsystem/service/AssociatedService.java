package br.com.southsystem.service;

import br.com.southsystem.exceptions.ScheduleNotFoundRuntimeException;
import br.com.southsystem.model.Associated;
import br.com.southsystem.repository.AssociatedRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssociatedService extends GenericService<Long, Associated, AssociatedRepository> {

	private AssociatedRepository associatedRepository;

	public AssociatedService(AssociatedRepository associatedRepository) {
		this.associatedRepository = associatedRepository;
	}

	public List<Associated> index() {
		return repository.findAll();
	}

	public Associated findById(Long id){
		return associatedRepository.findById(id).orElseThrow(ScheduleNotFoundRuntimeException::new);
	}

	@Override
	public void validateBeforeCreate(Associated model) {
	}

	@Override
	public void validateBeforeUpdate(Associated model) {
	}

	@Override
	public void validateBeforeDelete(Long aLong) {
	}
}
