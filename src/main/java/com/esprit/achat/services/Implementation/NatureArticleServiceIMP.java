package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.AppelOffre;
import com.esprit.achat.persistence.entity.NatureArticle;
import com.esprit.achat.services.Interface.AppelOffreService;
import com.esprit.achat.services.Interface.NatureArticleService;
import org.springframework.stereotype.Service;

@Service
public class NatureArticleServiceIMP extends CrudServiceIMP<NatureArticle,Integer> implements NatureArticleService {
}
