package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.*;
import com.esprit.achat.repositories.AppelOffreRepository;
import com.esprit.achat.services.Interface.*;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController

@RequestMapping("/appelOffre")
@AllArgsConstructor
public class AppelOffreController {
    private AppelOffreService appelOffreService;
    private DemandeAchatService demandeAchatService;
    private NatureArticleService natureArticleService;
    private AppelOffreRepository appelOffreRepository;

    private OffreSService offreSService;

    @GetMapping
    List<AppelOffre> retrieveAll() {
        return appelOffreService.retrieveAll();
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PostMapping("/add")
    @CrossOrigin
    void add(@Valid @RequestBody AppelOffre a) {
        if (Objects.nonNull(a.getDemandeAchat()) && Objects.nonNull(a.getDemandeAchat().getId()) && Objects.nonNull(a.getNatureArticle()) && Objects.nonNull(a.getNatureArticle().getId())) {
            DemandeAchat demandeAchat = demandeAchatService.retrieve(a.getDemandeAchat().getId());
            NatureArticle natureArticle = natureArticleService.retrieve(a.getNatureArticle().getId());

            a.setDemandeAchat(demandeAchat);
            a.setNatureArticle(natureArticle);
        }

        appelOffreService.add(a);
    }

    @PreAuthorize("hasRole('Fournisseur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody AppelOffre a) {
        appelOffreService.update(a);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id) {
        appelOffreService.remove(id);
    }

    @GetMapping("/{id}")
    AppelOffre retrieve(@PathVariable("id") Integer id) {
        return appelOffreService.retrieve(id);
    }

    @PreAuthorize("hasRole('Fournisseur')")
    @GetMapping("/desaffecterAppeloffreNatureArticle/{idA}")
    void desaffecterAppeloffreNatureArticle(@PathVariable Integer idA) {
        appelOffreService.desaffecterAppeloffreNatureArticle(idA);
    }

    @GetMapping("/meilleurMatch/{demande}")
    public ResponseEntity<String> trouverMeilleurMatch(@PathVariable ("demande") DemandeAchat demande){
        AppelOffre meilleurMatch = appelOffreService.trouverMeilleurMatch(demande);

        if (meilleurMatch == null) {
            return ResponseEntity.notFound().build();
        }

        String notifMessage = appelOffreService.notif(demande, meilleurMatch);

        return ResponseEntity.ok().body(notifMessage);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PutMapping("/updatePriceAppelOffre/{appelOffre}")
    public AppelOffre updatePriceAppelOffre(@PathVariable ("appelOffre") AppelOffre appelOffre) {

        appelOffre.setPrixTotal(appelOffreService.calculerPrixTotal(appelOffre));
        return appelOffreRepository.save(appelOffre);
    }

    @PostMapping("/accepter")
    public ResponseEntity<String> accepterMatch(@RequestBody AppelOffre match) {
        String message = appelOffreService.accepterMatch(match);
        return ResponseEntity.ok(message);
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
