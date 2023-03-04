package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.FactureAvoir;
import com.esprit.achat.repositories.FactureRepository;
import com.esprit.achat.services.Interface.FactureavoirService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service

    public class FactureavoirServiceIMP  extends CrudServiceIMP<FactureAvoir,Integer> implements FactureavoirService {

}
