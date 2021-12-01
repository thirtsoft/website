package com.website.controllers;

import com.website.controllers.api.UtilisateurGetApi;
import com.website.dtos.UtilisateurGetDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.services.UtilisateurGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurGetController implements UtilisateurGetApi {

    private final UtilisateurGetService utilisateurGetService;

    @Autowired
    public UtilisateurGetController(UtilisateurGetService utilisateurGetService) {
        this.utilisateurGetService = utilisateurGetService;
    }


    @Override
    public ResponseEntity<UtilisateurGetDto> updateUtilisateur(Long Id, UtilisateurGetDto utilisateurGetDto) {
        utilisateurGetDto.setId(Id);
        UtilisateurGetDto utilisateurGetDtoResult = utilisateurGetService.save(utilisateurGetDto);

        return new ResponseEntity<>(utilisateurGetDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UtilisateurGetDto> getUtilisateurById(Long id) throws ResourceNotFoundException {
        UtilisateurGetDto utilisateurGetDtoResult = utilisateurGetService.findById(id);

        return new ResponseEntity<>(utilisateurGetDtoResult, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurGetDto>> getAllUtilisateurs() {
        List<UtilisateurGetDto> utilisateurGetDtoList = utilisateurGetService.findAll();
        return new ResponseEntity<>(utilisateurGetDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UtilisateurGetDto>> getAllUtilisateursOrderDesc() {
        List<UtilisateurGetDto> utilisateurGetDtoList = utilisateurGetService.findAllUtilisateursOrderDesc();
        return new ResponseEntity<>(utilisateurGetDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUtilisateur(Long id) {
        utilisateurGetService.delete(id);
        return ResponseEntity.ok().build();
    }
}
