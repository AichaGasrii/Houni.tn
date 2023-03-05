package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OffrePService extends CrudService<OffreProduit, Integer>{


    OffreProduit addOffreProduit(String nom, Boolean disponibilité, Double quantite, MultipartFile image, Double prixUnitaire, AppelOffre appelOffre);
}
