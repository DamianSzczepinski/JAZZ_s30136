package com.westeros.data.repositories;

import com.westeros.data.model.Actor;
import com.westeros.data.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Integer> {
}
