package com.intecon.events;

public class SignFailedInvoiceEvent {
	public String signId;	
    public String invoiceId;
    
    public SignFailedInvoiceEvent(String signId, String invoiceId) {
        this.signId = signId;
        this.invoiceId = invoiceId;
    }
}
