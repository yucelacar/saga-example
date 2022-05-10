package com.intecon.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.intecon.commands.CreateInvoiceCommand;
import com.intecon.commands.UpdateInvoiceStatusCommand;
import com.intecon.events.InvoiceCreatedEvent;
import com.intecon.events.InvoiceUpdatedEvent;

@Aggregate
public class InvoiceAggregate {

    @AggregateIdentifier
    private String invoiceId;

    private String invoicePath;

    private String customerName;

    private String invoiceStatus;

    public InvoiceAggregate() {
    }

    @CommandHandler
    public InvoiceAggregate(CreateInvoiceCommand createInvoiceCommand){
       
    	AggregateLifecycle.apply(new InvoiceCreatedEvent(createInvoiceCommand.invoiceId, createInvoiceCommand.invoicePath,
                createInvoiceCommand.customerName, createInvoiceCommand.invoiceStatus));
    }
    
    @EventSourcingHandler
    protected void on(InvoiceCreatedEvent invoiceCreatedEvent){
        this.invoiceId = invoiceCreatedEvent.invoiceId;
        this.invoicePath = invoiceCreatedEvent.invoicePath;
        this.customerName = invoiceCreatedEvent.customerName;
        this.invoiceStatus = invoiceCreatedEvent.invoiceStatus;
    }

    @CommandHandler
    protected void on(UpdateInvoiceStatusCommand updateInvoiceStatusCommand){
    	System.out.println("INVOICE STATUS:"+updateInvoiceStatusCommand.invStat);
        AggregateLifecycle.apply(new InvoiceUpdatedEvent(updateInvoiceStatusCommand.invoiceId, updateInvoiceStatusCommand.invStat));
    }

    @EventSourcingHandler
    protected void on(InvoiceUpdatedEvent invoiceUpdatedEvent){
        this.invoiceId = invoiceUpdatedEvent.invoiceId;
        this.invoiceStatus = String.valueOf(invoiceUpdatedEvent.invStat);
    }
}
