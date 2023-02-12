package com.esprit.achat.repositories;

import com.esprit.achat.persistence.entity.ItemFacture;
import com.esprit.achat.persistence.entity.ItemFactureAvoir;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFactureAvoirRepository  extends CrudRepository<ItemFactureAvoir, Integer>{
}
