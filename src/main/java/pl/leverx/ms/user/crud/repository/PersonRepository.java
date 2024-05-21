package pl.leverx.ms.user.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.leverx.ms.user.crud.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByIdNumber(String idNumber);

    void deleteByIdNumber(String idNumber);
}
