package com.ges.clientfourniseurservice.dto;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import com.ges.clientfourniseurservice.model.Societe;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/
@Data@NoArgsConstructor@AllArgsConstructor@Builder
public class SalarieeDTO {
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
