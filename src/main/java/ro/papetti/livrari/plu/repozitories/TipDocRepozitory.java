package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.pluriva.dto.DocDto;
import ro.papetti.pluriva.entity.TipDoc;

import java.util.List;
import java.util.Optional;


@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")
public interface TipDocRepozitory extends JpaRepository<TipDoc, Integer> {

    @Query("SELECT t FROM TipDoc t ")
    <T> List<T> findDTOIAll(Class<T> type);

    @Query("select t from TipDoc t where t.tipDocId = :tipDocId")
    <T> Optional<T> findDTOIById(@Param("tipDocId") @NonNull Integer tipDocId, Class<T> type);

    @Query(value = "select   Doc.docId, D.Txt denumireDocument " +
            "FROM  SysErp.ERP.Doc Doc inner JOIN  SysErp.ERP.Crtdictionar('RO') D " +
            "ON D.Dictionar_ID = Doc.DocName_Id where Doc.docId = :docId", nativeQuery = true)
    @Transactional(readOnly = true)
    Optional<ro.papetti.pluriva.dto.DocDto> findDocDtoById(@Param("docId") @NonNull Integer docId);

    @Query(value = "select   Doc.docId, D.Txt denumireDocument " +
            "FROM  SysErp.ERP.Doc Doc inner JOIN  SysErp.ERP.Crtdictionar('RO') D " +
            "ON D.Dictionar_ID = Doc.DocName_Id", nativeQuery = true)
    @Transactional(readOnly = true)
    List<DocDto> findDocDtoAll();
}
