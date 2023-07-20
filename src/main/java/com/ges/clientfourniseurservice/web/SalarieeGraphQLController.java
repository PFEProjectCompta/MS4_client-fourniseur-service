package com.ges.clientfourniseurservice.web;

import com.ges.clientfourniseurservice.dto.FournisseurDTO;
import com.ges.clientfourniseurservice.dto.SalarieeDTO;
import com.ges.clientfourniseurservice.entities.Fournisseur;
import com.ges.clientfourniseurservice.entities.Salariee;
import com.ges.clientfourniseurservice.repository.SalarieeRepository;
import com.ges.clientfourniseurservice.service.PlanComptableRestClientService;
import com.ges.clientfourniseurservice.service.SocieteRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author WIAM
 **/
@Controller
public class SalarieeGraphQLController {
    @Autowired
    private SalarieeRepository salarieeRepository;
    @Autowired
    private SocieteRestClientService societeRestClientService;
    @Autowired
    private PlanComptableRestClientService planComptableRestClientService;
    @QueryMapping
    public Salariee salarieeById(@Argument String id){
        Salariee salariee=salarieeRepository.findById(id).get();
        salariee.setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),salariee.getPlanComptableElementId()));
//        salariee.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),salariee.getSocieteId()));
        return salariee;
    }

    @QueryMapping
    public List<Salariee> salarieeByIdSociete(@Argument String idSociete){
        List<Salariee> salarieeList=salarieeRepository.findAll();
        List<Salariee> salarieesBySociete=new ArrayList<>();
        for(int i=0;i<salarieeList.size();i++){
            if(salarieeList.get(i).getSocieteId().equals(idSociete)){
                salarieesBySociete.add(salarieeList.get(i));
            }
        }
        for (int i=0;i<salarieesBySociete.size();i++){
//            salarieesBySociete.get(i).setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),salarieesBySociete.get(i).getSocieteId()));
            salarieesBySociete.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),salarieesBySociete.get(i).getPlanComptableElementId()));
        }
        return salarieesBySociete;
    }

    @MutationMapping
    public Salariee ajouterSalariee(@Argument SalarieeDTO salarieeDTO){
        Salariee salariee=Salariee.builder()
                .id(UUID.randomUUID().toString())
                .nom(salarieeDTO.getNom())
                .prenom(salarieeDTO.getPrenom())
                .email(salarieeDTO.getEmail())
                .adresse(salarieeDTO.getAdresse())
                .ville(salarieeDTO.getVille())
                .pays(salarieeDTO.getPays())
                .telephone(salarieeDTO.getTelephone())
                .societeId(salarieeDTO.getSocieteId())
                .planComptableElementId(salarieeDTO.getPlanComptableElementId())
                .planComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),salarieeDTO.getPlanComptableElementId()))
                .build();
        salarieeRepository.save(salariee);
        return salariee;
    }
    @MutationMapping
    public Salariee modifierSalariee(@Argument SalarieeDTO salarieeDTO,@Argument String id){
        Salariee salariee=salarieeRepository.findById(id).get();
        salariee.setNom(salarieeDTO.getNom()==null?salariee.getNom():salarieeDTO.getNom());
        salariee.setPrenom(salarieeDTO.getPrenom()==null?salariee.getPrenom():salarieeDTO.getPrenom());
        salariee.setEmail(salarieeDTO.getEmail()==null?salariee.getEmail():salarieeDTO.getEmail());
        salariee.setAdresse(salarieeDTO.getAdresse()==null?salariee.getAdresse():salarieeDTO.getAdresse());
        salariee.setVille(salarieeDTO.getVille()==null?salariee.getVille():salarieeDTO.getVille());
        salariee.setPays(salarieeDTO.getPays()==null?salariee.getPays():salarieeDTO.getPays());
        salariee.setTelephone(salarieeDTO.getTelephone()==null?salariee.getTelephone():salarieeDTO.getTelephone());
        salariee.setSocieteId(salarieeDTO.getSocieteId()==null?salariee.getSocieteId():salarieeDTO.getSocieteId());
        salariee.setPlanComptableElementId(salarieeDTO.getPlanComptableElementId()==null?salariee.getPlanComptableElementId():salarieeDTO.getPlanComptableElementId());
        salariee.setPlanComptableElement(salarieeDTO.getPlanComptableElementId()==null?
                planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.
                        getRequestAttributes()).getRequest().getHeader("Authorization"),salariee.
                        getPlanComptableElementId())
                :
                planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.
                        getRequestAttributes()).getRequest().getHeader("Authorization"),salarieeDTO.
                        getPlanComptableElementId()));
        salarieeRepository.save(salariee);
        return salariee;
    }
    @MutationMapping
    public Salariee supprimerSalariee(@Argument String id){
        Salariee salariee=salarieeRepository.findById(id).get();
        salarieeRepository.delete(salariee);
        return salariee;
    }
}
