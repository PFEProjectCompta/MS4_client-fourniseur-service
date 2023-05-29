package com.ges.clientfourniseurservice.model;

import lombok.Data;

import java.util.List;

@Data
public class AdmineBureau {
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private List<Bureau> bureaus;
}
