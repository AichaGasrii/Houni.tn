package com.esprit.achat.services.Implementation;

import com.esprit.achat.persistence.entity.Question;
import com.esprit.achat.services.Interface.QuestionService;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceIMP extends CrudServiceIMP<Question,Integer> implements QuestionService {
}
