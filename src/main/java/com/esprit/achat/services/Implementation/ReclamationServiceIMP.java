package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Rating;
import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.repositories.ReclamationRepositoryMy;
import com.esprit.achat.services.Interface.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReclamationServiceIMP extends CrudServiceIMP<Reclamation,Integer> implements ReclamationService {

    @Autowired
    private ReclamationRepositoryMy reclamationRepository;
    @Override
    public Reclamation updateReclamation(Integer id, Reclamation reclamation) {
        Reclamation existingReclamation = retrieve(id);
        existingReclamation.setDescription(reclamation.getDescription());
        existingReclamation.setDate(reclamation.getDate());
        existingReclamation.setReclamationType(reclamation.getReclamationType());

        return reclamationRepository.save(existingReclamation);
    }
    @Override
    public List<Reclamation> getReclamationByUser(String name) {

        return 	reclamationRepository.getReclamationByUser(name);
    }


}
