package br.com.wallace.app.service;

import br.com.wallace.app.domain.medico.Medico;
import br.com.wallace.app.domain.medico.MedicoRequest;
import br.com.wallace.app.domain.medico.MedicoResponse;
import br.com.wallace.app.domain.paciente.Paciente;
import br.com.wallace.app.domain.paciente.PacienteRequest;
import br.com.wallace.app.domain.paciente.PacienteResponse;
import br.com.wallace.app.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServicePaciente {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Transactional
    public void cadastrarPaciente(Paciente dados){
        var paciente = pacienteRepository.save(dados);
    }

    public PacienteResponse detalharPaciente(UUID id){
        var paciente = pacienteRepository.getReferenceById(id);
        return new PacienteResponse(paciente);
    }

    @Transactional
    public PacienteResponse editarPaciente(UUID id, PacienteRequest att) throws Exception {
        var paciente = pacienteRepository.findById(id)
                .orElseThrow( () -> new Exception("Paciente não encontrado com o ID: " + id));

        paciente.setNome(att.nome());
        return detalharPaciente(id);
    }

    @Transactional
    public void apagarPaciente(UUID id) {
        var paciente = pacienteRepository.getReferenceById(id);
        pacienteRepository.delete(paciente);
    }

    public Page<Paciente> findAll(Pageable pageable) {
        return pacienteRepository.findAll(pageable);
    }

}
