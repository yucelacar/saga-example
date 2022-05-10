package com.intecon.dto;

public class InvoiceCreateDTO {
	
	private String invoicePath;
	private String customerName;
	
	public String getInvoicePath() {
		return invoicePath;
	}
	public void setInvoicePath(String invoicePath) {
		this.invoicePath = invoicePath;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

}
