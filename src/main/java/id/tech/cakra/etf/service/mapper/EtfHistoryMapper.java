package id.tech.cakra.etf.service.mapper;

import id.tech.cakra.etf.domain.*;
import id.tech.cakra.etf.service.dto.EtfHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EtfHistory} and its DTO {@link EtfHistoryDTO}.
 */
@Mapper(componentModel = "spring", uses = {EtfProductMapper.class})
public interface EtfHistoryMapper extends EntityMapper<EtfHistoryDTO, EtfHistory> {

    @Mapping(source = "etfProduct.id", target = "etfProductId")
    EtfHistoryDTO toDto(EtfHistory etfHistory);

    @Mapping(source = "etfProductId", target = "etfProduct")
    EtfHistory toEntity(EtfHistoryDTO etfHistoryDTO);

    default EtfHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        EtfHistory etfHistory = new EtfHistory();
        etfHistory.setId(id);
        return etfHistory;
    }
}
