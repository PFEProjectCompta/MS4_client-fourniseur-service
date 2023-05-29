package com.ges.clientfourniseurservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class FournisseurDTO {
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;
    private String societeId;
    private String planComptableElementId;
}
