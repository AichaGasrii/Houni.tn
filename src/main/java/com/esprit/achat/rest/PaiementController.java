package com.esprit.achat.rest;

import com.esprit.achat.persistence.entity.Paiement;
import com.esprit.achat.services.Interface.PaiementService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.ui.Model;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paiement")
@PreAuthorize("hasRole('User')")
@AllArgsConstructor
public class PaiementController {

    private PaiementService paiementService;

    @GetMapping
    List<Paiement> retrieveAll(){
        return paiementService.retrieveAll();
    }

    @PostMapping("/add")
    void add(@Valid @RequestBody Paiement p){
        paiementService.add(p);
    }

    @PutMapping("/edit")
    void update(@Valid @RequestBody Paiement p){
        paiementService.update(p);
    }

    @DeleteMapping("/delete/{id}")
    void remove(@PathVariable("id") Integer id){
        paiementService.remove(id);
    }

    @GetMapping("/{id}")
    Paiement retrieve(@PathVariable("id") Integer id){
        return paiementService.retrieve(id);
    }

    @PostMapping("/affecter-devise")
    public ResponseEntity<Paiement> affecterDeviseAuxPaiements() {
        paiementService.affecterDeviseAuxPaiements();
        return ResponseEntity.ok().build();
    }
/*
    @Value("${paypal.clientId}")
    private String clientId;

    @Value("${paypal.clientSecret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    private APIContext apiContext;

        @GetMapping("/{prix}")
        public String initierPaiement(@PathVariable("prix") Double prix, Model model) {
            // Créez un objet PayPal avec les identifiants client et secret de votre compte PayPal.
            PayPalEnvironment environment = new PayPalEnvironment.Sandbox(clientId, clientSecret);
            PayPalHttpClient client = new PayPalHttpClient(environment);

            // Créez un objet de paiement.
            Payment payment = new Payment();
            Amount amount = new Amount();
            amount.setCurrency("USD");
            amount.setTotal(prix.toString());
            payment.setIntent("sale");
            payment.setPayer(new Payer("paypal"));
            payment.setTransactions(Collections.singletonList(new Transaction(amount)));

            // Créez l'URL de retour et d'annulation.
            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setCancelUrl("http://example.com/cancel");
            redirectUrls.setReturnUrl("http://example.com/success");
            payment.setRedirectUrls(redirectUrls);

            try {
                // Effectuez le paiement.
                Payment createdPayment = payment.create(apiContext);

                // Récupérez l'URL de redirection vers PayPal.
                String redirectUrl = createdPayment.getLinks().stream()
                        .filter(link -> link.getRel().equals("approval_url"))
                        .findFirst()
                        .get()
                        .getHref();

                // Redirigez l'utilisateur vers PayPal pour autoriser le paiement.
                return "redirect:" + redirectUrl;
            } catch (PayPalRESTException e) {
                // Gérer les erreurs éventuelles.
                e.printStackTrace();
                return "error";
            }
        }

        @GetMapping("/success")
        public String succesPaiement(HttpServletRequest request, HttpServletResponse response) {
            try {
                // Une fois l'utilisateur autorisé le paiement, récupérez le paiement en fournissant le PayerID et l'ID du paiement.
                Payment payment = Payment.get(apiContext, request.getParameter("paymentId"));
                PaymentExecution paymentExecution = new PaymentExecution();
                paymentExecution.setPayerId(request.getParameter("PayerID"));
                payment.execute(apiContext, paymentExecution);

                // Traitez le paiement réussi.
                return "success";
            } catch (PayPalRESTException e) {
                // Gérer les erreurs éventuelles.
                e.printStackTrace();
                return "error";
            }
        }

        @GetMapping("/cancel")
        public String annulerPaiement() {
            // Traitez l'annulation du paiement.
            return "cancel";
        }

 */




}
