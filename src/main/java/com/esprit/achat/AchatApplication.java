package com.esprit.achat;

import com.esprit.achat.persistence.entity.Commande;
import com.esprit.achat.persistence.enumeration.Etat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.validation.Constraint;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class AchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchatApplication.class, args);
	}

}
