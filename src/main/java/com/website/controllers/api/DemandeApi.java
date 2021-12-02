package com.website.controllers.api;

import com.website.dtos.DemandeDto;
import com.website.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.website.utilis.Constants.APP_ROOT;

public interface DemandeApi {

    @PostMapping(value = APP_ROOT + "/demandes/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une demande",
            notes = "Cette méthode permet d'enregistrer une demande", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La category a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune category  crée / modifié")
    })
    ResponseEntity<DemandeDto> createDemande(@RequestBody DemandeDto demandeDto);

    @PostMapping(value = APP_ROOT + "/demandes/createDemandeWithFile")
    @ApiOperation(value = "Enregistrer une demande",
            notes = "Cette méthode permet d'enregistrer une demande", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Demande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune Demande  crée / modifié")
    })
    ResponseEntity<?> createDemandeWithFile(@RequestPart(name = "demande") String demande,
                                            @RequestParam(name = "file") MultipartFile fileDemande) throws IOException;

    @PostMapping(value = APP_ROOT + "/demandes/createDemandeWithFileInPath")
    @ApiOperation(value = "Enregistrer une demande",
            notes = "Cette méthode permet d'enregistrer une demande", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "La Demande a été crée / modifié"),
            @ApiResponse(code = 400, message = "Aucune Demande  crée / modifié")
    })
    ResponseEntity<?> createDemandeWithFileInPath(@RequestPart(name = "demande") String demande,
                                                  @RequestParam(name = "file") MultipartFile fileDemande) throws IOException;

    @PutMapping(value = APP_ROOT + "/demandes/update/{Id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une demande par son ID",
            notes = "Cette méthode permet de modifier une demande par son ID", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La demande a été modifié"),
            @ApiResponse(code = 400, message = "Aucun demande modifié")
    })
    ResponseEntity<DemandeDto> updateDemande(@PathVariable(value = "Id") Long Id, @RequestBody DemandeDto demandeDto);

