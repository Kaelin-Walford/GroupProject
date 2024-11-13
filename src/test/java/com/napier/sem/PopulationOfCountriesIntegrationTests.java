package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationOfCountriesIntegrationTests
{
    static PopulationOfCountries countries;
    static App app;

    @BeforeAll
    static void init()
    {
        countries = new PopulationOfCountries();
        app.connect("localhost:33060", 30000);

    }

    @Test
    void testGetEmployee()
    {
        ArrayList<CountryReport> rep = countries.getCountry(app.con);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Code, "JPN");
            assertEquals(cr.Name, "Japan");
            assertEquals(cr.Continent, "Asia");
            assertEquals(cr.Region, "Eastern Asia");
            assertEquals(cr.Population, 126714000);
            assertEquals(cr.Capital, "Tokyo");
        }
    }
}
