package br.com.wallace.app.domain.medico;

import java.util.UUID;

public record MedicoResponse(UUID id, String nome, String crm, Especialidade especialidade) {
    public MedicoResponse(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getCrm(), medico.getEspecialidade());
    }

}
