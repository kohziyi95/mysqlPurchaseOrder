package vttp2022.mysqlpurchaseorder.controller;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp2022.mysqlpurchaseorder.service.POService;

@Controller
@RequestMapping("/")
public class POController {
    private Logger logger = Logger.getLogger(POController.class.getName());

    @Autowired
    private POService service;

    @GetMapping("/")
    public String showIndex(Model model){
        model.addAttribute("descriptionList", service.getDescriptionList());
        return "poForm";
    }

    @PostMapping("/purchaseOrder")
    public String showPurchaseOrder(Model model, @RequestBody MultiValueMap<String,String> payload){
        String name = payload.getFirst("name");
        String orderDate = payload.getFirst("date");
        String orderId = service.createPurchaseOrder(name, orderDate);
        for (int i = 0; i < 4; i++){
            String itemName = "item" + (i+1);
            String qtyName = "qty" + (i+1);
            if (!payload.getFirst(itemName).isEmpty()){
                String description = payload.getFirst(itemName);
                int quantity = Integer.parseInt(payload.getFirst(qtyName));
                service.createLineItem(orderId, quantity, description);
            }
        }
    return "showPo";
    }
}
