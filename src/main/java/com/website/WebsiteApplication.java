package com.website;

import com.website.enums.RoleName;
import com.website.enums.StatusDemande;
import com.website.models.Demande;
import com.website.models.Role;
import com.website.models.Service;
import com.website.repository.DemandeRepository;
import com.website.repository.RoleRepository;
import com.website.repository.ServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@SpringBootApplication
public class WebsiteApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(WebsiteApplication.class);

    private final ServiceRepository serviceRepository;
    private final DemandeRepository demandeRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public WebsiteApplication(ServiceRepository serviceRepository,
                              DemandeRepository demandeRepository,
                              RoleRepository roleRepository) {
        this.serviceRepository = serviceRepository;
        this.demandeRepository = demandeRepository;
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
        createDirectoryIfItDoesntExist();
    }

    private static void createDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/website/Demande/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    @Override
    public void run(String... args) throws Exception {

        Service s1 = serviceRepository.save(new Service(1L, "Stats", "Analyse Statistiques", "asque de Saisie,\n" +
                "Saisie des données, Analyse univariée, Analyse bivarié,\n" +
                "Analyse multivarié", new Date()));
        Service s2 = serviceRepository.save(new Service(2L, "Rv", "Rendez-vous", "Prise de rendez-vous pour les services (Gynécologie, Medecine générale, Ondologies)", new Date()));
        Service s3 = serviceRepository.save(new Service(3L, "Appli", "Applications", "Accompagnement dans la conception et la réalisation d'Application " +
                "de gestion des données médicales", new Date()));
        Service s4 = serviceRepository.save(new Service(4L, "Vent", "Vente", "vente de matériels informatiques et médicales, multiService);\n" +
                "Mise en forme et impressions de documents", new Date()));
      //  Service s5 = serviceRepository.save(new Service(5L, "Mat", "Chirurgie", "Chirurgie", new Date()));

        Demande d1 = demandeRepository.save(new Demande(1L, "D1", "D1", "D1", "D1", "D1", "D1", "D1", "ENCOURS", new Date()));
        Demande d2 = demandeRepository.save(new Demande(2L, "D2", "D2", "D2", "D2", "D2", "D2", "D2", "VALIDER", new Date()));
        Demande d3 = demandeRepository.save(new Demande(3L, "D3", "D3", "D3", "D3", "D3", "D3", "D3", "ENCOURS", new Date()));
        Demande d4 = demandeRepository.save(new Demande(4L, "D4", "D4", "D4", "D4", "D4", "D4", "D4", "ANNULER", new Date()));
        Demande d5 = demandeRepository.save(new Demande(5L, "D5", "D5", "D5", "D5", "D5", "D5", "D5", "VALIDER", new Date()));

        Demande d6 = demandeRepository.save(new Demande(6L, "D6", "D6", "D6", "779440310", "D6@gmail.com", "TH6", "articles.xlsx", "ANNULER", new Date()));
        Demande d7 = demandeRepository.save(new Demande(7L, "D7", "D7", "D7", "779440310", "D7@gmail.com", "TH7", "Administration-des-services-reseau.pdf", "VALIDER", new Date()));
        Demande d8 = demandeRepository.save(new Demande(8L, "D8", "D8", "D8", "779440310", "D8@gmail.com", "TH8", "articles.xlsx", "ENCOURS", new Date()));
        Demande d9 = demandeRepository.save(new Demande(9L, "D9", "D9", "D9", "779440310", "D9@gmail.com", "TH9", "Administration-des-services-reseau.pdf", "ANNULER", new Date()));
        Demande d10 = demandeRepository.save(new Demande(10L, "D10", "D10", "D10", "779440310", "D10@gmail.com", "TH10", "articles.xlsx", "VALIDER", new Date()));


        Role user = roleRepository.save(new Role(1L, RoleName.UTILISATEUR));
        Role gest = roleRepository.save(new Role(2L, RoleName.GESTIONNAIRE));
        Role manager = roleRepository.save(new Role(3L, RoleName.MANAGER));
        Role admin = roleRepository.save(new Role(4L, RoleName.ADMIN));
    }
}
