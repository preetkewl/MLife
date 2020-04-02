package com.mlife.adapter;

/**
 * Created by milagro on 9/11/2017.
 */

public class Payment_GetterSetter {

    int id;
    String InvoiceNumber, Month, Amount, DueDate, Description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Payment_GetterSetter(int id, String invoiceNumber, String amount, String dueDate, String description) {
        this.id = id;
        InvoiceNumber = invoiceNumber;
        Amount = amount;
        DueDate = dueDate;
        Description = description;
    }

    public Payment_GetterSetter(int id, String invoiceNumber, String month, String amount, String dueDate, String description) {
        this.id = id;
        InvoiceNumber = invoiceNumber;
        Month = month;
        Amount = amount;
        DueDate = dueDate;
        Description = description;
    }
}
