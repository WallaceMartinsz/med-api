package br.com.wallace.app.domain.medico;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    public Medico(MedicoRequest dados){
        this.nome = dados.nome();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
    }

}
