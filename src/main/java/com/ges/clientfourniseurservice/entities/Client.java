package com.ges.clientfourniseurservice.entities;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import com.ges.clientfourniseurservice.model.Societe;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "client")
public class Client {
    @Id
    private String id;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "email")
    private String email;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "ville")
    private String ville;
    @Column(name = "pays")
    private String pays;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "societe_id")
    private String societeId;
    @Transient
    private Societe societe;
    private String planComptableElementId;
    @Transient
    private PlanComptableElement planComptableElement;
}
