package com.esprit.achat.services.Interface;
import com.esprit.achat.persistence.entity.NatureArticle;

public interface NatureArticleService extends CrudService<NatureArticle, Integer>{

    public void affecterUnitesAuxNaturesArticles();

    public String obtenirUnitePourNatureArticle(NatureArticle natureArticle);
}
