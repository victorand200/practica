package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.Product;

@Repository
public interface IProductRepository extends IGenericRepository<Product> {
}
