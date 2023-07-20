package com.ges.clientfourniseurservice.repository;

import com.ges.clientfourniseurservice.entities.Salariee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author WIAM
 **/
@RepositoryRestResource
public interface SalarieeRepository extends JpaRepository<Salariee,String> {
}
