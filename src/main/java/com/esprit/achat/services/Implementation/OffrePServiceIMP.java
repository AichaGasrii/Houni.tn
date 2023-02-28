package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.services.Interface.OffrePService;
import org.springframework.stereotype.Service;

@Service
public class OffrePServiceIMP extends CrudServiceIMP<OffreProduit,Integer> implements OffrePService {
}
