package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.enumeration.OffreType;
import org.springframework.scheduling.annotation.Scheduled;

public interface DemandeAchatService extends CrudService<DemandeAchat, Integer>{

    void nbreAchatParType();


}
