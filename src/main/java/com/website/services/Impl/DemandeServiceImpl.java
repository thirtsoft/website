package com.website.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.dtos.DemandeDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Demande;
import com.website.repository.DemandeRepository;
import com.website.services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

    private final Path fileStorageLocation = Paths.get("C:\\Folio9470m\\Website\\Demande");

    private final DemandeRepository demandeRepository;


    @Autowired
    public DemandeServiceImpl(DemandeRepository demandeRepository) {
        this.demandeRepository = demandeRepository;
    }

    @Override
    public DemandeDto save(DemandeDto demandeDto) {
        return DemandeDto.fromEntityToDto(
                demandeRepository.save(
                        DemandeDto.fromDtoToEntity(demandeDto)
                )
        );
    }

    @Override
    public DemandeDto createDemandeWithfile(String demande, MultipartFile fileDemande) throws IOException {
        DemandeDto demandeDtoResult = new ObjectMapper().readValue(demande, DemandeDto.class);
        System.out.println(demandeDtoResult);

        demandeDtoResult.setBaseDeDonnee(fileDemande.getOriginalFilename());

        return DemandeDto.fromEntityToDto(
                demandeRepository.save(
                        DemandeDto.fromDtoToEntity(demandeDtoResult)
                )
        );

    }

    @Override
    public DemandeDto update(Long id, DemandeDto demandeDto) {
        if (!demandeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Demande not found");
        }

        Optional<Demande> demande = demandeRepository.findById(id);

        if (!demande.isPresent()) {
            throw new ResourceNotFoundException("Demande not found");
        }

        DemandeDto demandeDtoResult = DemandeDto.fromEntityToDto(demande.get());
        demandeDtoResult.setNumero(demandeDto.getNumero());
        demandeDtoResult.setFirstName(demandeDto.getFirstName());
        demandeDtoResult.setLastName(demandeDto.getLastName());
        demandeDtoResult.setMobile(demandeDto.getMobile());
        demandeDtoResult.setEmail(demandeDto.getEmail());
        demandeDtoResult.setTypeEtude(demandeDto.getTypeEtude());
        demandeDtoResult.setDirectorThese(demandeDto.getDirectorThese());
        demandeDtoResult.setSpeciality(demandeDto.getSpeciality());
        demandeDtoResult.setSaisiDonnees(demandeDto.getSaisiDonnees());
        demandeDtoResult.setSubjectThese(demandeDto.getSubjectThese());
        demandeDtoResult.setMasqueSaisi(demandeDto.getMasqueSaisi());
        demandeDtoResult.setAnalyseUnvaried(demandeDto.getAnalyseUnvaried());
        demandeDtoResult.setAnalyseBivarie(demandeDto.getAnalyseBivarie());
        demandeDtoResult.setAnalyseMultivariate(demandeDto.getAnalyseMultivariate());
        demandeDtoResult.setPrice(demandeDto.getPrice());
        demandeDtoResult.setNbreJours(demandeDto.getNbreJours());
        demandeDtoResult.setBaseDeDonnee(demandeDto.getBaseDeDonnee());
        demandeDtoResult.setCreatedDate(demandeDto.getCreatedDate());
        demandeDtoResult.setStatus(demandeDto.getStatus());

        return DemandeDto.fromEntityToDto(
                demandeRepository.save(
                        DemandeDto.fromDtoToEntity(demandeDtoResult)
                )
        );
    }

    @Override
    public DemandeDto updateStatusOfDemandeDtoById(String status, String id) {
        Optional<Demande> optionalDemande = demandeRepository.findById(Long.valueOf(id));

        DemandeDto demandeDtoResult = DemandeDto.fromEntityToDto(optionalDemande.get());

        demandeDtoResult.setStatus(status);
        demandeDtoResult.setCreatedDate(new Date());

        return DemandeDto.fromEntityToDto(
                demandeRepository.save(
                        DemandeDto.fromDtoToEntity(demandeDtoResult)
                )
        );
    }

    @Override
    public DemandeDto updatePriceAndNumberOfDayOfDemandeDtoByID(String price, String nbreJours, String id) {

        Optional<Demande> optionalDemande = demandeRepository.findById(Long.valueOf(id));

        DemandeDto demandeDtoResult = DemandeDto.fromEntityToDto(optionalDemande.get());

        demandeDtoResult.setPrice(Double.valueOf(price));
        demandeDtoResult.setNbreJours(Integer.valueOf(nbreJours));
        demandeDtoResult.setCreatedDate(new Date());

        return DemandeDto.fromEntityToDto(
                demandeRepository.save(
                        DemandeDto.fromDtoToEntity(demandeDtoResult)
                )
        );
    }


    @Override
    public BigDecimal countNumberTotalOfDemande() {
        return demandeRepository.countNumberOfDemande();
    }

    @Override
    public BigDecimal countNumberOfDemandeByDay() {
        return demandeRepository.countNumberOfDemandeByDay();
    }

    @Override
    public BigDecimal countNumberOfOrdersInMonth() {
        return demandeRepository.countNumberOfOrdersInMonth();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusPending() {
        return demandeRepository.countNumberOfDemandesByStatusPending();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusRefused() {
        return demandeRepository.countNumberOfDemandesByStatusRefused();
    }

    @Override
    public BigDecimal countNumberOfDemandesByStatusValidated() {
        return demandeRepository.countNumberOfDemandesByStatusValidated();
    }

    @Override
    public DemandeDto findById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Demande> demande = demandeRepository.findById(id);

        return Optional.of(DemandeDto.fromEntityToDto(demande.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<DemandeDto> findAll() {
        return demandeRepository.findAll().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findTop12ByOrderByCreateDateDesc() {
        return demandeRepository.findTop12ByOrderByCreatedDateDesc().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findAllServicesOrderDesc() {
        return demandeRepository.findByOrderByIdDesc().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findAllPendingDemandeOrderByIdDesc() {
        return demandeRepository.findListDemandeByOrderByIdDesc().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findAllRefusedDemandeOrderByIdDesc() {
        return demandeRepository.findListDemandeByStatusRefusedOrderByIdDesc().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DemandeDto> findAllValidatedDemandeOrderByIdDesc() {
        return demandeRepository.findListDemandeByStatusValidatedOrderByIdDesc().stream()
                .map(DemandeDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfDemandeByMonth() {
        return demandeRepository.countNumberOfDemandeByMonth()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<?> countNumberTotalOfDemandeByYear() {
        return demandeRepository.countNumberOfDemandeByYear()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        demandeRepository.deleteById(id);
    }
}
