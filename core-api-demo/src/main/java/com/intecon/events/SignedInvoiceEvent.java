package com.intecon.events;

public class SignedInvoiceEvent {

	
	public String signId;	
    public String invoiceId;
    public String customerName;
    
    public SignedInvoiceEvent(String signId, String invoiceId,String customerName) {
        this.signId = signId;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
    }
}
