package com.esprit.achat.services.Interface;

import com.esprit.achat.persistence.entity.AppelOffre;

public interface AppelOffreService extends CrudService<AppelOffre, Integer>{
    public void affecterAppleOffreAOffreProduit(Integer idA, Integer idO);

    void desaffecterAppeloffreNatureArticle(Integer idA);
}
