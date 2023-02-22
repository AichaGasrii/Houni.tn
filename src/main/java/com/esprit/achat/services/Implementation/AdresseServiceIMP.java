package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Adresse;
import com.esprit.achat.services.Interface.AdresseService;
import org.springframework.stereotype.Service;

@Service
public class AdresseServiceIMP extends CrudServiceIMP<Adresse,Integer> implements AdresseService {
}
