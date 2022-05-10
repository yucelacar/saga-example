package com.intecon.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateInvoiceCommand {
	
	@TargetAggregateIdentifier
    public final String invoiceId;

    public final String invoicePath;
    
    public final String customerName;
    
    public final String invoiceStatus;

    public CreateInvoiceCommand(String invoiceId, String invoicePath,String customerName,String invoceStatus) {
        this.invoiceId = invoiceId;
        this.invoicePath = invoicePath;
        this.customerName = customerName;
        this.invoiceStatus = invoceStatus;
    }
}
