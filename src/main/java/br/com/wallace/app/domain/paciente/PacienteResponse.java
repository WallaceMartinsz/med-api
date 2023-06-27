package br.com.wallace.app.domain.paciente;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PacienteResponse(UUID id, String nome, String cpf) {
    public PacienteResponse(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getCpf());
    }
}
