package br.com.oliveira.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oliveira.data.vo.v1.PersonVO;
import br.com.oliveira.data.vo.v2.PersonVOV2;
import br.com.oliveira.exceptions.ResourceNotFoundException;
import br.com.oliveira.mapper.DozerMapper;
import br.com.oliveira.model.Person;
import br.com.oliveira.repositories.PersonRepository;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        return DozerMapper.parserListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        return DozerMapper.parserObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");

        var entity = DozerMapper.parserObject(person, Person.class);
        var vo = DozerMapper.parserObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with V2!");

        var entity = DozerMapper.parserObject(person, Person.class);
        var vo = DozerMapper.parserObject(repository.save(entity), PersonVOV2.class);

        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parserObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this Id!"));

        repository.delete(entity);
    }
}
