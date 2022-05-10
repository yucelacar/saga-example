package com.intecon.service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.intecon.commands.CreateInvoiceCommand;
import com.intecon.dto.InvoiceCreateDTO;
import com.intecon.enums.InvoiceStatus;


@Service
public class InvoiceService {

	
	private final CommandGateway commandGateway;

    public InvoiceService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }
    
    public CompletableFuture<String> createInvoice(InvoiceCreateDTO invoiceCreateDTO) {
    	/*
    	 * dsajdksajdjskadsa
    	 * dsadhsakh
    	 */
        return commandGateway.send(new CreateInvoiceCommand(UUID.randomUUID().toString(),
                invoiceCreateDTO.getInvoicePath(), invoiceCreateDTO.getCustomerName(), String.valueOf(InvoiceStatus.CREATED)));
    }
}
