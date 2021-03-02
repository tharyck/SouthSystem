package br.com.southsystem.service;

import br.com.southsystem.exceptions.*;
import br.com.southsystem.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public abstract class GenericService<ID, MODEL extends Model<ID>, REPOSITORY extends JpaRepository<MODEL, ID>> {

	@Autowired
	protected REPOSITORY repository;

	@ReadOnlyProperty
	public List<MODEL> index() {
		return repository.findAll();
	}

	@ReadOnlyProperty
	public MODEL show(ID id) {
		try {
			return repository.findById(id).orElseThrow(NotFoundRuntimeException::new);
		} catch (IllegalArgumentException iae) {
			throw new NotFoundRuntimeException();
		}
	}

	public MODEL create(MODEL model) {
		validateBeforeCreate(model);
		return this.createTransaction(model);
	}

	public MODEL update(ID id, MODEL model) {
		model.setId(id);
		validateBeforeUpdate(model);
		return this.updateTransaction(id, model);
	}

	public void delete(ID id) {
		validateBeforeDelete(id);
		this.deleteTransaction(id);
	}

	public abstract void validateBeforeCreate(MODEL model);

	public abstract void validateBeforeUpdate(MODEL model);

	public abstract void validateBeforeDelete(ID id);

	@Transactional
	MODEL createTransaction(MODEL model) {
		try {
			return repository.save(model);
		} catch (Exception e) {
			throw new CreateConflictRuntimeException(e.getMessage());
		}
	}

	@Transactional
	MODEL updateTransaction(ID id, MODEL model) {
		if (!repository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			model.setId(id);
			return repository.save(model);
		} catch (Exception e) {
			throw new UpdateConflictRuntimeException(e.getMessage());
		}
	}

	@Transactional
	void deleteTransaction(ID id) {
		if (!repository.existsById(id)) {
			throw new NotFoundRuntimeException();
		}
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new DeleteConflictRuntimeException(e.getMessage());
		}
	}
}
