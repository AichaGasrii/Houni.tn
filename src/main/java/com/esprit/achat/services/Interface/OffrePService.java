package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;

import java.util.List;

public interface OffrePService extends CrudService<OffreProduit, Integer>{
    List<OffreProduit> listeDeproduitParNature(NatureArticle natureArticle);


}
