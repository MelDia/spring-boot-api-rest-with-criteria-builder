package com.meldia.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.meldia.restapi.model.VideogameModel;


@Repository
public interface VideogameRepository extends JpaRepository<VideogameModel, Integer> {

	@Query(value ="SELECT * FROM videogame_rest_api.videogame_store WHERE id =:id", nativeQuery = true)
	public VideogameModel findId(Integer id);
	
	@Query(value ="SELECT * FROM videogame_rest_api.videogame_store WHERE name =:name", nativeQuery = true)
	public VideogameModel productExist(String name);
	
}
