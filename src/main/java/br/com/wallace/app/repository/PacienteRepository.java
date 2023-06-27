package br.com.wallace.app.repository;

import br.com.wallace.app.domain.paciente.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
}
