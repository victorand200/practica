package tec.edu.azuay.sale.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface IGenericRepository<T> extends JpaRepository<T, Long> {
}
