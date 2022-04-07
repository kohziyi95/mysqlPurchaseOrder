package vttp2022.mysqlpurchaseorder.repositories;

public class Queries {
    //insert queries
    public final static String SQL_INSERT_PURCHASE_ORDER 
        = "insert into purchase_order (order_id, name, order_date) values (? , ? , ?)";
    public final static String SQL_INSERT_LINE_ITEM
        = "insert into line_item (quantity, order_id, prod_id) values (? , ? , ?)";

    //select queries
    public final static String SQL_SELECT_SKU
        = "select * from sku";

    public final static String SQL_SELECT_SKU_BY_DESCRIPTION
         = "select * from sku where description like ? ";
}
