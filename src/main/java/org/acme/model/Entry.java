package org.acme.model;

import java.util.Map;
import java.util.Objects;

import org.acme.service.AbstractService;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection
public class Entry {

    public String categoryID;
    public String description;
    public String amount;
    public String balance;
    public String date;

    public Entry() {}

    public static Entry from(Map<String, AttributeValue> item) {
        Entry entry = new Entry();
        if (item != null && !item.isEmpty()) {
            entry.setCategoryID(item.get(AbstractService.ENTRY_CATEGORYID_COL).s());
            entry.setDescription(item.get(AbstractService.ENTRY_DESCRIPTION_COL).s());
            entry.setAmount(item.get(AbstractService.ENTRY_AMOUNT_COL).s());
            entry.setBalance(item.get(AbstractService.ENTRY_BALANCE_COL).s());  
            entry.setDate(item.get(AbstractService.ENTRY_DATE_COL).s());

        }
        return entry;
    }

    public String getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBalance() {
        return this.balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Entry)) {
            return false;
        }

        Entry other = (Entry) obj;

        return Objects.equals(other.categoryID, this.categoryID)
            && Objects.equals(other.description, this.description)
            && Objects.equals(other.amount, this.amount)
            && Objects.equals(other.balance, this.balance)
            && Objects.equals(other.date, this.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.categoryID);
    }

}