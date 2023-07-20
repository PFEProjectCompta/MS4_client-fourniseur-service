package com.ges.clientfourniseurservice.entities;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import com.ges.clientfourniseurservice.model.Societe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WIAM
 **/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "salariee")
public class Salariee {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private String ville;
    private String pays;
    private String telephone;
    private String societeId;
    @Transient
    private Societe societe;
    private String planComptableElementId;
    @Transient
    private PlanComptableElement planComptableElement;
}
