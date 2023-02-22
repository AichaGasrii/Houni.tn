package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.FactureavoirService;
import com.esprit.achat.services.Interface.QuestionService;
import org.springframework.stereotype.Service;

@Service
    public class FactureavoirServiceIMP  extends CrudServiceIMP<FactureAvoir,Integer> implements FactureavoirService {
}
