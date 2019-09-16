package id.tech.cakra.etf.repository;
import id.tech.cakra.etf.domain.Portofolio;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Portofolio entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PortofolioRepository extends JpaRepository<Portofolio, Long> {

}
