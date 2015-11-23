package com.hnib.kisoccer.model;

import java.util.List;

/**
 * Created by caucukien on 23/11/2015.
 */

public class JsonFixturesResponse {
    private List<Fixture> fixtures;
    private int count;

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


