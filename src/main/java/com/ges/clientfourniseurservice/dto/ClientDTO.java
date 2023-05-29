package com.ges.clientfourniseurservice.dto;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class ClientDTO {
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
