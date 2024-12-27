package tec.edu.azuay.sale.service.interfaces;

import java.util.List;

public interface IGenericService<T, U> {

    T save(U item);

    T getById(Long itemId);

    List<T> getAllItems();

    T updateItem(U item, Long itemId);

    void deleteItem(Long itemId);
}
