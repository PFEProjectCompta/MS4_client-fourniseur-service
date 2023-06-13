package com.ges.clientfourniseurservice.web;

import com.ges.clientfourniseurservice.config.InitialData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientFournisseurController {

    @GetMapping("/loadData")
    public String laodData(){
        InitialData.ajouterClient();
        InitialData.ajouterFournisseur();
        return "done";
    }
}
