package org.acme.service;

import java.util.HashMap;
import java.util.Map;

import org.acme.model.Entry;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

public class AbstractService {

    public String expenseID;
    public String description;
    public String amount;
    public String balance;
    public String date;
    
    public static final String ENTRY_EXPENSEID_COL = "expenseID";
    public static final String ENTRY_DESCRIPTION_COL = "description";
    public static final String ENTRY_AMOUNT_COL = "amount";
    public static final String ENTRY_BALANCE_COL = "balance";
    public static final String ENTRY_DATE_COL = "date";

    public String getTableName() {
        return "entry";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(ENTRY_EXPENSEID_COL, ENTRY_DESCRIPTION_COL, ENTRY_AMOUNT_COL, ENTRY_BALANCE_COL, ENTRY_DATE_COL).build();
    }

    protected PutItemRequest putRequest(Entry entry) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(ENTRY_EXPENSEID_COL, AttributeValue.builder().s(entry.getExpenseID()).build());
        item.put(ENTRY_DESCRIPTION_COL, AttributeValue.builder().s(entry.getDescription()).build());
        item.put(ENTRY_AMOUNT_COL, AttributeValue.builder().s(entry.getAmount()).build());
        item.put(ENTRY_BALANCE_COL, AttributeValue.builder().s(entry.getBalance()).build());
        item.put(ENTRY_DATE_COL, AttributeValue.builder().s(entry.getDate()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(ENTRY_EXPENSEID_COL, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(ENTRY_EXPENSEID_COL, ENTRY_DESCRIPTION_COL, ENTRY_AMOUNT_COL, ENTRY_BALANCE_COL, ENTRY_DATE_COL)
                .build();
    }
    
}