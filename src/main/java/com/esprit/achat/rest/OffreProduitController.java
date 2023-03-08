package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.DemandeAchat;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.persistence.entity.OffreProduit;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.OffrePService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/offreProduit")

@AllArgsConstructor
public class OffreProduitController {

    private OffrePService offreService;
    private AppelOffreService appelOffreService;

    @GetMapping
    List<OffreProduit> retrieveAll(){
        return offreService.retrieveAll();
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PostMapping("/add")
    void add(@Valid @RequestBody OffreProduit o){

        if(Objects.nonNull(o.getAppeloffre()) && Objects.nonNull(o.getAppeloffre().getId()) ) {
            AppelOffre appelOffre =  appelOffreService.retrieve(o.getAppeloffre().getId());
            o.setAppeloffre(appelOffre);
        }
        offreService.add(o);
    }


    @PreAuthorize("hasRole('Fournisseur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody OffreProduit o){
        offreService.update(o);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreProduit retrieve(@PathVariable("id") Integer id){
        return offreService.retrieve(id);
    }


    @PostMapping("/addavecImage")
    public ResponseEntity<OffreProduit> addOffreProduit(
            @RequestParam("nom") String nom,
            @RequestParam("disponibilité") Boolean disponibilité,
            @RequestParam("quantite") Double quantite,
            @RequestParam("image") MultipartFile image,
            @RequestParam("prixUnitaire") Double prixUnitaire,
            @RequestParam("appelOffreId") Integer appelOffreId){

// récupération de la demande d'achat
        AppelOffre appelOffre = new AppelOffre();
        appelOffre.setId(appelOffreId);

        // appel du service pour ajouter l'offre produit
        OffreProduit savedOffreProduit = offreService.addOffreProduit(nom, disponibilité, quantite, image, prixUnitaire,appelOffre);

        return ResponseEntity.ok(savedOffreProduit);
    }

    @PostMapping("/upload-file")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws Exception{
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        System.out.println(file.getContentType());
        System.out.println(file.getSize());
        String Path_Directory="C:\\Centrale-d-achat-P-I4-me-dev\\src\\main\\resources\\static\\downloadFile";
        Files.copy(file.getInputStream(), Paths.get(Path_Directory+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        return "Successfuly Image is Upload";
    }


    @GetMapping("/offers/{id}/qrcode")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable int id) throws Exception {
        // Get the offer data
        OffreProduit offer = offreService.retrieve(id);

        // Generate the QR code
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BitMatrix matrix = new MultiFormatWriter().encode(offer.getPhoto(), BarcodeFormat.QR_CODE, 200, 200);
        MatrixToImageWriter.writeToStream(matrix, "png", out);

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(out.size());
        headers.set("Content-Disposition", "attachment; filename=\"qrcode.png\"");

        // Return the QR code image as a response
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }


   /* @PostMapping("/affecter-unites")
    public ResponseEntity<OffreProduit> affecterUnitesAuxNaturesArticles(@RequestBody OffreProduit o) {
        offreService.affecterUnitesAuxNaturesArticles(o);
        return ResponseEntity.ok().build();
    }
    */

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
