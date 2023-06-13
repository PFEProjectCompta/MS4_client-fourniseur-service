package com.ges.clientfourniseurservice.web;

import com.ges.clientfourniseurservice.dto.ClientDTO;
import com.ges.clientfourniseurservice.entities.Client;
import com.ges.clientfourniseurservice.repository.ClientRepository;
import com.ges.clientfourniseurservice.service.PlanComptableRestClientService;
import com.ges.clientfourniseurservice.service.SocieteRestClientService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.UUID;

@Controller
public class ClientGraphQLController {
    private ClientRepository clientRepository;
    private PlanComptableRestClientService planComptableRestClientService;
    private SocieteRestClientService societeRestClientService;

    public ClientGraphQLController(ClientRepository clientRepository,
                                   PlanComptableRestClientService planComptableRestClientService,
                                   SocieteRestClientService societeRestClientService) {
        this.clientRepository = clientRepository;
        this.planComptableRestClientService = planComptableRestClientService;
        this.societeRestClientService = societeRestClientService;
    }
    @QueryMapping
    public List<Client> clientList(){
        List<Client> clientList=clientRepository.findAll();
        for (int i=0;i<clientList.size();i++){
            clientList.get(i).setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),clientList.get(i).getSocieteId()));
            clientList.get(i).setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),clientList.get(i).getPlanComptableElementId()));
        }
        return clientList;
    }
    @QueryMapping
    public Client clientById(@Argument String id){
        Client client=clientRepository.findById(id).get();
        client.setPlanComptableElement(planComptableRestClientService.planComptableElementById(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),client.getPlanComptableElementId()));
        client.setSociete(societeRestClientService.SocieteById(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization"),client.getSocieteId()));
        return client;
    }
    @MutationMapping
    public Client ajouterClient(@Argument ClientDTO clientDTO,@Argument String id){
        Client client=Client.builder()
                .id(UUID.randomUUID().toString())
                .nom(clientDTO.getNom())
                .prenom(clientDTO.getPrenom())
                .email(clientDTO.getEmail())
                .adresse(clientDTO.getAdresse())
                .ville(clientDTO.getVille())
                .pays(clientDTO.getPays())
                .telephone(clientDTO.getTelephone())
                .societeId(clientDTO.getSocieteId())
                .planComptableElementId(clientDTO.getPlanComptableElementId())
                .build();
        return clientRepository.save(client);
    }
    @MutationMapping
    public Client modifierClient(@Argument ClientDTO clientDTO,@Argument String id){
        Client client=clientRepository.findById(id).get();
        client.setNom(clientDTO.getNom()==null?client.getNom():clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom()==null?client.getPrenom():clientDTO.getPrenom());
        client.setEmail(clientDTO.getEmail()==null?client.getEmail():clientDTO.getEmail());
        client.setAdresse(clientDTO.getAdresse()==null?client.getAdresse():clientDTO.getAdresse());
        client.setVille(clientDTO.getVille()==null?client.getVille():clientDTO.getVille());
        client.setPays(clientDTO.getPays()==null?client.getPays():clientDTO.getPays());
        client.setTelephone(clientDTO.getTelephone()==null?client.getTelephone():clientDTO.getTelephone());
        client.setSocieteId(clientDTO.getSocieteId()==null?client.getSocieteId():clientDTO.getSocieteId());
        client.setPlanComptableElementId(clientDTO.getPlanComptableElementId()==null?client.getPlanComptableElementId():clientDTO.getPlanComptableElementId());
        Client clientFull=clientRepository.save(client);
        return clientFull;
    }
    @MutationMapping
    public Client supprimerClient(@Argument String id){
        Client client=clientRepository.findById(id).get();
        clientRepository.delete(client);
        return client;
    }
}
