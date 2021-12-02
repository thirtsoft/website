package com.website.repository;

import com.website.models.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {

    @Query("select count(p) from Demande p ")
    BigDecimal countNumberOfDemande();

    @Query("select count(c) from Demande c where c.createdDate > current_date")
    BigDecimal countNumberOfDemandeByDay();

    @Query("select count(c) from Demande c where month(c.createdDate) = month(current_date)")
    BigDecimal countNumberOfOrdersInMonth();

    @Query("select count(c) from Demande c where c.status = 'ENCOURS' ")
    BigDecimal countNumberOfDemandesByStatusPending();

    @Query("select count(c) from Demande c where c.status = 'ANNULER' ")
    BigDecimal countNumberOfDemandesByStatusRefused();

    @Query("select count(c) from Demande c where c.status = 'VALIDER' ")
    BigDecimal countNumberOfDemandesByStatusValidated();

    List<Demande> findTop12ByOrderByCreatedDateDesc();

    List<Demande> findByOrderByIdDesc();

    @Query("select d from Demande d where d.status = 'ENCOURS' order by id Desc ")
    List<Demande> findListDemandeByOrderByIdDesc();

    @Query("select d from Demande d where d.status = 'ANNULER' order by id Desc ")
    List<Demande> findListDemandeByStatusRefusedOrderByIdDesc();

    @Query("select d from Demande d where d.status = 'VALIDER' order by id Desc ")
    List<Demande> findListDemandeByStatusValidatedOrderByIdDesc();

    @Query("select EXTRACT(month from(v.createdDate)), count(v) from Demande v group by EXTRACT(month from(v.createdDate))")
    List<?> countNumberOfDemandeByMonth();

    @Query("select EXTRACT(year from(v.createdDate)), count(v) from Demande v group by EXTRACT(year from(v.createdDate))")
    List<?> countNumberOfDemandeByYear();

    @Query("select EXTRACT(month from(c.createdDate)), sum(c.price) from Demande c group by EXTRACT(month from(c.createdDate))")
    List<?> sumTotalOfDemandeByMonth();


}
