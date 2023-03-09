package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Livraison;
import com.esprit.achat.persistence.entity.Livreur;
import com.esprit.achat.persistence.entity.Reclamation;
import com.esprit.achat.services.Interface.LivraisonService;
import com.esprit.achat.services.Interface.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Livreur")
public class LivreurController {

   @Autowired
    private LivraisonService ls ;


    @Autowired
    private LivreurService livreurService;


    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getLivreurMaxLikesForYear/{annee}")
    public String getLivreurMaxLikesForYear(@PathVariable int annee)
    {
       return livreurService.getLivreurMaxLikesForYear(annee);
    }

/*
    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/supprimerLivreurplusDislike/{annee}")
    void supprimerLivreurPlusDislike(@PathVariable int annee)
    {
        livreurService.supprimerLivreurPlusDislike(annee);
    }*/

    @PreAuthorize("hasRole('User')")
    @PostMapping("/addLike/{id}")
   public ResponseEntity<Livreur> ajouterLike(@PathVariable Long id)
    {
        return livreurService.ajouterLike(id);
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping("/addDislike/{id}")
    public  ResponseEntity<Livreur> ajouterdisLike(@PathVariable Long id)
    {
        return livreurService.ajouterdisLike(id);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/verifierPrimeLivreur/{l}")
    String verifierPrimeLivreur(@PathVariable Long l)
    {
        return livreurService.verifierPrimeLivreur(l);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/add")
    String add(@Valid @RequestBody Livreur n )
    {
        n.setDateCreation(LocalDate.now());
        livreurService.add(n);
        return ("l'ajout du livreur "+ n.getNom()+" " +
                "c'est fait avec succées ,il est disponible a travailler seulement "+n.getHoraireTravail()+"");
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody Livreur n) {
        livreurService.update(n);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/trierparnote")
    public ResponseEntity<List<Livreur>> trierLivreurParNoteMoyenne() {

        List<Livreur> livreurs = livreurService.trierLivreurParNoteMoyenne();

        return new ResponseEntity<>(livreurs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping("/{idLivraison}/note/{note}")
    public ResponseEntity<String> ajouterNoteLivreur(@PathVariable Long idLivraison, @PathVariable int note) {
        String result = livreurService.ajouterNoteLivreur(idLivraison, note);
        return ResponseEntity.ok(result);
    }
    @PreAuthorize("hasRole('User')")
    @PostMapping("/Chercherlivreur/{id}")
    public String chercherLivreurDisponible(@PathVariable Long id)
    {
        return livreurService.chercherLivreurDisponible(id);
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/getLivreur")
    List<Livreur> retrieveAll() {
        return livreurService.retrieveAll();

    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Long id) {
        livreurService.remove(id);
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    Livreur retrieve(@PathVariable("id") Long id) {
        return livreurService.retrieve(id);
    }

    @ControllerAdvice
    public class CommandeControllerAdvice {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ResponseBody
        public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return errors;
        }
    }

}
