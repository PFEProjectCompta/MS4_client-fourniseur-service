package com.ges.clientfourniseurservice.web;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.ges.clientfourniseurservice.service.PlanComptableElementKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * @author WIAM
 **/
@RestController
public class PlanComptableElementKafkaController {
    @Autowired
    private PlanComptableElementKafkaService planComptableElementKafkaService;
    @Autowired
    private RedisTemplate<String, PlanComptableDTOKafka> redisTemplate;

    @GetMapping("allPlanComptableElementKafka")
    public List<PlanComptableDTOKafka> allPlanComptableElementKafka(){
        return planComptableElementKafkaService.allPlanComptableElement();
    }
    @GetMapping("viderRedisePlanComptable")
    public String viderRedisePlanComptable(){
        Set<String> keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);
        return "Done";
    }
}
