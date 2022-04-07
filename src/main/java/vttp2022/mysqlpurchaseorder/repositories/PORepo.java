package vttp2022.mysqlpurchaseorder.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.mysqlpurchaseorder.model.Sku;

import static vttp2022.mysqlpurchaseorder.repositories.Queries.*;

@Repository
public class PORepo {
    private Logger logger = Logger.getLogger(PORepo.class.getName());

    @Autowired
    private JdbcTemplate template;

    public boolean setPurchaseOrder(String orderId, String name, String orderDate){
        int added = template.update(SQL_INSERT_PURCHASE_ORDER,orderId, name, orderDate);
        return added>0;
    }

    public boolean setLineItem(int quantity, String orderId, String prodId){
        int added = template.update(SQL_INSERT_LINE_ITEM, quantity, orderId, prodId);
        return added>0;
    }

    public Optional<String> getSKUProductIdByDescription(String description) {
        final SqlRowSet results = template.queryForRowSet(SQL_SELECT_SKU_BY_DESCRIPTION, description);
        if (!results.next())
            return Optional.empty();

        return Optional.of(results.getString("prod_id"));
    }

    public List<Sku> getSKUList(){
        List<Sku> skuList = new ArrayList<>();
        final SqlRowSet results = template.queryForRowSet(SQL_SELECT_SKU);
        while (results.next()){
            Sku sku = Sku.create(results);
            skuList.add(sku);
        }
        return skuList; 
    }
}
