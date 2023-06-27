package br.com.wallace.app.domain.medico;

import jakarta.validation.constraints.NotNull;

public record MedicoRequest(@NotNull String nome, @NotNull String crm, @NotNull Especialidade especialidade) {
    public MedicoRequest(Medico medico){
        this(medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }
}
