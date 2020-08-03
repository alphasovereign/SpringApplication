package com.routing.example.app.repsitory;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.routing.example.app.model.TT;


@Repository
public interface MySqlRepo extends CrudRepository<TT, Integer> {}
