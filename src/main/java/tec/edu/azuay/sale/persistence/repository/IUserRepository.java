package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends IGenericRepository<User> {

    Optional<User> findOneByUserIgnoreCase(String user);
}
