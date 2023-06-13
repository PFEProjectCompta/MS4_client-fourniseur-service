package com.ges.clientfourniseurservice.service;

import com.ges.clientfourniseurservice.model.Bureau;
import com.ges.clientfourniseurservice.model.CompteUtilisateur;
import com.ges.clientfourniseurservice.model.Societe;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "office-service")
public interface SocieteRestClientService {
    @GetMapping("/societes/{id}?projection=societeProjection")
    public Societe SocieteById(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable String id);
    @GetMapping("/societes?projection=societeProjection")
    public PagedModel<Societe> allSocietes(@RequestHeader(value = "Authorization", required = true) String authorizationHeader);
    @GetMapping("/fullSocieteCompteUtilisateur/{id}")
    public CompteUtilisateur fullSocieteCompteUtilisateur(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable String id);
    @GetMapping("/Fullbureau/{id}")
    public Bureau fullExercieSocieteCompteBureauAdmine(@RequestHeader(value = "Authorization", required = true) String authorizationHeader,@PathVariable String id);
}
