package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Reponse;
import com.esprit.achat.services.Interface.ReponseService;
import org.springframework.stereotype.Service;

@Service
public class ReponseServiceIMP extends CrudServiceIMP<Reponse,Integer> implements ReponseService {
}
