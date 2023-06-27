package br.com.wallace.app.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record PacienteRequest(@NotNull String nome, @NotNull String cpf) {
    public PacienteRequest(Paciente paciente){
        this(paciente.getNome(), paciente.getCpf());
    }
}
