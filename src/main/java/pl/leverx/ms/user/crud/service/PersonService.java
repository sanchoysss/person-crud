package pl.leverx.ms.user.crud.service;

import pl.leverx.ms.user.crud.dto.PersonDTO;

import java.util.List;

public interface PersonService {

    List<PersonDTO> getPeople();

    PersonDTO getPerson(String idNumber);

    PersonDTO createPerson(PersonDTO personDTO);

    PersonDTO updatePerson(PersonDTO personDTO);

    void deletePerson(String idNumber);
}
