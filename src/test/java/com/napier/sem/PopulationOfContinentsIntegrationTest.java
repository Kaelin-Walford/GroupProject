package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationOfContinentsIntegrationTest
{
    static PopulationOfContinents continents;
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        continents = new PopulationOfContinents();
    }

    /**
     * Tests for getCountry
     */
    //Test normal
    @Test
    void testGetContinents()
    {
        boolean found = false;
        ArrayList<ContinentReport> rep = continents.getContinent(app.con);
        for(ContinentReport cr : rep)

        assertNotNull(rep);
        continents.displayContinent(rep);
    }

    //Test Null
    @Test
    void testGetContinentNull()
    {
        Connection cons = null;
        ArrayList<ContinentReport> rep = continents.getContinent(cons);
        continents.displayContinent(rep);
    }
}
