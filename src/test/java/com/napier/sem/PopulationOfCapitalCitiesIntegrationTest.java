package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationOfCapitalCitiesIntegrationTest
{
    static PopulationOfCapitalCities cities;
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 30000);
        cities = new PopulationOfCapitalCities();
    }

    /**
     * Test for getCapitalCities
     */
    //Test normal
    @Test
    void testGetCapitalCities()
    {
        boolean found = false;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCities(app.con);
        for(CapitalCityReport report : rep)
        {
            if("Tokyo".equals(report.Name) && "Japan".equals(report.Country) && report.Population == 7980230)
            {
                found = true;
                break;
            }
        }
        assertTrue(found);
        cities.displayCapitalCities(rep);
    }

    //Test Null
    @Test
    void testGetCountryNull()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCities(cons);
        cities.displayCapitalCities(rep);
    }

    /**
     *test for getCapitalCitiesInContinent
     */

    //Test normal with continent Antarctica
    @Test
    void testGetCapitalCitiesInContinent()
    {
        boolean found = false;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInContinent(app.con, "Antarctica");
        for(CapitalCityReport report : rep)
        {
            if("South Georgia and the South Sandwich Islands".equals(report.Country) || "Heard Island and McDonald Islands".equals(report.Country) || "Bouvet Island".equals(report.Country) || "French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country))
            {
                found = true;
                assertTrue(found);
                found = false;
            }
            else
            {
                assertTrue(found);
            }
        }
        cities.displayCapitalCities(rep);
    }

    //Test with invalid continent
    @Test
    void testGetCapitalCitiesInContinentWithInvalidContinent()
    {
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInContinent(app.con, "invalid");
        assertTrue(rep.isEmpty());
    }

    //Test with null continent
    @Test
    void testGetCapitalCitiesInContinentWithNullContinent()
    {
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInContinent(app.con, null);
        assertTrue(rep.isEmpty());
    }

    //Test with null connection
    @Test
    void testGetCapitalCitiesInContinentWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInContinent(cons, "Antarctica");
        assertEquals(rep, null);
    }

    /**
     * Tests for getCapitalCitiesInRegion
     */
    //Test normal with region antarctica
    @Test
    void testGetCapitalCitiesInRegion()
    {
        boolean found = false;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInRegion(app.con, "Antarctica");
        for(CapitalCityReport report : rep)
        {
            if("South Georgia and the South Sandwich Islands".equals(report.Country) || "Heard Island and McDonald Islands".equals(report.Country) || "Bouvet Island".equals(report.Country) || "French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country))
            {
                found = true;
                assertTrue(found);
                found = false;
            }
            else
            {
                assertTrue(found);
            }
        }
        cities.displayCapitalCities(rep);
    }

    //Test with invalid region
    @Test
    void testGetCapitalCitiesInRegionWithInvalidRegion()
    {
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInRegion(app.con, "invalid");
        assertTrue(rep.isEmpty());
    }

    //Test with null region
    @Test
    void testGetCapitalCitiesInRegionWithNullRegion()
    {
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInRegion(app.con, null);
        assertTrue(rep.isEmpty());
    }

    //Test with null connection
    @Test
    void testGetCapitalCitiesInRegionWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getCapitalCitiesInRegion(cons, "Antarctica");
        assertEquals(rep, null);
    }

    /**
     * Test for getTopNCapitalCities
     */

    //Test for normal with n as 10
    @Test
    void testGetTopNCapitalCities()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCities(app.con, 10);
        assertEquals(rep.size(), 10);
        cities.displayCapitalCities(rep);
    }

    //Test n as 0
    @Test
    void testGetTopNCapitalCitiesWith0()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCities(app.con, 0);
        assertEquals(rep.size(), 0);
        cities.displayCapitalCities(rep);
    }

    //Test with null connection
    @Test
    void testGetTopNCapitalCitiesWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCities(cons, 10);
        assertEquals(rep, null);
        cities.displayCapitalCities(rep);
    }

    /**
     * Test for getTopNCapitalCitiesInContinent
     */

    //Test for normal with n as 2 and continent as Antarctica
    @Test
    void testGetTopNCapitalCitiesInContinent()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInContinent(app.con, 2, "Antarctica");
        assertEquals(rep.size(), 2);
        boolean found = false;
        for(CapitalCityReport report : rep)
        {
            if("French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country))
            {
                found = true;
                assertTrue(found);
                found = false;
            }
            else
            {
                assertTrue(found);
            }
        }
        cities.displayCapitalCities(rep);
    }
    //Test n as 0 and continent as antarctica
    @Test
    void testGetTopNCapitalCitiesInContinentWith0()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInContinent(app.con, 0, "Antarctica");
        assertEquals(rep.size(), 0);
        cities.displayCapitalCities(rep);
    }

    //Test with null connection and continent antarctica
    @Test
    void testGetTopNCapitalCitiesInContinentWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInContinent(cons, 2, "Antarctica");
        assertNull(rep);
        cities.displayCapitalCities(rep);
    }

    /**
     * Test for getTopNCapitalCitiesInRegion
     */

    //Test for normal with n as 2 and region antarctica
    @Test
    void testGetTopNCapitalCitiesInRegion()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInRegion(app.con, 2, "Antarctica");
        assertEquals(rep.size(), 2);
        boolean found = false;
        for(CapitalCityReport report : rep)
        {
            if("French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country))
            {
                found = true;
                assertTrue(found);
                found = false;
            }
            else
            {
                assertTrue(found);
            }
        }
        cities.displayCapitalCities(rep);
    }
    //Test n as 0 and region antarctica
    @Test
    void testGetTopNCapitalCitiesInRegionWith0()
    {
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInRegion(app.con, 0, "Antarctica");
        assertEquals(rep.size(), 0);
        cities.displayCapitalCities(rep);
    }

    //Test with null connection
    @Test
    void testGetTopNCapitalCitiesInRegionWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CapitalCityReport> rep = cities.getTopNCapitalCitiesInRegion(cons, 2, "Antarctica");
        assertNull(rep);
        cities.displayCapitalCities(rep);
    }

    /**
     * Tests for storeValues
     */

    //Test for normal
    @Test
    void testStoreValues() throws SQLException {
        ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT city.Name, country.Name, city.Population "
                + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                + "ORDER BY city.Population DESC");
        cities.storeValues(capitalCities, rset);
        assertNotNull(capitalCities);
    }

    //Test for null result set
    @Test
    void testStoreValuesNullResultSet()
    {
        ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();
        ResultSet rset = null;
        cities.storeValues(capitalCities, rset);
        assertEquals(capitalCities.size(), 0);
    }

    //Test for null array
    @Test
    void testStoreValuesNullArray() throws SQLException {
        ArrayList<CapitalCityReport> capitalCities = null;
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT city.Name, country.Name, city.Population "
                + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                + "ORDER BY city.Population DESC");
        cities.storeValues(capitalCities, rset);
        assertNull(capitalCities);
    }
}
