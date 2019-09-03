package id.tech.cakra.etf.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link id.tech.cakra.etf.domain.EtfExecution} entity.
 */
public class EtfExecutionDTO implements Serializable {

    private Long id;

    
    private Integer basketOrderId;

    @Size(max = 10)
    private String stockCode;

    private Float qty;

    private Float qtyOdd;

    private Float price;

    private Float amount;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBasketOrderId() {
        return basketOrderId;
    }

    public void setBasketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Float getQty() {
        return qty;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getQtyOdd() {
        return qtyOdd;
    }

    public void setQtyOdd(Float qtyOdd) {
        this.qtyOdd = qtyOdd;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtfExecutionDTO etfExecutionDTO = (EtfExecutionDTO) o;
        if (etfExecutionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), etfExecutionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EtfExecutionDTO{" +
            "id=" + getId() +
            ", basketOrderId=" + getBasketOrderId() +
            ", stockCode='" + getStockCode() + "'" +
            ", qty=" + getQty() +
            ", qtyOdd=" + getQtyOdd() +
            ", price=" + getPrice() +
            ", amount=" + getAmount() +
            "}";
    }
}
