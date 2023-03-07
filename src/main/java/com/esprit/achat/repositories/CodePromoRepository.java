package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.CodePromo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodePromoRepository extends CrudRepository<CodePromo, Integer>{
    public List<CodePromo> findByCode(String codePromo);


}
