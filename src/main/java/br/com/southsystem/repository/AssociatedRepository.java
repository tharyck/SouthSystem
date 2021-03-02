package br.com.southsystem.repository;

import br.com.southsystem.model.Associated;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociatedRepository extends JpaRepository<Associated, Long> {
    Associated findByCpf(String cpf);
}