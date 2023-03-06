package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;

import java.util.List;

public interface NatureArticleService extends CrudService<NatureArticle, Integer>{


    void affecterOffresProduitsANatureArticle(Integer IdOffre, Integer idNature);


    List<OffreProduit> listeDeproduitParNature(Integer natureId);

    public void affecterUnitesAuxNaturesArticles();

    public String obtenirUnitePourNatureArticle(NatureArticle natureArticle);


}
