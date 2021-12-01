package com.website.services;

import com.website.dtos.DemandeDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface DemandeService {

    DemandeDto save(DemandeDto demandeDto);

    DemandeDto createDemandeWithfile(String demande, MultipartFile fileDemande) throws IOException;

    DemandeDto update(Long id, DemandeDto demandeDto);

    DemandeDto updateStatusOfDemandeDtoById(String status, String id);

    DemandeDto updatePriceAndNumberOfDayOfDemandeDtoByID(String price, String nbreJours, String id);

    BigDecimal countNumberTotalOfDemande();

    BigDecimal countNumberOfDemandeByDay();

    BigDecimal countNumberOfOrdersInMonth();

    BigDecimal countNumberOfDemandesByStatusPending();

    BigDecimal countNumberOfDemandesByStatusRefused();

    BigDecimal countNumberOfDemandesByStatusValidated();

    DemandeDto findById(Long id);

    List<DemandeDto> findAll();

    List<DemandeDto> findTop12ByOrderByCreateDateDesc();

    List<DemandeDto> findAllServicesOrderDesc();

    List<DemandeDto> findAllPendingDemandeOrderByIdDesc();

    List<DemandeDto> findAllRefusedDemandeOrderByIdDesc();

    List<DemandeDto> findAllValidatedDemandeOrderByIdDesc();

    List<?> countNumberTotalOfDemandeByMonth();

    List<?> countNumberTotalOfDemandeByYear();

    void delete(Long id);
}
