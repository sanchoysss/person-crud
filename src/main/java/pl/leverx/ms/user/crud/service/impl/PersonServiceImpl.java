package pl.leverx.ms.user.crud.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.leverx.ms.user.crud.dto.PersonDTO;
import pl.leverx.ms.user.crud.entity.Person;
import pl.leverx.ms.user.crud.repository.PersonRepository;
import pl.leverx.ms.user.crud.service.PersonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PersonDTO> getPeople() {
        return personRepository.findAll().stream().map(this::map).toList();
    }

    @Override
    public PersonDTO getPerson(String idNumber) {
        return personRepository.findByIdNumber(idNumber).map(this::map).orElse(null);
    }

    @Override
    public PersonDTO createPerson(PersonDTO personDTO) {
        var personToSave = map(personDTO);
        var savedPerson = personRepository.saveAndFlush(personToSave);
        return map(savedPerson);
    }

    @Override
    @Transactional
    public PersonDTO updatePerson(PersonDTO personDTO) {
        var personToUpdateOptional = personRepository.findByIdNumber(personDTO.idNumber());
        if (personToUpdateOptional.isEmpty()) {
            return null;
        }
        var personToUpdate = personToUpdateOptional.get();
        personToUpdate.setFirstName(personDTO.firstName());
        personToUpdate.setLastName(personDTO.lastName());
        personRepository.save(personToUpdate);
        return map(personToUpdate);
    }

    @Override
    public void deletePerson(String idNumber) {
        personRepository.deleteByIdNumber(idNumber);
    }

    private PersonDTO map(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    private Person map(PersonDTO person) {
        return modelMapper.map(person, Person.class);
    }
}
