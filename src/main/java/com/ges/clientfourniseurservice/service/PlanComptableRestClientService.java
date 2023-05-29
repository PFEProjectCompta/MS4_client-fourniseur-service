package com.ges.clientfourniseurservice.service;

import com.ges.clientfourniseurservice.model.PlanComptableElement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "plan-comptable-service")
public interface PlanComptableRestClientService {
    @GetMapping("/planComptableElements/{id}?projection=planComptableElementProjection")
    public PlanComptableElement planComptableElementById(@PathVariable String id);
    @GetMapping("/planComptableElements?projection=planComptableElementProjection")
    public PagedModel<PlanComptableElement> allplanComptableElements();
}
