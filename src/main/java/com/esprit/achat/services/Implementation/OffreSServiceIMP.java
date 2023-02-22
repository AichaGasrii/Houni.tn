package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.services.Interface.OffreSService;
import org.springframework.stereotype.Service;

@Service
public class OffreSServiceIMP extends CrudServiceIMP<OffreService,Integer> implements OffreSService {
}
