package com.intecon.events;

import com.intecon.enums.InvoiceStatus;

public class InvoiceUpdatedEvent {

	
	public String invoiceId;
	public InvoiceStatus invStat;
	
	public InvoiceUpdatedEvent(String invoiceId, InvoiceStatus invStat) {
		this.invoiceId = invoiceId;
		this.invStat = invStat;
	}
}
