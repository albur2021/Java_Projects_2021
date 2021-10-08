package com.buran.oneToMany.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.buran.oneToMany.models.Player;


	@Repository
	public interface PlayerRepository extends CrudRepository<Player, Long>{
}
