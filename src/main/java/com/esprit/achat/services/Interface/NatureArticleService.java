package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;

public interface NatureArticleService extends CrudService<NatureArticle, Integer>{

    public void affecterUnitesAuxNaturesArticles();

    public String obtenirUnitePourNatureArticle(NatureArticle natureArticle);

    void affecteroffreproduitANatureArticle(OffreProduit o, int idNatureArticle);
}
