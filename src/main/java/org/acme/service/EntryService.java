package org.acme.service;

import org.acme.model.Entry;

import java.util.List;
import java.util.stream.Collectors;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EntryService extends AbstractService {

    @Inject
    DynamoDbClient dynamoDB;

    public List<Entry> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Entry::from)
                .collect(Collectors.toList());
    }

    public Entry findByExpenseID(String expenseID) {
        return  Entry.from(dynamoDB.getItem(getRequest(expenseID)).item());

    }

    public void addEntry(Entry entry) {
        dynamoDB.putItem(putRequest(entry));
    }
    
}