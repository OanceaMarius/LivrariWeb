package ro.papetti.livrari.plu.repozitories;

import jakarta.persistence.PersistenceContext;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ro.papetti.pluriva.entity.LimitePartenerClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@PersistenceContext(unitName = "plurivaEntityManagerFactory")

public interface LimitePartenerClientRepozitory extends JpaRepository<LimitePartenerClient, Integer> {

//    @Transactional(readOnly = true)
//    @Query("""
//            select l from LimitePartenerClient l
//            where l.tblLimiteParteneriCLientiPK.partenerId = :partenerId and l.tblLimiteParteneriClientiPK.partenerFirmaId = :partenerFirmaId""")
//    LimitePartenerClient IsBlocat(@Param("partenerId") int partenerId, @Param("partenerFirmaId") int partenerFirmaId);


    @Query("select l from LimitePartenerClient l")
    @EntityGraph(value = "LimitePartenerClient.complet")
    List<LimitePartenerClient> findEagerAll();


//    @Query("select l from LimitePartenerClient l where " +
//            "l.tblLimiteParteneriCLientiPK.partenerId = :partenerId " +
//            "and l.tblLimiteParteneriClientiPK.partenerFirmaId = :partenerFirmaId " +
//            "and l.tblLimiteParteneriClientiPK.divizieId=1")
//    @EntityGraph(value = "LimitePartenerClient.complet")
//    Optional<LimitePartenerClient> findEagerById(@Param("partenerFirmaId") int partenerFirmaId,@Param("partenerId") int partenerId);

    @Query("""
            select l from LimitePartenerClient l
            where l.tblLimiteParteneriClientiPK.partenerFirmaId = :partenerFirmaId 
            and l.tblLimiteParteneriClientiPK.partenerId = :partenerId 
            and l.tblLimiteParteneriClientiPK.divizieId = 1""")
    @EntityGraph(value = "LimitePartenerClient.complet")
    Optional<LimitePartenerClient> findEagerByIdWithDivizieId_1(@Param("partenerFirmaId") int partenerFirmaId, @Param("partenerId") int partenerId);

    @Query("""
            select l from LimitePartenerClient l
            where l.tblLimiteParteneriClientiPK.partenerFirmaId = :partenerFirmaId 
            and l.tblLimiteParteneriClientiPK.partenerId = :partenerId 
            and l.tblLimiteParteneriClientiPK.divizieId = 1""")
    Optional<LimitePartenerClient> findByIdWithDivizieId_1(@Param("partenerFirmaId") int partenerFirmaId, @Param("partenerId") int partenerId);

    @Query(value = "SELECT dbo.ClientBlocat(:Blocat, :ZileVechimeScadenta, :ZileBlocare, :DataDeblocare, :SetareBlocare)", nativeQuery = true)
    boolean clientBlocat(@Param("Blocat") Boolean blocat,
                            @Param("ZileVechimeScadenta") Integer zileVechimeScadenta,
                            @Param("ZileBlocare") Integer zileBlocare,
                            @Param("DataDeblocare") LocalDateTime dataDeblocare,
                            @Param("SetareBlocare") Integer setareBlocare
                            );


}
