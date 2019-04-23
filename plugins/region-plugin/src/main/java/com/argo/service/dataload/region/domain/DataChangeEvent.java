package com.argo.service.dataload.region.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 2019/4/22
 * Time: 17:12
 *
 * @author hugh
 */
public class DataChangeEvent {
    /**
     * Category of changed data. Could be :
     * master : Master data
     * trans : Transactional data
     * log : Log data
     */
    private String category;
    private String key;
    /**
     * Domain of data. Could be: Party, Product, Logistics, etc
     */
    private String domain;
    /**
     * Name of entity.
     */
    private String entity;
    /**
     * List of data been changed. Each of the item is Json representation of the identities of changed data
     */
    private List<String> items;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
