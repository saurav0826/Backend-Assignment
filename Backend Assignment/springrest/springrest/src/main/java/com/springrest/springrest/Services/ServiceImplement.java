package com.springrest.springrest.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.springrest.springrest.Entities.Customer;
import com.springrest.springrest.Entities.PurchaseOrder;
import com.springrest.springrest.Entities.ShippingDetails;
@Service
public class ServiceImplement implements Services {

    List<Customer> customerData;
    List<PurchaseOrder> PurchaseOrderData;
    List<ShippingDetails> ShippingDetailsData;
    List<Customer> SelectedCustomerData;
    Map<Customer, List<PurchaseOrder>> CustomersWRTpurchase;
    Map<Customer, Map<PurchaseOrder, ShippingDetails>> AllData;

    public ServiceImplement()
    {
        // Example data case.
        customerData=new ArrayList<>();
        customerData.add(new Customer("saurav","ss14@smail.iitm.ac.in", "9356641616", "Nanded", 1));
        customerData.add(new Customer("shinde","smk10@gmail.com","9518732066", "Bhubaneswar", 2));
        PurchaseOrderData = new ArrayList<>();
        PurchaseOrderData.add(new PurchaseOrder("a", (long) 500, 68.25, 70, 123, 2));
        PurchaseOrderData.add(new PurchaseOrder("b", (long) 50, 8.75, 10, 987, 2));
        PurchaseOrderData.add(new PurchaseOrder("c", (long) 10, 68.25, 70, 321, 1));
        PurchaseOrderData.add(new PurchaseOrder("d", (long) 200, 8.75, 10, 789, 1));
        ShippingDetailsData= new ArrayList<>();
        ShippingDetailsData.add(new ShippingDetails("465654, alsidgncflaxbd", "Bhubaneswar", "9871652", 123, 2));
        ShippingDetailsData.add(new ShippingDetails("465654, alsidgncflaxbd", "Bhubaneswar", "9871652", 987, 2));
        ShippingDetailsData.add(new ShippingDetails("89, alsdk;ghfak;jdshf", "Nanded", "654465", 321, 1));
        ShippingDetailsData.add(new ShippingDetails("89, alsdk;ghfak;jdshf", "Nanded", "654465", 789, 1));
    }

    @Override
    public List<Customer> getCustomers(){
        return customerData;
    }

    @Override
    public Customer getCustomer(long customer_id) {
        
        Customer c=null;
        for(Customer local:customerData)
        {
            if(local.getCustomer_Id()==customer_id)
            {
                c=local;
                break;
            }
        }
        return c;
    }

    @Override // Q1
    public Customer AddCustomer(Customer x) {
        customerData.add(x);
        return x;
    }

    @Override // Q2
    public List<PurchaseOrder> getPurchaseOrders() {
        return PurchaseOrderData;
    }

    @Override 
    public PurchaseOrder AddPurchaseOrder(PurchaseOrder x) {
        PurchaseOrderData.add(x);
        return x;
    }

    @Override // Q3
    public List<ShippingDetails> getShippingDetails() {
        return ShippingDetailsData;
    }

    @Override // Q4
    public List<Customer> getCusomersInCity(String c) {
        SelectedCustomerData= new ArrayList<>();
        for(Customer local:customerData)
        {
            if(local.getCity().toLowerCase()==c.toLowerCase())
            {
                SelectedCustomerData.add(local);
            }
        }
        return SelectedCustomerData;
    }

    @Override // Q5
    public Map<Customer, List<PurchaseOrder>> getCustomersWRTpurchase() {
        CustomersWRTpurchase=new HashMap<>();
        for(Customer Cus:customerData)
        {
            List<PurchaseOrder> localList=new ArrayList<>();
            for(PurchaseOrder order:PurchaseOrderData)
            {
                if(Cus.getCustomer_Id()==order.getCustomerId())
                {
                    localList.add(order);
                }
            }
            CustomersWRTpurchase.put(Cus, localList);
        }
        return CustomersWRTpurchase;
    }

    @Override // Q6
    public Map<Customer, Map<PurchaseOrder, ShippingDetails>> getFullData() {
        AllData=new HashMap<>();
        for(Customer cus: customerData)
        {
            Map<PurchaseOrder, ShippingDetails> LocalData= new HashMap<>();
            for(PurchaseOrder puo: PurchaseOrderData)
            {
                if(cus.getCustomer_Id()==puo.getCustomerId())
                {
                    for(ShippingDetails ship: ShippingDetailsData)
                    {
                        if(ship.getCustomerId()==puo.getCustomerId() && ship.getPurchaseId()==puo.getOrderId())
                        {
                            LocalData.put(puo, ship);
                        }
                    }
                }
            }
            AllData.put(cus, LocalData);
        }
        return AllData;
    }
    
}