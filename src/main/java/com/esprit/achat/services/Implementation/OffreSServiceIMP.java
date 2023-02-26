package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.OffreSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffreSServiceIMP extends CrudServiceIMP<OffreService,Integer> implements OffreSService {

}
