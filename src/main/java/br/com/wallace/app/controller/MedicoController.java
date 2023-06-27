package br.com.wallace.app.controller;

import br.com.wallace.app.domain.medico.Medico;
import br.com.wallace.app.domain.medico.MedicoRequest;
import br.com.wallace.app.domain.medico.MedicoResponse;
import br.com.wallace.app.service.medico.ServiceMedico;
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
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private ServiceMedico serviceMedico;

    @PostMapping
    public ResponseEntity<MedicoRequest> inserirMedico(@RequestBody @Valid MedicoRequest data){
        var medico = new Medico(data);
        serviceMedico.cadastrarMedico(medico);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MedicoRequest(medico));

    }

    @GetMapping
    public ResponseEntity<Page<MedicoResponse>> listarMedicos(@PageableDefault(sort = "nome") Pageable page){
        Page<Medico> pageMedicos = serviceMedico.findAll(page);
        Page<MedicoResponse> medicos = pageMedicos.map(MedicoResponse::new);
        return ResponseEntity.ok(medicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> detalharMedico(@PathVariable UUID id){
        return ResponseEntity.ok(serviceMedico.detalharMedico(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deletarMedico(@PathVariable UUID id){
        serviceMedico.apagarMedico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> alterarDadosMedico(@PathVariable UUID id, @RequestBody MedicoRequest att) throws Exception {
        return ResponseEntity.ok(serviceMedico.editarMedico(id, att));
    }
}
