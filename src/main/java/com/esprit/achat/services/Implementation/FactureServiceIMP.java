package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Facture;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.FactureService;
import com.esprit.achat.services.Interface.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceIMP  extends CrudServiceIMP<Facture,Integer> implements FactureService {
}
