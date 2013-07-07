package com.coral.foundation.ebay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.ApiException;
import com.ebay.sdk.SdkException;
import com.ebay.sdk.TimeFilter;
import com.ebay.sdk.eBayAccount;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GetSellerTransactionsCall;
import com.ebay.sdk.call.GetSessionIDCall;
import com.ebay.soap.eBLBaseComponents.AddressType;
import com.ebay.soap.eBLBaseComponents.AmountType;
import com.ebay.soap.eBLBaseComponents.CheckoutStatusCodeType;
import com.ebay.soap.eBLBaseComponents.CompleteStatusCodeType;
import com.ebay.soap.eBLBaseComponents.DetailLevelCodeType;
import com.ebay.soap.eBLBaseComponents.OrderIDArrayType;
import com.ebay.soap.eBLBaseComponents.OrderType;
import com.ebay.soap.eBLBaseComponents.PaginationType;
import com.ebay.soap.eBLBaseComponents.ShippingServiceOptionsType;
import com.ebay.soap.eBLBaseComponents.SiteCodeType;
import com.ebay.soap.eBLBaseComponents.TransactionType;

/**
* Sample code to relist an item (with minor modifications) using the RelistFixedPriceItem call
*
* The following steps outline what needs to be done for a successfull GetTransactions and GetOrders call
*
* 1. Create an ApiContext Object
* 2. Set the auth token and target api url (Webservice endpoint)
* 3. Create a GetTransactionsCall object.
* 4. Set the ModifiedFrom and ModifiedTo filter (just an example)
* 5. Set Pagination Parameters
* 6. Do an iteration till all transactions for the specified filter is retrieved (in this example we'll be using do..while )
* 7. Check if the transaction is part of an order.
* 8. If yes, add it to a list.
* 9. Once all transactions are retrieved, check if the transaction list contains any entry
*10. If Yes, execute the GetOrders call with all the orders.
*11. Replace the transaction level information with the information from the orders.
*/

public class ManageTransactions {
	
	private String customToken;
	private ApiContext apiContext=AppContextFactory.getInstance();
	
	
	public ManageTransactions(String customToken){
		this.setCustomToken(customToken);
	}
	
	public ManageTransactions(){
		
	}
	
