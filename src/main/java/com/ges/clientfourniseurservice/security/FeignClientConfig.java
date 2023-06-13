package com.ges.clientfourniseurservice.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig  { //implements RequestInterceptor

//    @Override
//    public void apply(RequestTemplate requestTemplate) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
//            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
//            String basicAuth = "Basic " + Base64Utils.encodeToString((token.getName() + ":" + token.getCredentials()).getBytes());
//            requestTemplate.header("Authorization", basicAuth);
//            System.out.println("hi : "+basicAuth);
//        }
//    }


//    @Bean
//    Logger.Level feignLoggerLevel() {
//        return Logger.Level.FULL;
//    }



}
