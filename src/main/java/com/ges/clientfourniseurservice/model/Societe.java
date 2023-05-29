package com.ges.clientfourniseurservice.model;

import lombok.Data;

@Data
public class Societe {
    private String id;
    private String raison_social;
    private String activite;
    private String adresse;
    private CompteUtilisateur compteUtilisateur;
}
