package com.website.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.controllers.api.DemandeApi;
import com.website.dtos.DemandeDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.service.ResponseMessage;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class DemandeController implements DemandeApi {

    private final Path fileStorageLocation = Paths.get("C:\\Folio9470m\\website\\Demande//");

    private final String EXTERNAL_FILE_PATH = "C://Users//Folio9470m//website//Demande//";

    private final DemandeService demandeService;

    @Autowired
    ServletContext context;

    @Autowired
    public DemandeController(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    @Override
    public ResponseEntity<DemandeDto> createDemande(DemandeDto demandeDto) {
        if (demandeService.findById(demandeDto.getId()) != null) {
            return new ResponseEntity<>(demandeDto, HttpStatus.BAD_REQUEST);
        }
        demandeDto.setStatus("ENCOURS");
        demandeDto.setNumero("DEMANDE " + Math.random());

        demandeService.save(demandeDto);

        return new ResponseEntity<>(demandeDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> createDemandeWithFile(String demande, MultipartFile fileDemande) throws IOException {
        DemandeDto demandeDto = new ObjectMapper().readValue(demande, DemandeDto.class);
        if (fileDemande != null && !fileDemande.isEmpty()) {
            demandeDto.setBaseDeDonnee(fileDemande.getOriginalFilename());
            fileDemande.transferTo(new File(EXTERNAL_FILE_PATH + fileDemande.getOriginalFilename()));
        }

        demandeDto.setNumero("DEMAND " + Math.random());
        demandeDto.setStatus("ENCOURS");
        demandeDto.setCreatedDate(new Date());
        demandeService.save(demandeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Demande is created");
    }

    @Override
    public ResponseEntity<?> createDemandeWithFileInPath(String demande, MultipartFile fileDemande) throws IOException {
        DemandeDto demandeDto = new ObjectMapper().readValue(demande, DemandeDto.class);
        if (fileDemande != null && !fileDemande.isEmpty()) {
            demandeDto.setBaseDeDonnee(fileDemande.getOriginalFilename());
            fileDemande.transferTo(new File(context.getRealPath("/Fichiers/") + fileDemande.getOriginalFilename()));
        }

        demandeDto.setNumero("DEMAND " + Math.random());
        demandeDto.setStatus("ENCOURS");
        demandeDto.setCreatedDate(new Date());
        demandeService.save(demandeDto);

        return ResponseEntity.status(HttpStatus.CREATED).body("Demande is created");
    }


    @Override
    public ResponseEntity<DemandeDto> updateDemande(Long Id, DemandeDto demandeDto) {
        demandeDto.setId(Id);
        DemandeDto demandeDtoResult = demandeService.save(demandeDto);
        return new ResponseEntity<>(demandeDtoResult, HttpStatus.OK);
    }


    @Override
    public BigDecimal countNumberTotalOfDemandes() {
        return demandeService.countNumberTotalOfDemande();
    }

    @Override
    public BigDecimal countNumberOfDemandeInDay() {
        return demandeService.countNumberOfDemandeByDay();
    }

    @Override
    public BigDecimal countNumberOfOrdersInMonth() {
        return demandeService.countNumberOfOrdersInMonth();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusPending() {
        return demandeService.countNumberOfDemandesByStatusPending();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusRefused() {
        return demandeService.countNumberOfDemandesByStatusRefused();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusValidated() {
        return demandeService.countNumberOfDemandesByStatusValidated();
    }

    @Override
    public ResponseEntity<DemandeDto> getDemandeById(Long id) throws ResourceNotFoundException {
        DemandeDto demandeDtoResult = demandeService.findById(id);
        return new ResponseEntity<>(demandeDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DemandeDto>> getAllDemandes() {
        List<DemandeDto> demandeDtoList = demandeService.findAll();
        return new ResponseEntity<>(demandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DemandeDto>> getAllDemandesOrderDesc() {
        List<DemandeDto> demandeDtoList = demandeService.findAllServicesOrderDesc();
        return new ResponseEntity<>(demandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DemandeDto>> getAllPendingDemandesOrderByIdDesc() {
        List<DemandeDto> demandeDtoList = demandeService.findAllPendingDemandeOrderByIdDesc();
        return new ResponseEntity<>(demandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DemandeDto>> getAllRefusedDemandesOrderByIdDesc() {
        List<DemandeDto> demandeDtoList = demandeService.findAllRefusedDemandeOrderByIdDesc();
        return new ResponseEntity<>(demandeDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DemandeDto>> getAllValidatedDemandesOrderByIdDesc() {
        List<DemandeDto> demandeDtoList = demandeService.findAllValidatedDemandeOrderByIdDesc();
        return new ResponseEntity<>(demandeDtoList, HttpStatus.OK);
    }

    @Override
    public List<?> countNumberTotalOfDemandeByMonth() {
        return demandeService.countNumberTotalOfDemandeByMonth();
    }

    @Override
    public List<?> countNumberTotalOfDemandeByYear() {
        return demandeService.countNumberTotalOfDemandeByYear();
    }

    @Override
    public ResponseEntity<DemandeDto> updateStatusOfDemandeByID(String status, String id) {
        DemandeDto newDemandeDto = demandeService.updateStatusOfDemandeDtoById(status, id);
        return new ResponseEntity<>(newDemandeDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DemandeDto> updatePriceAndNumberOfDayOfDemandeByID(String price, String nbreJours, String id) {
        DemandeDto newDemandeDto = demandeService.updatePriceAndNumberOfDayOfDemandeDtoByID(price, nbreJours, id);
        return new ResponseEntity<>(newDemandeDto, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<?> deleteDemande(Long id) {
        demandeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<ResponseMessage> uploadExcelDemande(MultipartFile demandeFile) {
        return null;
    }

    @Override
    public void uploadDemandeFile(MultipartFile demandeFile, Long id) throws IOException {
        DemandeDto demandeDtoResult = demandeService.findById(id);

        demandeDtoResult.setBaseDeDonnee(demandeFile.getOriginalFilename());
        Files.write(Paths.get(System.getProperty("user.home") + "/website/Demande/" + demandeDtoResult.getBaseDeDonnee()), demandeFile.getBytes());

        demandeService.save(demandeDtoResult);
    }

    @Override
    public ResponseEntity<InputStreamResource> excelDemandesReport() throws IOException {
        return null;
    }

    @Override
    public void downloadDemandeFile(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(EXTERNAL_FILE_PATH + fileName);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @Override
    public void downloadDemandeFileFromPath(HttpServletRequest request, HttpServletResponse response, String fileName) throws IOException {
        File file = new File(Paths.get(context.getRealPath("/Fichiers/")) + fileName);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }


}
