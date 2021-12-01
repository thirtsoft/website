package com.website.controllers.api;

import com.website.dtos.DemandeDto;
import com.website.dtos.EmailDto;
import com.website.dtos.ServiceDto;
import com.website.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.website.utilis.Constants.APP_ROOT;

public interface EmailApi {

    @PostMapping(value = APP_ROOT + "/emails/sendEmailToManager", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Service",
            notes = "Cette méthode permet d'enregistrer une Service", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Service a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune Service  crée / modifié")
    })
    ResponseEntity<EmailDto> sendEmail(@RequestBody EmailDto emailDto) throws MailException;


    @PostMapping(value = APP_ROOT + "/emails/sendEmailToCustomer", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Service",
            notes = "Cette méthode permet d'enregistrer une Service", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Service a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune Service  crée / modifié")
    })
    ResponseEntity<DemandeDto> sendMail(@RequestBody DemandeDto demandeDto) throws MailException;


    @GetMapping(value = APP_ROOT + "/emails/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Email par ID",
            notes = "Cette méthode permet de chercher un category par son ID", response = ServiceDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID Email a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Email n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<EmailDto> getEmailById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/emails/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Services",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Services", responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Services / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllEmails();

    @GetMapping(value = APP_ROOT + "/emails/allEmailsOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des emails par ordre décroissante",
            notes = "Cette méthode permet de chercher et renvoyer la liste des EmailDto", responseContainer = "List<EmailDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Emails / une liste vide")
    })
    ResponseEntity<List<EmailDto>> getAllEmailsOrderDesc();

    @GetMapping(value = APP_ROOT + "/emails/countNumberOfMailInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre d'email dans un mois",
            notes = "Cette méthode permet de renvoyer le nombre d'email dans un mois", responseContainer = "EmailDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le nombre d'email dans un mois / une liste vide")
    })
    BigDecimal countNumberOfEmailInMonth();

    @DeleteMapping(value = APP_ROOT + "/emails/delete/{id}")
    @ApiOperation(value = "Supprimer un Email par son ID",
            notes = "Cette méthode permet de supprimer un Email par son ID", response = EmailDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La EmailDto a été supprimé")
    })
    ResponseEntity<?> deleteEmail(@PathVariable(value = "id") Long id);


}
