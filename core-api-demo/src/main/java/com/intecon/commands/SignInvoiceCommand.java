package com.intecon.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class SignInvoiceCommand {

	
	@TargetAggregateIdentifier
	public String signId;	
    public String invoiceId;
    public String invoicePath;
    public String customerName;
    
    public SignInvoiceCommand(String signId, String invoiceId,String invoicePath,String customerName) {
        this.signId = signId;
        this.invoiceId = invoiceId;
        this.invoicePath = invoicePath;
        this.customerName = customerName;
    }
}
