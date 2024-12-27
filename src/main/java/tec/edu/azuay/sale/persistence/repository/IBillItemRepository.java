package tec.edu.azuay.sale.persistence.repository;

import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.BillItem;

@Repository
public interface IBillItemRepository extends IGenericRepository<BillItem> {
}
