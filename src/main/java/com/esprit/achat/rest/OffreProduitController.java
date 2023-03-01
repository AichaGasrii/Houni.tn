package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
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
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Objects;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@RestController
@RequestMapping("/offreProduit")
@PreAuthorize("hasRole('Fournisseur')")
@AllArgsConstructor
public class OffreProduitController {

    private OffrePService offreService;
    private AppelOffreService appelOffreService;

    @GetMapping
    List<OffreProduit> retrieveAll(){
        return offreService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@RequestBody OffreProduit o){

        if(Objects.nonNull(o.getAppeloffre()) && Objects.nonNull(o.getAppeloffre().getId()) ) {
            AppelOffre appelOffre =  appelOffreService.retrieve(o.getAppeloffre().getId());
            o.setAppeloffre(appelOffre);
        }
        offreService.add(o);
    }

    @PutMapping("/edit")
    void update(@RequestBody OffreProduit o){
        offreService.update(o);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")
    OffreProduit retrieve(@PathVariable("id") Integer id){
        return offreService.retrieve(id);
    }

    @GetMapping("/produit-nature/{nature}")
    public List<OffreProduit> listeDeproduitParNature(@PathVariable("nature") NatureArticle natureArticle) {
        return offreService.listeDeproduitParNature(natureArticle);
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




}
