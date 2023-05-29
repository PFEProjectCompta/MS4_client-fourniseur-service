package com.ges.clientfourniseurservice.entities;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import com.ges.clientfourniseurservice.model.Societe;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;
@Projection(name = "fournisseurProjection",types = Fournisseur.class)
public interface FournisseurProjection {
    public String getId();
    public String getNom();
    public String getPrenom();
    public String getEmail();
    public String getAdresse();
    public String getVille();
    public String getPays();
    public String getTelephone();
    public String getSocieteId();
    public List<Societe> getSociete();
    public String getPlanComptableElementId();
    public PlanComptableElement getPlanComptableElement();
}
