package com.intecon.events;

public class SentInvoiceMailEvent {

	public String mailId;
	public String signId;	
    public String invoiceId;
    public String customerName;
    
    public SentInvoiceMailEvent(String mailId, String signId,String invoiceId,String customerName) {
        this.mailId = mailId;
        this.signId = signId;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
    }
}
