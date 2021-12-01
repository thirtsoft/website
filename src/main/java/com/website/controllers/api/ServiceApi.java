package com.website.controllers.api;

import com.website.dtos.ServiceDto;
import com.website.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static com.website.utilis.Constants.APP_ROOT;

public interface ServiceApi {

    @PostMapping(value = APP_ROOT + "/services/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Service",
            notes = "Cette méthode permet d'enregistrer une Service", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Service a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune Service  crée / modifié")
    })
    ResponseEntity<ServiceDto> createService(@RequestBody ServiceDto serviceDto);

    @PutMapping(value = APP_ROOT + "/services/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Service par son ID",
            notes = "Cette méthode permet de modifier une Service par son ID", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Service a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Service modifié")
    })
    ResponseEntity<ServiceDto> updateService(@PathVariable(value = "id") Long id, @RequestBody ServiceDto serviceDto);

    @GetMapping(value = APP_ROOT + "/services/countNumberTotalOfServices", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre total de Service",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total de Service")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de Service / le nombre est nulle")
    })
    BigDecimal countNumberTotalOfServices();

    @GetMapping(value = APP_ROOT + "/services/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Service par ID",
            notes = "Cette méthode permet de chercher un category par son ID", response = ServiceDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID Service a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Service n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<ServiceDto> getServiceById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/services/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Services",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Services", responseContainer = "List<ServiceDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Services / une liste vide")
    })
    ResponseEntity<List<ServiceDto>> getAllServices();

    @GetMapping(value = APP_ROOT + "/services/allServicesOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Services",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Services", responseContainer = "List<ServiceDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / une liste vide")
    })
    ResponseEntity<List<ServiceDto>> getAllServicesOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/services/delete/{id}")
    @ApiOperation(value = "Supprimer une Service par son ID",
            notes = "Cette méthode permet de supprimer un Service par son ID", response = ServiceDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Service a été supprimé")
    })
    ResponseEntity<?> deleteService(@PathVariable(value = "id") Long id);


}
