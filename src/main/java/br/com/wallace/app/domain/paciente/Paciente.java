package br.com.wallace.app.domain.paciente;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String cpf;

    public Paciente(PacienteRequest data) {
        this.nome = data.nome();
        this.cpf = data.cpf();
    }
}