	public static void main(String args[]){
		ManageTransactions m=new ManageTransactions();
		String ruName="mocha-platform-mocha-pl-0bf9-4-bhioe";
//		m.createRedirectURLByRuName(ruName);
		try {
			m.getSalesTransactionsByEbayToken(m.getGetFetchTokenBySessionID("VkQCAA**b896c1b713f0a471da206d60fffff9f3"));
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String createRedirectURLByRuName(String ruName){
		String sid =UUID.randomUUID().toString();
		String signInBaseURL = "https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn";
		try {
			String signInURL = signInBaseURL + "&runame=" + URLEncoder.encode(ruName, "ISO-8859-1") + "&sid=" +sid
					+"&SessID="+getSessionIDByRuName("mocha-platform-mocha-pl-0bf9-4-bhioe");
			System.out.println(signInURL);
			return signInURL;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getGetFetchTokenBySessionID(String sessionID){
		ApiCredential apiCredential=apiContext.getApiCredential();		
//		eBayAccount ebayAccount=new eBayAccount();
//		ebayAccount.setUsername(userName);
//		apiCredential.seteBayAccount(ebayAccount);
		FetchTokenCall userTokenCall=new FetchTokenCall(apiContext);
		userTokenCall.setSessionID(sessionID);
		try {
			userTokenCall.fetchToken();
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(""+userTokenCall.getReturnedToken());
		
		return userTokenCall.getReturnedToken();
	}
	
	private String getSessionIDByRuName(String ruName){
        GetSessionIDCall call = new GetSessionIDCall();  
        call.setApiContext(apiContext);  
		call.setRuName(ruName);  
        try {
			String sessionID = call.getSessionID();
			System.out.println("session id is:"+sessionID);
			return sessionID;
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SdkException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        return null;
	}
	
     public List<OrderType> getSalesTransactionsByEbayToken(String ebayToken) throws ApiException, SdkException, Exception{
    	 
    	 OrderType[] orders=null;
    	 
//          ApiContext apiContext = new ApiContext();
          // set API Token to access eBay API Server
          ApiCredential cred = apiContext.getApiCredential();
          //Set Auth Token
          cred.seteBayToken(ebayToken);

          apiContext.setApiCredential(cred);
          apiContext.getApiLogging().setLogSOAPMessages(true);// This will log SOAP requests and responses

          apiContext.setSite(SiteCodeType.UK); // Set site to UK

          GetSellerTransactionsCall getTrans = new GetSellerTransactionsCall(
                    apiContext);

          getTrans.addDetailLevel(DetailLevelCodeType.RETURN_ALL);
          getTrans.setIncludeFinalValueFee(true); 
           
          // Create a filter, in this example we are using from and to filter, to get all transactions in the last 15 minutes.
          Calendar to = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
          Calendar from = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
          //from.roll(Calendar.MINUTE,-15);
          from.roll(Calendar.DAY_OF_MONTH, 29);

          TimeFilter filter = new TimeFilter(from, to);
          getTrans.setModifiedTimeFilter(filter);
          getTrans.setIncludeContainingOrder(true);// We need to get the containing order.
          getTrans.setIncludeFinalValueFee(true);
          
          PaginationType pagination = new PaginationType();
          pagination.setEntriesPerPage(100);

          getTrans.setPagination(pagination);

          int pageNumber = 0;
          int tranCounter = 0;
          
          SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss.SSS");
          
          List <String> orderIdList = new ArrayList<String>(); 

          do {
               System.out
                         .println("-------------------------------------------------");
               System.out.println("Calling getseller transactions");
               System.out.println("Requesting Page : " + ++pageNumber + " .....");
               getTrans.getPagination().setPageNumber(pageNumber);
               TransactionType[] transactions = getTrans.getSellerTransactions();

               System.out.print(" *********************************");
               System.out.print("Page " + pageNumber + " of "
                         + getTrans.getPaginationResult().getTotalNumberOfPages());
               System.out.println("*********************************");
               if (getTrans.getReturnedTransactionCountActual() > 0) {
                    for (TransactionType transaction : transactions) {

                         System.out.println(" Transaction "
                                   + ++tranCounter
                                   + " of "
                                   + getTrans.getPaginationResult()
                                             .getTotalNumberOfEntries());

                         System.out.println(" BuyerId: "
                                   + transaction.getBuyer().getUserID());

                         System.out.println("Transaction ID "
                                   + transaction.getTransactionID() + " ItemID: "
                                   + transaction.getItem().getItemID()
                                   + " Item Title : "
                                   + transaction.getItem().getTitle());

                         System.out.println("Quantity: "
                                   + transaction.getQuantityPurchased());

                         System.out.println("Transaction Price : "
                                   + transaction.getTransactionPrice().getValue()
                                   + " CheckOut Status : "
                                   + transaction.getStatus().getCheckoutStatus()
                                             .value()
                                   + " PaymentStatus : "
                                   + transaction.getStatus().getEBayPaymentStatus()
                                             .value());

                         System.out.println("CreatedDate : "
                                   + dateFormatter.format(transaction.getCreatedDate()
                                             .getTime())
                                   + " Last Time Modified : "
                                   + dateFormatter.format(transaction.getStatus()
                                             .getLastTimeModified().getTime()));
                         if (transaction.getShippingDetails().getSalesTax()
                                   .getSalesTaxAmount() != null) {
                              AmountType tax = transaction.getShippingDetails()
                                        .getSalesTax().getSalesTaxAmount();
                              System.out.println("Sales Tax : " + tax.getValue());
                         } else {
                              System.out.println("Sales Tax : 0.0");
                         }

                         if (transaction.getFinalValueFee() != null) {
                              AmountType finalValueFee = transaction
                                        .getFinalValueFee();
                              System.out.println("Final Value Fee : "
                                        + finalValueFee.getValue());
                         }

                         if (transaction.getStatus().getCheckoutStatus()
                                   .equals(CheckoutStatusCodeType.CHECKOUT_COMPLETE)) {// To check if the item is ready for shipping
                              System.out
                                        .format("Payment Method: %s Total: %s Adjustment Amount: %s ",
                                                  transaction.getStatus()
                                                            .getPaymentMethodUsed().value(),
                                                  transaction.getAmountPaid().getValue(),
                                                  transaction.getAdjustmentAmount()
                                                            .getValue());
                              if (transaction.getShippingServiceSelected() != null) {
                                   System.out.println("Shipping Service: "
                                             + transaction.getShippingServiceSelected()
                                                       .getShippingService());
                                   if (transaction.getShippingServiceSelected()
                                             .getShippingServiceCost() != null) {
                                        System.out.println("Shipping Cost: "
                                                  + transaction
                                                            .getShippingServiceSelected()
                                                            .getShippingServiceCost()
                                                            .getValue());
                                   }
                              }

                              AddressType shippingAddress = transaction.getBuyer()
                                        .getBuyerInfo().getShippingAddress();

                              StringBuffer address = new StringBuffer();
                              address.append(shippingAddress.getName());
                              if (shippingAddress.getStreet() != null) {
                                   address.append(",").append(
                                             shippingAddress.getStreet());
                              }
                              if (shippingAddress.getStreet1() != null) {
                                   address.append(",").append(
                                             shippingAddress.getStreet1());
                              }
                              if (shippingAddress.getStreet2() != null) {
                                   address.append(",").append(
                                             shippingAddress.getStreet2());
                              }
                              if (shippingAddress.getCityName() != null) {
                                   address.append(",").append(
                                             shippingAddress.getCityName());
                              }
                              if (shippingAddress.getStateOrProvince() != null) {
                                   address.append(",").append(
                                             shippingAddress.getStateOrProvince());
                              }
                              if (shippingAddress.getPostalCode() != null) {
                                   address.append(",").append(
                                             shippingAddress.getPostalCode());
                              }
                              if (shippingAddress.getCountryName() != null) {
                                   address.append(",").append(
                                             shippingAddress.getCountryName());
                              }
                              if (shippingAddress.getPhone() != null) {
                                   address.append(",").append(
                                             shippingAddress.getPhone());
                              }

                              System.out.println("Shipping Address: " + address);

                         }

                         if (transaction.getPaidTime() != null) {
                              System.out.println("Paid Time:"
                                        + dateFormatter.format(transaction
                                                  .getPaidTime().getTime()));
                         }

                         if (transaction.getShippedTime() != null) {
                              System.out.println("Shipped Time: "
                                        + dateFormatter.format(transaction
                                                  .getShippedTime().getTime()));
                         }

                         // Check if the transaction is part of an order. If it is we
                         // need to get the latest information from the order.
                         if (transaction.getContainingOrder() != null) {
                              if (!orderIdList.contains(transaction
                                        .getContainingOrder().getOrderID())) {
                                   String orderID = transaction.getContainingOrder()
                                             .getOrderID();
                                   orderIdList.add(orderID); // Collect all the orders
                                                                      // so that we need only
                                                                      // one call with
                                                                      // GetOrders.
                                   System.out
                                             .println("This transaction is part of the order : "
                                                       + orderID);
                              }
                         }
                         System.out.println();
                    }
               }
          } while (getTrans.getHasMoreTransactions());
           
          // Get all order information for related orders
          if (!orderIdList.isEmpty()) {
               System.out
                         .println("*********************************************************************");
               
               System.out.println("Calling getOrders");
               System.out.println("Order List : " + orderIdList);
               
               System.out.println("*********************************************************************");

               GetOrdersCall getOrders = new GetOrdersCall(apiContext);
               

               OrderIDArrayType orderIdArray = new OrderIDArrayType();
               // Convert ArrayList to String Array
               String[] orderId = orderIdList.toArray(new String[orderIdList
                         .size()]);
               // Set it to the orderIdArrayType
               orderIdArray.setOrderID(orderId);

               getOrders.setOrderIDArray(orderIdArray);

               orders = getOrders.getOrders();
               
               for (OrderType order : orders) {
                    ShippingServiceOptionsType shipping = order
                              .getShippingServiceSelected();
                    if (shipping == null) {
                         if (order.getCheckoutStatus().getStatus() == CompleteStatusCodeType.COMPLETE) {
                              shipping = order.getShippingDetails()
                                        .getShippingServiceOptions(0);
                         } else {
                              shipping = new ShippingServiceOptionsType();
                         }
                    }
                    
                    double salesTax = order.getShippingDetails().getSalesTax()
                              .getSalesTaxAmount() == null ? 0.0 : order
                              .getShippingDetails().getSalesTax()
                              .getSalesTaxAmount().getValue();
                    
                    System.out.println("Information for order : " + order.getOrderID());
                    
                    System.out.format("Sales Tax %f ", salesTax);
                    
                    AddressType shippingAddress = order.getShippingAddress();

                    StringBuffer address = new StringBuffer();
                    address.append(shippingAddress.getName());
                    if (shippingAddress.getStreet() != null) {
                         address.append(",").append(shippingAddress.getStreet());
                    }
                    if (shippingAddress.getStreet1() != null) {
                         address.append(",")
                                   .append(shippingAddress.getStreet1());
                    }
                    if (shippingAddress.getStreet2() != null) {
                         address.append(",")
                                   .append(shippingAddress.getStreet2());
                    }
                    if (shippingAddress.getCityName() != null) {
                         address.append(",").append(
                                   shippingAddress.getCityName());
                    }
                    if (shippingAddress.getStateOrProvince() != null) {
                         address.append(",").append(
                                   shippingAddress.getStateOrProvince());
                    }
                    if (shippingAddress.getPostalCode() != null) {
                         address.append(",").append(
                                   shippingAddress.getPostalCode());
                    }
                    if (shippingAddress.getCountryName() != null) {
                         address.append(",").append(
                                   shippingAddress.getCountryName());
                    }
                    if (shippingAddress.getPhone() != null) {
                         address.append(",").append(shippingAddress.getPhone());
                    }
                    
                    System.out.println("Shipping Address: " + address);
                    
                //Update your system with the order information. For all transactions that match this OrderID -
                //Replace the following Transaction information with information from the Order
                    System.out
                              .println("Update Shipping and Payment information associated with transactions that belong to OrderID: "
                                        + order.getOrderID());

                //1. shipping.ShippingService;
                //2. shipping.ShippingServiceCost.Value;
                //3. salesTax;
                //4. order.AdjustmentAmount.Value;
                //5. order.Total.Value;
                //6. order.CheckoutStatus.Status;
                //7. order.CheckoutStatus.eBayPaymentStatus;
                //8. order.CheckoutStatus.PaymentMethod;
                //9. shipAddress;
               }
          }
          return Arrays.asList(orders);
     }

	public String getCustomToken() {
		return customToken;
	}

	public void setCustomToken(String customToken) {
		this.customToken = customToken;
	}
}