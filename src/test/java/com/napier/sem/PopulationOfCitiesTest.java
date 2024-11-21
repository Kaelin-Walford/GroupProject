package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PopulationOfCitiesTest
{
    static PopulationOfCities populationOfCities;

    @BeforeAll
    static void init()
    {
        populationOfCities = new PopulationOfCities();
    }

    //Display  cities tests
    @Test
    void displayCitiesTestNull()
    {
        populationOfCities.displayCities(null);
    }
    @Test
    void displayCitiesTestEmpty()
    {
        ArrayList<CityReport> CityReports = new ArrayList<CityReport>();
        populationOfCities.displayCities(CityReports);
    }

    @Test
    void displayCitiesTestContainsNull()
    {
        ArrayList<CityReport> CityReports = new ArrayList<CityReport>();
        CityReports.add(null);
        populationOfCities.displayCities(CityReports);
    }

    @Test
    void  displayCities()
    {
        ArrayList<CityReport> CityReports = new ArrayList<CityReport>();
        CityReport City = new CityReport();
        City.name = "Edinburgh";
        City.Country = "Scotland";
        City.population = 2000;
        CityReports.add(City);
        populationOfCities.displayCities(CityReports);
    }
}
