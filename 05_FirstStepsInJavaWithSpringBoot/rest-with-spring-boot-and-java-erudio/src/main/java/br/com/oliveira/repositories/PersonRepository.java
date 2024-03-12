package br.com.oliveira.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.oliveira.data.vo.v1.PersonVO;

@Repository
public interface PersonRepository extends JpaRepository<PersonVO, Long> {
}
