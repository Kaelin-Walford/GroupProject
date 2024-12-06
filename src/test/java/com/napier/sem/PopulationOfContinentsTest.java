package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PopulationOfContinentsTest {

    static PopulationOfContinents app;

    @BeforeAll
    static void init()
    {
        app = new PopulationOfContinents();
    }

    //DisplayCountries Tests

    //Test with null value
    @Test
    void displayContinentsTestNull ()
    {
        app.displayContinent(null);
    }

    //Test with empty arraylist
    @Test
    void displayContinentsTestEmpty ()
    {
        ArrayList<ContinentReport> continentReports = new ArrayList<ContinentReport>();
        app.displayContinent(continentReports);
    }

    //Test contains null
    @Test
    void displayCountriesTestContainsNull ()
    {
        ArrayList<ContinentReport> continentReports = new ArrayList<ContinentReport>();
        continentReports.add(null);
        app.displayContinent(continentReports);
    }

    //Test normal
    @Test
    void displayCountries ()
    {
        ArrayList<ContinentReport> continentReports = new ArrayList<ContinentReport>();
        ContinentReport continentReport = new ContinentReport();
        continentReport.Continent = "Antarctica";
        continentReport.PopulationInMillions = 0;
        continentReport.PercentageInCities = 0;
        continentReport.PercentageOutwithCities = 0;

        continentReports.add(continentReport);
        app.displayContinent(continentReports);
    }
}
