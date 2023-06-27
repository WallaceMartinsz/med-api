package br.com.wallace.app.service.medico;

import br.com.wallace.app.domain.medico.Medico;
import br.com.wallace.app.domain.medico.MedicoRequest;
import br.com.wallace.app.domain.medico.MedicoResponse;
import br.com.wallace.app.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class ServiceMedico {

    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public void cadastrarMedico(Medico dados){
        var medico = medicoRepository.save(dados);
    }

    public MedicoResponse detalharMedico(UUID id){
        var medico = medicoRepository.getReferenceById(id);
        return new MedicoResponse(medico);
    }

    @Transactional
    public MedicoResponse editarMedico(UUID id, MedicoRequest att) throws Exception {
        var medico = medicoRepository.findById(id)
                .orElseThrow( () -> new Exception("Médico não encontrado com o ID: " + id));

        medico.setNome(att.nome());
        medico.setEspecialidade(att.especialidade());
        return detalharMedico(id);

    }

    @Transactional
    public void apagarMedico(UUID id) {
        var medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);
    }

        public Page<Medico> findAll(Pageable pageable) {
        return medicoRepository.findAll(pageable);
    }


}
