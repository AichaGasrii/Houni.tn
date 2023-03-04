package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.services.Interface.ReclamationService;
import org.springframework.stereotype.Service;

@Service
public class ReclamationServiceIMP extends CrudServiceIMP<Reclamation,Integer> implements ReclamationService {
}
