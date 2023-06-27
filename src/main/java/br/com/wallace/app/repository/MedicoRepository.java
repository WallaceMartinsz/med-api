package br.com.wallace.app.repository;

import br.com.wallace.app.domain.medico.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {

    Page<Medico> findAll(Pageable pageable);
}
