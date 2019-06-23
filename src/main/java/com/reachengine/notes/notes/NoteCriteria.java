package com.reachengine.notes.notes;

import com.reachengine.notes.common.Criteria;

public class NoteCriteria implements Criteria {

    String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
