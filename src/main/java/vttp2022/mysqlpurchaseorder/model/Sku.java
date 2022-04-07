package vttp2022.mysqlpurchaseorder.model;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class Sku {
    private int prodId;
    private String description;
    private Float unitPrice;

    public static Sku create(SqlRowSet result){
        Sku sku = new Sku();
        sku.setProdId(result.getInt("prod_id"));
        sku.setDescription(result.getString("description"));
        sku.setUnitPrice(result.getFloat("unit_price"));
        return sku;
    }

    public int getProdId() {
        return prodId;
    }
    public void setProdId(int prodId) {
        this.prodId = prodId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Float getUnitPrice() {
        return unitPrice;
    }
    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    
}
