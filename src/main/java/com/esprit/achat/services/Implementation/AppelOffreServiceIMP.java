package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.OffreService;
import org.springframework.stereotype.Service;

@Service
public class AppelOffreServiceIMP extends CrudServiceIMP<AppelOffre,Integer> implements AppelOffreService {
}
