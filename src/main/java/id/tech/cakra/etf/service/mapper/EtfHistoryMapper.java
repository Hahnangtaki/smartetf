package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfHistory} and its DTO {@link EtfHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtfHistoryMapper extends EntityMapper<EtfHistoryDTO, EtfHistory> {



    default EtfHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfHistory etfHistory = new EtfHistory();
        etfHistory.setId(id);
        return etfHistory;
    }
}
