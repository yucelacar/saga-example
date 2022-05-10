package com.intecon.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class SendInvoiceMailCommand {

	
	@TargetAggregateIdentifier
	public String mailId;
	public String signId;	
    public String invoiceId;
    public String customerName;
    
    public SendInvoiceMailCommand(String mailId, String signId,String invoiceId,String customerName) {
        this.mailId = mailId;
        this.signId = signId;
        this.invoiceId = invoiceId;
        this.customerName = customerName;
    }
}
