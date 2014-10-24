package org.wickedsource.budgeteer.service.budget;

import org.joda.money.Money;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditBudgetData implements Serializable{

    private long id;

    private String title;

    private Money total;

    private String importKey;

    private List<String> tags;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Money getTotal() {
        return total;
    }

    public void setTotal(Money total) {
        this.total = total;
    }

    public String getImportKey() {
        return importKey;
    }

    public void setImportKey(String importKey) {
        this.importKey = importKey;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = new ArrayList<String>(tags);
    }
}