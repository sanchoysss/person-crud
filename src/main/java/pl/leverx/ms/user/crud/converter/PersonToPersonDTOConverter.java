package pl.leverx.ms.user.crud.converter;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;
import pl.leverx.ms.user.crud.dto.PersonDTO;
import pl.leverx.ms.user.crud.entity.Person;

@Component
public class PersonToPersonDTOConverter extends AbstractConverter<Person, PersonDTO> {
    @Override
    protected PersonDTO convert(Person person) {
        return new PersonDTO(
                person.getIdNumber(),
                person.getFirstName(),
                person.getLastName()
        );
    }
}
