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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
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
//            fournisseurList.get(i).setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseurList.get(i).getSocieteId()));
            fournisseurList.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseurList.get(i).getPlanComptableElementId()));
        }
        return fournisseurList;
    }
    @QueryMapping
    public Fournisseur fournisseurById(@Argument String id){
        Fournisseur fournisseur=fournisseurRepository.findById(id).get();
        fournisseur.setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseur.getPlanComptableElementId()));
//        fournisseur.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseur.getSocieteId()));
        return fournisseur;
    }

    @QueryMapping
    public List<Fournisseur> fournisseurByIdSociete(@Argument String idSociete){
        List<Fournisseur> fournisseurList=fournisseurRepository.findAll();
        List<Fournisseur> fournisseursBySociete=new ArrayList<>();
        for(int i=0;i<fournisseurList.size();i++){
            if(fournisseurList.get(i).getSocieteId().equals(idSociete)){
                fournisseursBySociete.add(fournisseurList.get(i));
            }
        }
        for (int i=0;i<fournisseursBySociete.size();i++){
//            fournisseursBySociete.get(i).setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseursBySociete.get(i).getSocieteId()));
            fournisseursBySociete.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseursBySociete.get(i).getPlanComptableElementId()));
        }
        return fournisseursBySociete;
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
                .planComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseurDTO.getPlanComptableElementId()))
                .build();
        fournisseurRepository.save(fournisseur);
        return fournisseur;
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
        fournisseur.setPlanComptableElement(fournisseurDTO.getPlanComptableElementId()==null?
                planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.
                        getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseur.
                        getPlanComptableElementId())
                :
                planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.
                        getRequestAttributes()).getRequest().getHeader("Authorization"),fournisseurDTO.
                        getPlanComptableElementId()));
        fournisseurRepository.save(fournisseur);
        return fournisseur;
    }
    @MutationMapping
    public Fournisseur supprimerFournisseur(@Argument String id){
        Fournisseur fournisseur=fournisseurRepository.findById(id).get();
        fournisseurRepository.delete(fournisseur);
        return fournisseur;
    }
}
