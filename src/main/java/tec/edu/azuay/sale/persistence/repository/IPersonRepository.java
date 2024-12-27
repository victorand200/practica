package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.Person;

import java.util.Optional;

@Repository
public interface IPersonRepository extends IGenericRepository<Person> {
    Optional<Person> findPersonByDni(String dni);
}
