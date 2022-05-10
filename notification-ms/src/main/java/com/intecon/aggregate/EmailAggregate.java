package com.intecon.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.intecon.commands.SendInvoiceMailCommand;
import com.intecon.events.SentInvoiceMailEvent;

@Aggregate
public class EmailAggregate {

	@AggregateIdentifier
	private String mailId;
    private String signId;
    private String invoiceId;    
    
    private String customerName;

    @CommandHandler
    public EmailAggregate(SendInvoiceMailCommand signInvoiceCommand){
        AggregateLifecycle.apply(new SentInvoiceMailEvent(signInvoiceCommand.mailId,signInvoiceCommand.signId, signInvoiceCommand.invoiceId,signInvoiceCommand.customerName));
    }

    @EventSourcingHandler
    protected void on(SentInvoiceMailEvent signedInvoiceEvent){
    	this.mailId = signedInvoiceEvent.mailId;
    	this.signId = signedInvoiceEvent.signId;
        this.invoiceId = signedInvoiceEvent.invoiceId;
        this.customerName = signedInvoiceEvent.customerName;
    }
}
