package com.ges.clientfourniseurservice.model;

import lombok.Data;

@Data
public class PlanComptableElement {
    private String id;
    private String numeroCompte;
    private String intitule;
    private Boolean reporter;
    private CompteGeneral compteGeneral;
    private String societeId;

}
