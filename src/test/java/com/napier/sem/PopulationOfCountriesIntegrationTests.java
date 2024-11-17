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
        app = new App();
        app.connect("localhost:33060", 30000);
        countries = new PopulationOfCountries();

    }

    @Test
    void testGetEmployee()
    {
        boolean found = false;
        ArrayList<CountryReport> rep = countries.getCountry(app.con);
        for(CountryReport cr : rep)
        {
            if("JPN".equals(cr.Code) && "Japan".equals(cr.Name) && "Asia".equals(cr.Continent) && "Eastern Asia".equals(cr.Region) && 126714000 == cr.Population && "Tokyo".equals(cr.Capital))
            {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
