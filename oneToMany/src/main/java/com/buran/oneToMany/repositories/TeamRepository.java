package com.buran.oneToMany.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.buran.oneToMany.models.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long>{
	
}
