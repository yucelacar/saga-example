package com.intecon.events;

public class InvoiceCreatedEvent {
	
	public final String invoiceId;

    public final String invoicePath;
    
    public final String customerName;
    
    public final String invoiceStatus;

    public InvoiceCreatedEvent(String invoiceId, String invoicePath,String customerName,String invoceStatus) {
        this.invoiceId = invoiceId;
        this.invoicePath = invoicePath;
        this.customerName = customerName;
        this.invoiceStatus = invoceStatus;
    }
}
