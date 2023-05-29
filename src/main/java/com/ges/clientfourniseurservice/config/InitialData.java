package com.ges.clientfourniseurservice.config;

import com.ges.clientfourniseurservice.entities.Client;
import com.ges.clientfourniseurservice.entities.Fournisseur;
import com.ges.clientfourniseurservice.model.PlanComptableElement;
import com.ges.clientfourniseurservice.model.Societe;
import com.ges.clientfourniseurservice.repository.ClientRepository;
import com.ges.clientfourniseurservice.repository.FournisseurRepository;
import com.ges.clientfourniseurservice.service.PlanComptableRestClientService;
import com.ges.clientfourniseurservice.service.SocieteRestClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class InitialData {
     private static ClientRepository clientRepository;
     private static FournisseurRepository fournisseurRepository;
     private static SocieteRestClientService societeRestClientService;
     private static PlanComptableRestClientService planComptableRestClientService;
     private static List<Societe> societes;
     private static List<PlanComptableElement> planComptableElements;

    public InitialData(ClientRepository clientRepository, FournisseurRepository fournisseurRepository,
                       SocieteRestClientService societeRestClientService,
                       PlanComptableRestClientService planComptableRestClientService) {
        this.clientRepository = clientRepository;
        this.fournisseurRepository = fournisseurRepository;
        this.societeRestClientService = societeRestClientService;
        this.planComptableRestClientService = planComptableRestClientService;
        societes=societeRestClientService.allSocietes().getContent().stream().toList();
        planComptableElements=planComptableRestClientService.allplanComptableElements().getContent().stream().toList();
    }
    public static void ajouterClient(){
        Random random=new Random();
        for(int i=0;i<10;i++){
            Client client=Client.builder()
                    .id(UUID.randomUUID().toString())
                    .nom("nom"+i)
                    .prenom("prenom"+i)
                    .adresse("addresse"+i)
                    .pays("pay"+i)
                    .ville("ville"+i)
                    .email("nsma@gmail.com")
                    .telephone("09129983289382")
                    .societeId(societes.get(random.nextInt(19)).getId())
                    .planComptableElementId(planComptableElements.get(random.nextInt(19)).getId())
                    .build();
            System.out.println(clientRepository.save(client));
        }
    }
    public static void ajouterFournisseur(){
        Random random=new Random();
        for(int i=0;i<10;i++){
            Fournisseur fournisseur=Fournisseur.builder()
                    .id(UUID.randomUUID().toString())
                    .nom("nom"+i)
                    .prenom("prenom"+i)
                    .adresse("addresse"+i)
                    .pays("pay"+i)
                    .ville("ville"+i)
                    .email("nsma@gmail.com")
                    .telephone("09129983289382")
                    .societeId(societes.get(random.nextInt(19)).getId())
                    .planComptableElementId(planComptableElements.get(random.nextInt(19)).getId())
                    .build();
            System.out.println(fournisseurRepository.save(fournisseur));
        }
    }
}
