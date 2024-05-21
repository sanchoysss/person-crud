package pl.leverx.ms.user.crud.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.leverx.ms.user.crud.dto.PersonDTO;
import pl.leverx.ms.user.crud.service.PersonService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getPeople() {
        return ResponseEntity.ok(personService.getPeople());
    }

    @GetMapping(path = "/{idNumber}")
    public ResponseEntity<PersonDTO> getPersonByIdNumber(@PathVariable String idNumber) {
        return ResponseEntity.ok(personService.getPerson(idNumber));
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO person) {
        return ResponseEntity
                .created(URI.create("/api/v1/people/" + person.idNumber()))
                .body(personService.createPerson(person));
    }

    @PutMapping
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonDTO person) {
        return ResponseEntity.ok(personService.updatePerson(person));
    }

    @DeleteMapping(path = "/{idNumber}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable String idNumber) {
        personService.deletePerson(idNumber);
        return ResponseEntity.noContent().build();
    }
}
