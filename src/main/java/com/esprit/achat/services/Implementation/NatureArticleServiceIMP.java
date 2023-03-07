package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AutreCharge;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.repositories.NatureArticleRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.NatureArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NatureArticleServiceIMP extends CrudServiceIMP<NatureArticle,Integer> implements NatureArticleService {
    @Autowired
    NatureArticleRepository natureArticleRepository;
    @Autowired
    OffreProduitRepository offreProduitRepository;

    @Override
    public void affecterOffresProduitsANatureArticle(Integer idOffre, Integer idNature){
        // OffreProduit => child
        // On affecte le child au master
        NatureArticle n =natureArticleRepository.findById(idNature).get();
        OffreProduit o =offreProduitRepository.findById(idOffre).get();
        n.getOffreProduits().add(o);
        natureArticleRepository.save(n);
    }


    @Override
    public List<OffreProduit> listeDeproduitParNature(Integer natureId){
        NatureArticle natureArticle= natureArticleRepository.findById(natureId).orElse(null);
        return natureArticle.getOffreProduits();

    }

    public void affecterUnitesAuxNaturesArticles() {
        List<NatureArticle> natureArticles = natureArticleRepository.findAll();
        for (NatureArticle natureArticle : natureArticles) {
            String unité = obtenirUnitePourNatureArticle(natureArticle);
            natureArticle.setUnité(unité);
        }
        natureArticleRepository.saveAll(natureArticles);
    }
    public String obtenirUnitePourNatureArticle(NatureArticle natureArticle) {
       if (natureArticle == null) {
           return "unité introuvable";
       }
        String secteur = natureArticle.getSecteur().trim().toLowerCase();
        switch (secteur) {
            case "fruits":
                return "kg";
            case "produits laitiers":
                return "L";
            case "appareils":
            case "produits de beauté":
                return "pièces";
            case "cables":
                return "mètre";
            default:
                return "unité introuvable";
        }
    }


}
