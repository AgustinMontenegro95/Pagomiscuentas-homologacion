package ar.com.dinamicaonline.pagomiscuentas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ar.com.dinamicaonline.pagomiscuentas.models.Entidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EntidadRepository extends JpaRepository<Entidad, Integer> {

    @Query("SELECT entidadId FROM Entidad WHERE documento = :idAccount")
    public Integer findByDocumentId(@Param("idAccount") long idAccount);
}
