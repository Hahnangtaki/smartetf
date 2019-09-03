package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.PortofolioDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Portofolio} and its DTO {@link PortofolioDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PortofolioMapper extends EntityMapper<PortofolioDTO, Portofolio> {



    default Portofolio fromId(Long id) {
        if (id == null) {
            return null;
        }
        Portofolio portofolio = new Portofolio();
        portofolio.setId(id);
        return portofolio;
    }
}
