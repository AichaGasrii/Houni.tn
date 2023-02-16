package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.Unité;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.UnitéService;
import org.springframework.stereotype.Service;

@Service
public class UnitéServiceIMP extends CrudServiceIMP<Unité,Integer> implements UnitéService {
}
