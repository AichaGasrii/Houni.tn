package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class CommandeServiceIMP  extends CrudServiceIMP<Commande,Integer> implements CommandeService {
}
