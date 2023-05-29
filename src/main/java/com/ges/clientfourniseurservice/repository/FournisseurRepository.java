package com.ges.clientfourniseurservice.repository;

import com.ges.clientfourniseurservice.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface FournisseurRepository extends JpaRepository<Fournisseur,String > {
}
