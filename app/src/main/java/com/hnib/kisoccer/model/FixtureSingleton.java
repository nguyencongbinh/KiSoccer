package com.hnib.kisoccer.model;

import com.hnib.kisoccer.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caucukien on 28/11/2015.
 */
public class FixtureSingleton {

    private static FixtureSingleton instance;
    List<Fixture> fixtures ;
    private FixtureSingleton(){
        fixtures = new ArrayList<Fixture>();
    }
    public static FixtureSingleton getInstance(){
        if(instance==null){
            instance = new FixtureSingleton();
        }
        return instance;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }


    public List<Fixture> getResultFixtures(){
        List<Fixture> resultFixtures = new ArrayList<>();
        for(Fixture fixture : fixtures){
            if(fixture.getStatus().equals(Constants.Key.KEY_FINISHED)){
                resultFixtures.add(fixture );
            }
        }
        return resultFixtures;
    }

    public List<Fixture> getTodaytFixtures(){
        List<Fixture> todayFixtures = new ArrayList<>();
        for(Fixture fixture : fixtures){
            if(fixture.getStatus().equals(Constants.Key.KEY_FINISHED)==false){
                todayFixtures.add(fixture );
            }
        }
        return todayFixtures;
    }
}
