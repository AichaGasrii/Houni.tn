package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.AppelOffreService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.util.List;

@Service
public class AppelOffreServiceIMP extends CrudServiceIMP<AppelOffre,Integer> implements AppelOffreService {
    @Autowired
    AppelOffreRepository appelOffreRepository;
    @Autowired
    OffreProduitRepository offreProduitRepository;
    @Override
    public void affecterAppleOffreAOffreProduit(Integer idA, Integer idO) {
        AppelOffre appelOffre = appelOffreRepository.findById(idA).orElse(null);
        OffreProduit offreProduit = offreProduitRepository.findById(idO).orElse(null);
        appelOffre.setOffreProduits((List<OffreProduit>) offreProduit);
        appelOffreRepository.save(appelOffre);
    }

    @Override
    public void desaffecterAppeloffreNatureArticle( Integer idA) {

        AppelOffre appelOffre =appelOffreRepository.findById(idA).orElse(null);

        appelOffre.setNatureArticle(null);
        appelOffreRepository.save(appelOffre);

    }

@Override
    public AppelOffre trouverMeilleurMatch(DemandeAchat demande)  {

        AppelOffre meilleurMatch = null;
        double meilleureNote = 2.0;

    List<AppelOffre> offres = appelOffreRepository.findAll();

        for (AppelOffre offre : offres) {
            double note = 0.0;

            if (demande.getNom().equals(offre.getNom())) {
                note += 1.0;
            }

            if (demande.getObjet().equals(offre.getObjet())) {
                note += 1.0;
            }

            if (demande.getQuantiteMin() <= offre.getQuantiteMin()) {
                note += 1.0;
            }

            // autres règles de correspondance

            if (note > meilleureNote) {
                meilleurMatch = offre;
                meilleureNote = note;
            }
        }


        // Envoyer une notification  avec le meilleur match trouvé
       // String message = "Le meilleur match pour la demande d'achat " + demande.getId() + " est l'appel d'offre " + meilleurMatch.getId();
       // Notif(message);

        return meilleurMatch;
    }
    /*

    public void Notif(String message) throws JSONException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "https://api.postman.com/v1/messages";
        String apiKey = "PMAK-63ffbe555bf7502484ece332-afac13d073701843bf753adb14b5fe9d5a";

        JSONObject json = new JSONObject();
        json.put("text", message);

        HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange(url + "?apikey=" + apiKey, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

     */






}
