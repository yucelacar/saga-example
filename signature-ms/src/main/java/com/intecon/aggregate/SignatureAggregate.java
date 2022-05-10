package com.intecon.aggregate;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.intecon.commands.SignInvoiceCommand;
import com.intecon.enums.SignStatus;
import com.intecon.events.SignFailedInvoiceEvent;
import com.intecon.events.SignedInvoiceEvent;

@Aggregate
public class SignatureAggregate {

	@AggregateIdentifier
    private String signId;

    private String invoiceId;

    private String customerName;

    @CommandHandler
    public SignatureAggregate(SignInvoiceCommand signInvoiceCommand){
    	System.out.println("Signature Aggregate COMMAND");
    	if(signInvoiceCommand.customerName == null || signInvoiceCommand.customerName.trim().length() == 0) {
    		AggregateLifecycle.apply(new SignFailedInvoiceEvent(signInvoiceCommand.signId, signInvoiceCommand.invoiceId));
    	}else {
    		AggregateLifecycle.apply(new SignedInvoiceEvent(signInvoiceCommand.signId, signInvoiceCommand.invoiceId,signInvoiceCommand.customerName));
    	}
        
    }

    @EventSourcingHandler
    protected void on(SignedInvoiceEvent signedInvoiceEvent){
        this.signId = signedInvoiceEvent.signId;
        this.invoiceId = signedInvoiceEvent.invoiceId;
        this.customerName = signedInvoiceEvent.customerName;
    }
    
    @EventSourcingHandler
    protected void on(SignFailedInvoiceEvent signFailedInvoiceEvent){
        this.signId = signFailedInvoiceEvent.signId;
        this.invoiceId = signFailedInvoiceEvent.invoiceId;
    }
}
