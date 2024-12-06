package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PopulationOfRegionsTest {

    static PopulationOfRegions app;

    @BeforeAll
    static void init()
    {
        app = new PopulationOfRegions();
    }

    //DisplayCountries Tests

    //Test with null value
    @Test
    void displayRegionsTestNull ()
    {
        app.displayRegion(null);
    }

    //Test with empty arraylist
    @Test
    void displayRegionsTestEmpty ()
    {
        ArrayList<RegionReport> regionsReports = new ArrayList<RegionReport>();
        app.displayRegion(regionsReports);
    }

    //Test contains null
    @Test
    void displayRegionsTestContainsNull ()
    {
        ArrayList<RegionReport> regionReports = new ArrayList<RegionReport>();
        regionReports.add(null);
        app.displayRegion(regionReports);
    }

    //Test normal
    @Test
    void displayRegions ()
    {
        ArrayList<RegionReport> regionReports = new ArrayList<RegionReport>();
        RegionReport regionReport = new RegionReport();
        regionReport.Region = "Caribbean";
        regionReport.Continent = "North America";
        regionReport.TotalPopulationMillions = 228;
        regionReport.PopulationInCity = 4;
        regionReport.PopulationNotInCity = 96;

        regionReports.add(regionReport);
        app.displayRegion(regionReports);
    }
}
