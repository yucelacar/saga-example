package com.intecon.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intecon.dto.InvoiceCreateDTO;
import com.intecon.service.InvoiceService;


@RestController
@RequestMapping(value = "/api/invoice")
public class InvoiceController {

	@Autowired
	InvoiceService invService;
	
	@PostMapping
    public CompletableFuture<String> creteInvoice(@RequestBody InvoiceCreateDTO invoiceCreateDTO){
        return invService.createInvoice(invoiceCreateDTO);
    }
	
}
