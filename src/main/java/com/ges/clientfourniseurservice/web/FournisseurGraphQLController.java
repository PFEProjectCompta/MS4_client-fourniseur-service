package com.ges.clientfourniseurservice.web;

import com.ges.clientfourniseurservice.dto.FournisseurDTO;
import com.ges.clientfourniseurservice.entities.Client;
import com.ges.clientfourniseurservice.entities.Fournisseur;
import com.ges.clientfourniseurservice.repository.FournisseurRepository;
import com.ges.clientfourniseurservice.service.PlanComptableRestClientService;
import com.ges.clientfourniseurservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class FournisseurGraphQLController {
    private FournisseurRepository fournisseurRepository;
    private PlanComptableRestClientService planComptableRestClientService;
    private SocieteRestClientService societeRestClientService;

    public FournisseurGraphQLController(FournisseurRepository fournisseurRepository,
                                        PlanComptableRestClientService planComptableRestClientService,
                                        SocieteRestClientService societeRestClientService) {
        this.fournisseurRepository = fournisseurRepository;
        this.planComptableRestClientService = planComptableRestClientService;
        this.societeRestClientService = societeRestClientService;
    }
    @QueryMapping
    public List<Fournisseur> fournisseurList(){
        List<Fournisseur> fournisseurList=fournisseurRepository.findAll();
        for (int i=0;i<fournisseurList.size();i++){
            fournisseurList.get(i).setSociete(societeRestClientService.SocieteById(fournisseurList.get(i).getSocieteId()));
            fournisseurList.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(fournisseurList.get(i).getPlanComptableElementId()));
        }
        return fournisseurList;
    }
    @QueryMapping
    public Fournisseur fournisseurById(@Argument String id){
        Fournisseur fournisseur=fournisseurRepository.findById(id).get();
        fournisseur.setPlanComptableElement(planComptableRestClientService.planComptableElementById(fournisseur.getPlanComptableElementId()));
        fournisseur.setSociete(societeRestClientService.SocieteById(fournisseur.getSocieteId()));
        return fournisseur;
    }
    @MutationMapping
    public Fournisseur ajouterFournisseur(@Argument FournisseurDTO fournisseurDTO){
        Fournisseur fournisseur=Fournisseur.builder()
                .id(UUID.randomUUID().toString())
                .nom(fournisseurDTO.getNom())
                .prenom(fournisseurDTO.getPrenom())
                .email(fournisseurDTO.getEmail())
                .adresse(fournisseurDTO.getAdresse())
                .ville(fournisseurDTO.getVille())
                .pays(fournisseurDTO.getPays())
                .telephone(fournisseurDTO.getTelephone())
                .societeId(fournisseurDTO.getSocieteId())
                .planComptableElementId(fournisseurDTO.getPlanComptableElementId())
                .build();
        return fournisseurRepository.save(fournisseur);
    }
    @MutationMapping
    public Fournisseur modifierFournisseur(@Argument FournisseurDTO fournisseurDTO,@Argument String id){
        Fournisseur fournisseur=fournisseurRepository.findById(id).get();
        fournisseur.setNom(fournisseurDTO.getNom()==null?fournisseur.getNom():fournisseurDTO.getNom());
        fournisseur.setPrenom(fournisseurDTO.getPrenom()==null?fournisseur.getPrenom():fournisseurDTO.getPrenom());
        fournisseur.setEmail(fournisseurDTO.getEmail()==null?fournisseur.getEmail():fournisseurDTO.getEmail());
        fournisseur.setAdresse(fournisseurDTO.getAdresse()==null?fournisseur.getAdresse():fournisseurDTO.getAdresse());
        fournisseur.setVille(fournisseurDTO.getVille()==null?fournisseur.getVille():fournisseurDTO.getVille());
        fournisseur.setPays(fournisseurDTO.getPays()==null?fournisseur.getPays():fournisseurDTO.getPays());
        fournisseur.setTelephone(fournisseurDTO.getTelephone()==null?fournisseur.getTelephone():fournisseurDTO.getTelephone());
        fournisseur.setSocieteId(fournisseurDTO.getSocieteId()==null?fournisseur.getSocieteId():fournisseurDTO.getSocieteId());
        fournisseur.setPlanComptableElementId(fournisseurDTO.getPlanComptableElementId()==null?fournisseur.getPlanComptableElementId():fournisseurDTO.getPlanComptableElementId());
        return fournisseurRepository.save(fournisseur);
    }
    @MutationMapping
    public Fournisseur supprimerFournisseur(@Argument String id){
        Fournisseur fournisseur=fournisseurRepository.findById(id).get();
        fournisseurRepository.delete(fournisseur);
        return fournisseur;
    }
}
