package br.com.oliveira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.oliveira.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
