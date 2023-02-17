package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Devise;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.DeviseService;
import com.esprit.achat.services.Interface.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class DeviseServiceIMP  extends CrudServiceIMP<Devise,Integer> implements DeviseService {
}
