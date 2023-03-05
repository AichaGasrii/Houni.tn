package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.repositories.OffreProduitRepository;
import com.esprit.achat.services.Interface.OffrePService;
import com.esprit.achat.services.Interface.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class OffrePServiceIMP extends CrudServiceIMP<OffreProduit,Integer> implements OffrePService {


    @Autowired
    OffreProduitRepository offreProduitRepository;

    @Autowired
    UploadFileService uploadFileService;

    @Value("${file.upload}")
    private String pathFile;

    @Override
    public OffreProduit addOffreProduit(String nom, Boolean disponibilité, Double quantite, MultipartFile image, Double prixUnitaire, AppelOffre appelOffre) {

        // Sauvegarde de l'image
        boolean fileAdded = uploadFileService.addFile(image);
        if (!fileAdded) {
            throw new RuntimeException("Erreur lors de la sauvegarde de l'image.");
        }
        String imagePath = pathFile + image.getOriginalFilename();

        // Création de l'objet OffreProduit
        OffreProduit offreProduit = new OffreProduit();
        offreProduit.setNom(nom);
        offreProduit.setQuantite(quantite);
        offreProduit.setDisponibilité(disponibilité);
        offreProduit.setPhoto(imagePath);
        offreProduit.setPrixUnitaire(prixUnitaire);
        offreProduit.setAppeloffre(appelOffre);

        OffreProduit savedOffreProduit =offreProduitRepository.save(offreProduit);


        return savedOffreProduit;
    }



}
