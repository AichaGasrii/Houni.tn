package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.services.Interface.FactureService;
import org.springframework.stereotype.Service;

@Service
public class FactureServiceIMP  extends CrudServiceIMP<Facture,Integer> implements FactureService {


    @Override
    public Double  calculermontantTTC(Facture facture) {
        Double totalttc =0.0;

        // Calcul total ttc de chaque montant ttc de items
        for (ItemFacture itemFacture : facture.getItems()){
            totalttc +=  itemFacture.getMontantTtc();
        }

        return totalttc;
    }








    //  @Scheduled(cron = "*/30 * * * * *")
    /*
    public void retrieveAndUpdateStatusCommande() {

        // Archive all expired commande
        this.archiveExpiredCommande();

        commandeRepository.findByArchiveFalse().stream()
                .filter(commande -> ChronoUnit.DAYS.between(LocalDate.now(),commande.getCommande().getDateCreation()) < 30 )
                .forEach(commande ->
                        log.info(
                                "Commande num: " +commande.getId() +
                                        " de le client " + commande.getClient()+
                                        " expirera aprés 15 jour de cette date " + commande.getDatefacture() +
                                        " / "+ ChronoUnit.DAYS.between(LocalDate.now(),commande.getCommande().getDateCreation())
                        )
                );
    }

    @Transactional
    @Override
    public void archiveExpiredCommande() {
        commandeRepository.findByArchiveFalseAndDateCommande(LocalDate.now())
                .stream()
                .forEach(commande -> commande.setArchive(true));
    }



*/




}