/*    @PatchMapping(value = APP_ROOT + "/demandes/updateStatusOfDemande/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Demande par son Status",
            notes = "Cette méthode permet de modifier une Demande par son Status", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Demande a été modifié")
    })
    ResponseEntity<DemandeDto> updateStatusOfDemande(@RequestParam("status") String status, @PathVariable("id") String id);

    @PatchMapping(value = APP_ROOT + "/demandes/updatePriceOfDemande/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Demande par son price",
            notes = "Cette méthode permet de modifier une Demande par son price", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le status de la Demande a été modifié")
    })
    ResponseEntity<DemandeDto> updatePriceOfDemande(@RequestParam("price") String price, @PathVariable("id") String id);*/

    @GetMapping(value = APP_ROOT + "/demandes/countNumberTotalOfDemande", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre total de Demande",
            notes = "Cette méthode permet de chercher et renvoyer le nombre total de Demande")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre total de Demande / le nombre est nulle")
    })
    BigDecimal countNumberTotalOfDemandes();

    @GetMapping(value = APP_ROOT + "/demandes/countNumberOfDemandeInDay", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Demande dans la journée",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Demande dans la journée")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Demande dans la journée / le nombre est nulle")
    })
    BigDecimal countNumberOfDemandeInDay();

    @GetMapping(value = APP_ROOT + "/demandes/countNumberOfDemandeInMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Demande dans le moi",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Demande dans le moi")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Demande dans le moi / le nombre est nulle")
    })
    BigDecimal countNumberOfOrdersInMonth();

    @GetMapping(value = APP_ROOT + "/demandes/countNumberOfDemandeByStatusPending", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Demande encours",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Demande encours")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Demande encours / le nombre est nulle")
    })
    BigDecimal countNumberOfDemandesByStatusPending();

    @GetMapping(value = APP_ROOT + "/demandes/countNumberOfDemandeByStatusRefused", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Demande annuler",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Demande annuler")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Demande annuler / le nombre est nulle")
    })
    BigDecimal countNumberOfDemandesByStatusRefused();

    @GetMapping(value = APP_ROOT + "/demandes/countNumberOfDemandeByStatusValidated", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi le nombre de Demande valider",
            notes = "Cette méthode permet de chercher et renvoyer le nombre de Demande valider")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nombre de Demande valider / le nombre est nulle")
    })
    BigDecimal countNumberOfDemandesByStatusValidated();

    @GetMapping(value = APP_ROOT + "/demandes/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Demande par ID",
            notes = "Cette méthode permet de chercher un category par son ID", response = DemandeDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID Demande a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Demande n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<DemandeDto> getDemandeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/demandes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Demandes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Demandes", responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / une liste vide")
    })
    ResponseEntity<List<DemandeDto>> getAllDemandes();

    @GetMapping(value = APP_ROOT + "/demandes/allDemandesOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Demandes",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Demandes", responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Demandes / une liste vide")
    })
    ResponseEntity<List<DemandeDto>> getAllDemandesOrderDesc();

    @GetMapping(value = APP_ROOT + "/demandes/allPendingDemandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Demandes encours par ordre decroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Demandes encours par ordre decroissant",
            responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Demandes / une liste vide")
    })
    ResponseEntity<List<DemandeDto>> getAllPendingDemandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/demandes/allRefusedDemandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Demandes annuler par ordre decroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Demandes annuler par ordre decroissant",
            responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Demandes / une liste vide")
    })
    ResponseEntity<List<DemandeDto>> getAllRefusedDemandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/demandes/allValidatedDemandesOrderByIdDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Demandes valider par ordre decroissant",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Demandes valider par ordre decroissant",
            responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Demandes / une liste vide")
    })
    ResponseEntity<List<DemandeDto>> getAllValidatedDemandesOrderByIdDesc();

    @GetMapping(value = APP_ROOT + "/demandes/numberTotalOfDemandeByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Demande par moi",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Demande par moi", responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Demande par moi / une liste vide")
    })
    List<?> countNumberTotalOfDemandeByMonth();

    @GetMapping(value = APP_ROOT + "/demandes/numberTotalOfDemandeByYear", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du nombre de Demande par années",
            notes = "Cette méthode permet de chercher et renvoyer la liste du nombre de Demande par années", responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du nombre de Demande par années / une liste vide")
    })
    List<?> countNumberTotalOfDemandeByYear();

    @GetMapping(value = APP_ROOT + "/demandes/sumOfDemandeByMonth", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste du montant de Demande par moi",
            notes = "Cette méthode permet de chercher et renvoyer la liste du montant de Demande par moi", responseContainer = "List<DemandeDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste du montant de Demande par moi / une liste vide")
    })
    List<?> getSumOfDemandeByMonth();

    @PatchMapping(value = APP_ROOT + "/demandes/updateStatusOfDemande/{id}")
    @ApiOperation(value = "Modifier le status d'une demande",
            notes = "Cette méthode permet de modifier le status d'une demande", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le status et le nbre de jours d'une demande a été modifié"),
            @ApiResponse(code = 400, message = "Aucun status end nbreday n'a été modifié")

    })
    ResponseEntity<DemandeDto> updateStatusOfDemandeByID(@RequestParam("status") String status, @PathVariable("id") String id);

    @PatchMapping(value = APP_ROOT + "/demandes/updatePriceAndNumberOfDayOfDemandeByID/{id}")
    @ApiOperation(value = "Modifier le prix et le nbre de jours d'une demande",
            notes = "Cette méthode permet de modifier le prix et le nbre de jours d'une demande", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "le prix de la demande a été modifié"),
            @ApiResponse(code = 400, message = "Aucun prix n'a été modifié")

    })
    ResponseEntity<DemandeDto> updatePriceAndNumberOfDayOfDemandeByID(@RequestParam("price") String price, @RequestParam("nbreJours") String nbreJours, @PathVariable("id") String id);


    @DeleteMapping(value = APP_ROOT + "/demandes/delete/{id}")
    @ApiOperation(value = "Supprimer une Demande par son ID",
            notes = "Cette méthode permet de supprimer un Demande par son ID", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Demande a été supprimé")
    })
    ResponseEntity<?> deleteDemande(@PathVariable(value = "id") Long id);

    @PostMapping(value = APP_ROOT + "/demandes/uploadDemande")
    @ApiOperation(value = "Upload file",
            notes = "Cette méthode permet d'importer le contenu d'un file excel et d'envoyer les données vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fichier a été importer")
    })
    ResponseEntity<ResponseMessage> uploadExcelDemande(@RequestParam("file") MultipartFile demandeFile);

    @PostMapping(value = APP_ROOT + "/demandes/uploadDemandeFile/{id}")
    @ApiOperation(value = "Importer fichier demande",
            notes = "Cette méthode permet d'importer un fichier excel depuis le disque vers la BD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fichier a été importer")
    })
    void uploadDemandeFile(MultipartFile demandeFile, @PathVariable("id") Long id) throws IOException;

    @GetMapping(value = APP_ROOT + "/demandes/download/demandes.xlsx")
    @ApiOperation(value = "Download file",
            notes = "Cette méthode permet d'exporter les données de la BD vers un fichier Excel")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fichier a été exporté")
    })
    ResponseEntity<InputStreamResource> excelDemandesReport() throws IOException;

    @RequestMapping(value = APP_ROOT + "/demandes/downloadDemandeFile/{fileName:.+}")
    @ApiOperation(value = "Télécharger un Demande par son ID",
            notes = "Cette méthode permet de télécharger un Demande par son ID", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Demande a été télécharger")
    })
    void downloadDemandeFile(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable("fileName") String fileName) throws IOException;

    @RequestMapping(value = APP_ROOT + "/demandes/downloadDemandeFileFromPath/{fileName:.+}")
    @ApiOperation(value = "Télécharger un Demande par son ID",
            notes = "Cette méthode permet de télécharger un Demande par son ID", response = DemandeDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Demande a été télécharger")
    })
    void downloadDemandeFileFromPath(HttpServletRequest request, HttpServletResponse response,
                                     @PathVariable("fileName") String fileName) throws IOException;
}
