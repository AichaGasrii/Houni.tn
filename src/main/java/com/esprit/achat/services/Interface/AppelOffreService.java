package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import org.json.JSONException;

import java.util.List;

public interface AppelOffreService extends CrudService<AppelOffre, Integer>{

   // public void affecterAppleOffreAOffreProduit(Integer idA, Integer idO);

    void desaffecterAppeloffreNatureArticle(Integer idA);

    double calculerPrixTotal(AppelOffre appelOffre);

    String notif(DemandeAchat demande, AppelOffre offre);

    AppelOffre trouverMeilleurMatch(DemandeAchat demande) ;

    String accepterMatch(AppelOffre match);
}
