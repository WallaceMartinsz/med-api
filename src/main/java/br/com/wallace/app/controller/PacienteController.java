package br.com.wallace.app.controller;

import br.com.wallace.app.domain.medico.Medico;
import br.com.wallace.app.domain.medico.MedicoRequest;
import br.com.wallace.app.domain.medico.MedicoResponse;
import br.com.wallace.app.domain.paciente.Paciente;
import br.com.wallace.app.domain.paciente.PacienteRequest;
import br.com.wallace.app.domain.paciente.PacienteResponse;
import br.com.wallace.app.service.ServicePaciente;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private ServicePaciente servicePaciente;

    @GetMapping
    public ResponseEntity<Page<PacienteResponse>> listarPacientes (@PageableDefault(sort = "nome") Pageable page){
        Page<Paciente> pagePacientes = servicePaciente.findAll(page);
        Page<PacienteResponse> medicos = pagePacientes.map(PacienteResponse::new);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/id")
    public ResponseEntity<PacienteResponse> detalharPaciente(@PathVariable UUID id){
        return ResponseEntity.ok(servicePaciente.detalharPaciente(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterarDadosPaciente(@PathVariable UUID id,
                                                                 @RequestBody PacienteRequest novosDados) throws Exception {
        return ResponseEntity.ok(servicePaciente.editarPaciente(id, novosDados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPaciente(@PathVariable UUID id){
        servicePaciente.apagarPaciente(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<PacienteRequest> inserirMedico(@RequestBody @Valid PacienteRequest data){
        var paciente = new Paciente(data);
        servicePaciente.cadastrarPaciente(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PacienteRequest(paciente));
    }


}
