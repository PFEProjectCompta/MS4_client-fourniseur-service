package com.ges.clientfourniseurservice;

import com.base.basemodel.dto.PlanComptableDTOKafka;
import com.base.basemodel.dto.SocieteDTOKafka;
import com.ges.clientfourniseurservice.config.InitialData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class ClientFourniseurServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientFourniseurServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return args -> {
//			InitialData.ajouterClient();
//			InitialData.ajouterFournisseur();
		};
	};
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {

		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
	@Bean
	public RedisTemplate<String, SocieteDTOKafka> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, SocieteDTOKafka> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}

	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheManager cacheManager = RedisCacheManager.create(connectionFactory);
		return cacheManager;
	}
	@Bean
	public RedisTemplate<String, PlanComptableDTOKafka> redisTemplatePlan(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, PlanComptableDTOKafka> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory);
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		return template;
	}
}
