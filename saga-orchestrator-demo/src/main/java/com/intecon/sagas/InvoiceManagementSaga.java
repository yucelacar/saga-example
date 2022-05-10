package com.intecon.sagas;

import java.util.UUID;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

import com.intecon.commands.SendInvoiceMailCommand;
import com.intecon.commands.SignInvoiceCommand;
import com.intecon.commands.UpdateInvoiceStatusCommand;
import com.intecon.enums.InvoiceStatus;
import com.intecon.events.InvoiceCreatedEvent;
import com.intecon.events.InvoiceUpdatedEvent;
import com.intecon.events.SentInvoiceMailEvent;
import com.intecon.events.SignFailedInvoiceEvent;
import com.intecon.events.SignedInvoiceEvent;


@Saga
public class InvoiceManagementSaga {
	@Inject
    private transient CommandGateway commandGateway;

    @StartSaga
    @SagaEventHandler(associationProperty = "invoiceId")
    public void handle(InvoiceCreatedEvent invoiceCreatedEvent){
        String signId = UUID.randomUUID().toString();
        System.out.println("Saga invoked InvoiceCreatedEvent");
        //associate Saga
        SagaLifecycle.associateWith("signId", signId);
        System.out.println("invoiceId id" + invoiceCreatedEvent.invoiceId);
        //send the commands
        commandGateway.send(new SignInvoiceCommand(signId, invoiceCreatedEvent.invoiceId,invoiceCreatedEvent.invoicePath,invoiceCreatedEvent.customerName));
    }
    
    
    @SagaEventHandler(associationProperty = "signId")
    public void handle(SignedInvoiceEvent signedInvoiceEvent){
        String mailId = UUID.randomUUID().toString();

        System.out.println("Saga continued wtih SignedInvoiceEvent");

        //associate Saga with shipping
        SagaLifecycle.associateWith("mailId", mailId);

        //send the create shipping command
        commandGateway.send(new SendInvoiceMailCommand(mailId, signedInvoiceEvent.signId, signedInvoiceEvent.invoiceId,signedInvoiceEvent.customerName));
    }
    
    @SagaEventHandler(associationProperty = "signId")
    public void handle(SignFailedInvoiceEvent signFailedInvoiceEvent){
        System.out.println("Saga continued wtih signFailedInvoiceEvent");
        //send the create shipping command
        commandGateway.send(new UpdateInvoiceStatusCommand(signFailedInvoiceEvent.invoiceId, InvoiceStatus.SIGNFAILED));
    }
    
    @SagaEventHandler(associationProperty = "mailId")
    public void handle(SentInvoiceMailEvent sentInvoiceMailEvent){
    	System.out.println("Saga continued wtih SentInvoiceMailEvent");
        commandGateway.send(new UpdateInvoiceStatusCommand(sentInvoiceMailEvent.invoiceId, InvoiceStatus.SENT));
    }

    @SagaEventHandler(associationProperty = "invoiceId")
    public void handle(InvoiceUpdatedEvent invoiceUpdatedEvent){
    	System.out.println("Saga ended wtih InvoiceUpdatedEvent");
        SagaLifecycle.end();
    }

}
