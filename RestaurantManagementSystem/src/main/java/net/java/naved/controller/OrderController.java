package net.java.naved.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import net.java.naved.model.Order;
import net.java.naved.service.MenuService;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class OrderController {
	@Autowired
	private MenuService menuService;
	
	public long calculatePriceFromMenu(String itemId) {
	    return menuService.getPriceById(itemId);
	}
	
	public String giveNameFromMenu(String itemId) {
	    return menuService.getNameById(itemId);
	}

    private List<Order> orders = new ArrayList<>();  // Store orders in memory

    @GetMapping("/order")
    public String showOrderForm(Model model) {
        model.addAttribute("orders", orders);
        // Print the stored orders to the console
        for (Order order : orders) {
            System.out.println("Stored Order: Item ID - " + order.getItemId() + ", Quantity - " + order.getQuantity());
        }
        return "order";
    }

    @PostMapping("/place-order")
    public String placeOrder(@RequestParam("itemId[]") String[] itemIds, @RequestParam("quantity[]") int[] quantities) {
        // Process and store the orders
        for (int i = 0; i < itemIds.length; i++) {
            Order order = new Order();
            order.setItemId(itemIds[i]);
            order.setQuantity(quantities[i]);
            long price = calculatePriceFromMenu(itemIds[i]);
            order.setItemPrice(price);
            String name=giveNameFromMenu(itemIds[i]);
            order.setItemName(name);
            long val=quantities[i]*price;
            order.setValue(val);
            orders.add(order);
        }
        // Print the newly added orders to the console
        System.out.println("HI");
        for (Order order : orders) {
            System.out.println("Newly Added Order: Item ID - " + order.getItemId() + ", Quantity - " + order.getQuantity()+",Price - "+order.getItemPrice()+",Name - "+order.getItemName());
        }
        return "redirect:/order";
    }
    
    @PostMapping("/clear-orders")
    public String clearOrders() {
        // Clear all orders stored in the Java class
        orders.clear();
        return "redirect:/order";
    }
    
    @GetMapping("/get-bill")
    public void generateBill(HttpServletResponse response) {
        try {
            
        	List<Order> orderList = orders;
        	long totalValue = 0;  // Initialize the total value to 0

        	for (Order order : orderList) {
        	    totalValue += order.getValue();  // Add the value of the current order to the total
        	}
        	
        	double gst=0.09*totalValue;
        	
        	String filePath="C:\\Users\\ssyed\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\RestaurantManagementSystem\\src\\main\\resources\\Bill.jrxml";
    		
    		JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(orderList);
    		
    		Map<String,Object> parameters=new HashMap<String,Object>();
    		parameters.put("gst", gst);
    		parameters.put("code", "1AZ");
    		parameters.put("billno", "140");
    		parameters.put("tableno","5");
    		parameters.put("date", "12-10-23");
    		parameters.put("dataset",dataSource);
    		
    		
    		JasperReport report=JasperCompileManager.compileReport(filePath);
    		
    		JasperPrint print=JasperFillManager.fillReport(report,parameters,new JREmptyDataSource());
    		
    		JasperExportManager.exportReportToPdfFile(print,"C:\\Users\\ssyed\\OneDrive\\Desktop\\SCOREME\\bill.pdf");
    		//PDF EXPORT
    		response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bill.pdf");

            // Write the PDF data to the response output stream
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
    	    
    	    
    	    
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception while creating report");
        }
    }
    
}
