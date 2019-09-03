package id.tech.cakra.etf.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A EtfExecution.
 */
@Entity
@Table(name = "etf_execution")
public class EtfExecution implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    
    @Column(name = "basket_order_id", unique = true)
    private Integer basketOrderId;

    @Size(max = 10)
    @Column(name = "stock_code", length = 10)
    private String stockCode;

    @Column(name = "qty")
    private Float qty;

    @Column(name = "qty_odd")
    private Float qtyOdd;

    @Column(name = "price")
    private Float price;

    @Column(name = "amount")
    private Float amount;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBasketOrderId() {
        return basketOrderId;
    }

    public EtfExecution basketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
        return this;
    }

    public void setBasketOrderId(Integer basketOrderId) {
        this.basketOrderId = basketOrderId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public EtfExecution stockCode(String stockCode) {
        this.stockCode = stockCode;
        return this;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public Float getQty() {
        return qty;
    }

    public EtfExecution qty(Float qty) {
        this.qty = qty;
        return this;
    }

    public void setQty(Float qty) {
        this.qty = qty;
    }

    public Float getQtyOdd() {
        return qtyOdd;
    }

    public EtfExecution qtyOdd(Float qtyOdd) {
        this.qtyOdd = qtyOdd;
        return this;
    }

    public void setQtyOdd(Float qtyOdd) {
        this.qtyOdd = qtyOdd;
    }

    public Float getPrice() {
        return price;
    }

    public EtfExecution price(Float price) {
        this.price = price;
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getAmount() {
        return amount;
    }

    public EtfExecution amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EtfExecution)) {
            return false;
        }
        return id != null && id.equals(((EtfExecution) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "EtfExecution{" +
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
