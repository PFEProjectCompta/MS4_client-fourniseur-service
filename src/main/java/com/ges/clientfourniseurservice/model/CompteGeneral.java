package com.ges.clientfourniseurservice.model;

import com.ges.clientfourniseurservice.enums.NatureCompte;
import lombok.Data;

@Data
public class CompteGeneral {
    private String id;
    private NatureCompte natureCompte;
    private String debutFaurchette;
    private String finFaurchette;
    private String idSociete;

}
