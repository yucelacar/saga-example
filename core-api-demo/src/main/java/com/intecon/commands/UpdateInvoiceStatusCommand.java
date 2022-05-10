package com.intecon.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.intecon.enums.InvoiceStatus;

public class UpdateInvoiceStatusCommand {


	@TargetAggregateIdentifier
	public String invoiceId;
	public InvoiceStatus invStat;
	
	public UpdateInvoiceStatusCommand(String invoiceId, InvoiceStatus invStat) {
		this.invoiceId = invoiceId;
		this.invStat = invStat;
	}
	
}
