package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.Bill;

import java.util.Optional;

@Repository
public interface IBillRepository extends IGenericRepository<Bill> {

    Optional<Bill> findOneByRuc(String ruc);
}
