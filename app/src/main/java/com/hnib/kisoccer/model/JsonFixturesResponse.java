package com.hnib.kisoccer.model;

import java.util.List;

/**
 * Created by caucukien on 23/11/2015.
 */

public class JsonFixturesResponse {


    private List<RootLinks> _links;
    private List<Fixture> fixtures;
    private int count;


    public List<RootLinks> get_links() {
        return _links;
    }

    public void set_links(List<RootLinks> _links) {
        this._links = _links;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}


