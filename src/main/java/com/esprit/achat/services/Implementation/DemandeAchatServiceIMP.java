package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.DemandeAchatService;
import org.springframework.stereotype.Service;

@Service
public class DemandeAchatServiceIMP extends CrudServiceIMP<DemandeAchat,Integer> implements DemandeAchatService {
}
