package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

public class PopulationOfCapitalCitiesTest
{
    static PopulationOfCapitalCities populationOfCapitalCities;

    @BeforeAll
    static void init()
    {
        populationOfCapitalCities = new PopulationOfCapitalCities();
    }

    //Display capital cities tests
    @Test
    void displayCapitalCitiesTestNull()
    {
        populationOfCapitalCities.displayCapitalCities(null);
    }
    @Test
    void displayCapitalCitiesTestEmpty()
    {
        ArrayList<CapitalCityReport> capitalCityReports = new ArrayList<CapitalCityReport>();
        populationOfCapitalCities.displayCapitalCities(capitalCityReports);
    }

    @Test
    void displayCapitalCitiesTestContainsNull()
    {
        ArrayList<CapitalCityReport> capitalCityReports = new ArrayList<CapitalCityReport>();
        capitalCityReports.add(null);
        populationOfCapitalCities.displayCapitalCities(capitalCityReports);
    }

    @Test
    void  displayCapitalCities()
    {
        ArrayList<CapitalCityReport> capitalCityReports = new ArrayList<CapitalCityReport>();
        CapitalCityReport capitalCity = new CapitalCityReport();
        capitalCity.Name = "Edinburgh";
        capitalCity.Country = "Scotland";
        capitalCity.Population = 2000;
        capitalCityReports.add(capitalCity);
        populationOfCapitalCities.displayCapitalCities(capitalCityReports);
    }
}
