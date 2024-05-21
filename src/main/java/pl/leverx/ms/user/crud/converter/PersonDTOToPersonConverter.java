package pl.leverx.ms.user.crud.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;
import pl.leverx.ms.user.crud.dto.PersonDTO;
import pl.leverx.ms.user.crud.entity.Person;

@Component
public class PersonDTOToPersonConverter extends AbstractConverter<PersonDTO, Person> {
    @Override
    protected Person convert(PersonDTO personDTO) {
        return new Person(
                personDTO.idNumber(),
                personDTO.firstName(),
                personDTO.lastName()
        );
    }
}
