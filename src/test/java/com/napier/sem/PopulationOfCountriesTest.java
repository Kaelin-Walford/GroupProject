package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class PopulationOfCountriesTest
{
    static PopulationOfCountries app;

    @BeforeAll
    static void init()
    {
        app = new PopulationOfCountries();
    }

    //DisplayCountries Tests

    //Test with null value
    @Test
    void displayCountriesTestNull ()
    {
        app.displayCountry(null);
    }

    //Test with empty arraylist
    @Test
    void displayCountriesTestEmpty ()
    {
        ArrayList<CountryReport> countryReports = new ArrayList<CountryReport>();
        app.displayCountry(countryReports);
    }

    //Test contains null
    @Test
    void displayCountriesTestContainsNull ()
    {
        ArrayList<CountryReport> countryReports = new ArrayList<CountryReport>();
        countryReports.add(null);
        app.displayCountry(countryReports);
    }

    //Test normal
    @Test
    void displayCountries ()
    {
        ArrayList<CountryReport> countryReports = new ArrayList<CountryReport>();
        CountryReport countryReport = new CountryReport();
        countryReport.Name = "Scotland";
        countryReport.Continent = "Europe";
        countryReport.Region = "UK";
        countryReport.Population = 1000000;
        countryReport.Capital = "Edinburgh";
        countryReports.add(countryReport);
        app.displayCountry(countryReports);
    }
}
