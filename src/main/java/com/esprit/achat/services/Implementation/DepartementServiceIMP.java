package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Departement;
import com.esprit.achat.services.Interface.DepartementService;
import org.springframework.stereotype.Service;

@Service
public class DepartementServiceIMP extends CrudServiceIMP<Departement,Integer> implements DepartementService {
}
