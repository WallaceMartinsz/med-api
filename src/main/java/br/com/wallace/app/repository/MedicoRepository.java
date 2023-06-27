package br.com.wallace.app.repository;

import br.com.wallace.app.domain.medico.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoRepository extends JpaRepository<Medico, UUID> {

}
