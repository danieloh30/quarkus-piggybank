package org.acme;

import  org.acme.model.Entry;
import  org.acme.service.EntryService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import java.math.BigDecimal;
import java.util.List;

@Path("/entryResource")
public class EntryResource {

    @Inject
    EntryService eService;

    @GET
    @Path("/findAll")
    public List<Entry> findAll() {
        return eService.findAll();
    }

    @GET
    @Path("/find/{categoryID}")
    public Entry findByCategoryID(String categoryID) {
        return eService.findByCategoryID(categoryID);
    }

    @POST
    public void addEntry(Entry entry) throws Exception {
        // TODO: Add a method to calculate the Balance
        entry.setBalance(calculateBalance(entry.getAmount()));
        eService.addEntry(entry);
    }

    public String calculateBalance(String amount) {
        final BigDecimal[] balance = {new BigDecimal(amount)};
        eService.findAll().forEach(entry -> {
            balance[0] = balance[0].add(BigDecimal.valueOf(Double.valueOf(entry.getAmount())).setScale(0));
        });
        return balance[0].toString();
    }
    
}
