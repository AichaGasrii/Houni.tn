package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.dto.MontantPanier;
import com.esprit.achat.persistence.dto.Panier;
import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.CommandeService;
import com.esprit.achat.services.Interface.QuestionService;
import com.mysql.cj.xdevapi.Client;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CommandeServiceIMP  extends CrudServiceIMP<Commande,Integer> implements CommandeService {
/*
     public Boolean clientfidéle (Client client){

       int nbCommande =  client.getClient().stream()
                .map(Client::getCommande)
                .flatMap(Collection::stream)
                .filter(commande ->
                        commande.getArchive().equals(false) &&
                         ChronoUnit.YEARS.between(commande.getdatefacture()> 1  )
                .map(Commande::getClient)
                .distinct()
                .collect(Collectors.toList())
                .size();
        return nbCommande>2;
}

 */



    public MontantPanier calculMontantPanier(Panier panier) {

        MontantPanier montantPanier = new MontantPanier();
        montantPanier.setMontantTotalHT(0d);
        montantPanier.setMontantTotalAPayer(0d);
        panier.getItems().stream().forEach( item -> {
            Double aux = item.getQuantity() * item.getMontantHt();
            montantPanier.setMontantTotalHT(aux + montantPanier.getMontantTotalHT());
        });
        return montantPanier;
    }
}
