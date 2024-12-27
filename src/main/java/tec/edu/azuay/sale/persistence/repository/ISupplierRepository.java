package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.Supplier;

import java.util.Optional;

@Repository
public interface ISupplierRepository extends IGenericRepository<Supplier> {
    Optional<Supplier> findSupplierByRuc(String ruc);

}
