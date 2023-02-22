package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Location;
import com.esprit.achat.services.Interface.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceIMP extends CrudServiceIMP<Location,Integer> implements LocationService {
}
