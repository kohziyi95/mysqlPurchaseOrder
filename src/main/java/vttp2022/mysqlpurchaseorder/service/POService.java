package vttp2022.mysqlpurchaseorder.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.mysqlpurchaseorder.model.Sku;
import vttp2022.mysqlpurchaseorder.repositories.PORepo;

@Service
public class POService {
    private Logger logger = Logger.getLogger(POService.class.getName());

    @Autowired
    private PORepo repo;

    private Set<String> orderIdSet = new HashSet<>();
    
    public String generateOrderId(){
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++)
        {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }
        String orderId = sb.toString();

        if (orderIdSet.contains(sb.toString())){
            orderId = generateOrderId();
        } else {
            orderIdSet.add(orderId);
        }

        return orderId;
    }

    public List<String> getDescriptionList(){
        List<String> descriptionList = new ArrayList<>();
        List<Sku> skuList = repo.getSKUList();
        for (Sku sku : skuList){
            descriptionList.add(sku.getDescription());
        }
        return descriptionList;
    }

    public String createPurchaseOrder(String name, String orderDate){
        String orderId = generateOrderId();
        
        if (repo.setPurchaseOrder(orderId, name, orderDate)){
            logger.log(Level.INFO, "Order ID generated >>>> " + orderId);
            return orderId;
        } else {
            return null;
        }
    }

    public boolean createLineItem(String orderId, int quantity, String description){
        String prodId = repo.getSKUProductIdByDescription(description).get();
        return (
            repo.setLineItem(quantity, orderId, prodId)
        );
    }


}
