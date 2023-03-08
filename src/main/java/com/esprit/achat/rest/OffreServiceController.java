package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.OffreService;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.OffreSService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/offreService")

@AllArgsConstructor
public class OffreServiceController {

    private OffreSService offreService;
    private AppelOffreService appelOffreService;

    @GetMapping
    List<OffreService> retrieveAll(){
        return offreService.retrieveAll();
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PostMapping("/add")
    void add(@Valid @RequestBody OffreService o){
        if(Objects.nonNull(o.getAppeloffre()) && Objects.nonNull(o.getAppeloffre().getId()) ) {
            AppelOffre appelOffre =  appelOffreService.retrieve(o.getAppeloffre().getId());
            o.setAppeloffre(appelOffre);
        }
        offreService.add(o);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @PutMapping("/edit")
    void update(@Valid @RequestBody OffreService o){
        offreService.update(o);
    }
    @PreAuthorize("hasRole('Fournisseur')")
    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        offreService.remove(id);
    }

    @GetMapping("/{id}")

    OffreService retrieve(@PathVariable("id") Integer id){
        return offreService.retrieve(id);
    }

    @GetMapping("/offers/{id}/qrcode")
    public ResponseEntity<byte[]> generateQRCode(@PathVariable int id) throws Exception {
        // Get the offer data
        OffreService offer = offreService.retrieve(id);

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
