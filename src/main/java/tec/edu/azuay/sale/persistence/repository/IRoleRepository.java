package tec.edu.azuay.sale.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tec.edu.azuay.sale.persistence.models.Role;

import java.util.List;

@Repository
public interface IRoleRepository extends IGenericRepository<Role> {

    @Query("SELECT r FROM Role r join r.users u where u.userId = :idUser")
    List<Role> findAllByUserId(@Param("idUser") Long idUser);
}
