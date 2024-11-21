
package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PopulationOfCitiesIntegrationTest {
    static PopulationOfCities cities;
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060", 30000);
        cities = new PopulationOfCities();
    }

    /**
     * Test for getCitiesInWorld
     */
    //Test normal
    @Test
    void testGetCities() {
        boolean found = false;
        ArrayList<CityReport> rep = cities.getCitiesInWorld(app.con);
        for (CityReport report : rep) {
            if ("Tokyo".equals(report.name) && "Japan".equals(report.Country) && report.population == 7980230) {
                found = true;
                break;
            }
        }
        assertTrue(found);
        cities.displayCities(rep);
    }

    //Test Null
    @Test
    void testGetCountryNull() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesInWorld(cons);
        cities.displayCities(rep);
    }

    /**
     * test for getCitiesInContinent
     */

    //Test normal with continent Antarctica
    @Test
    void testGetCitiesInContinent() {
        boolean found = false;
        ArrayList<CityReport> rep = cities.getCitiesIn(app.con, "Antarctica");
        for (CityReport report : rep) {
            if ("South Georgia and the South Sandwich Islands".equals(report.Country) || "Heard Island and McDonald Islands".equals(report.Country) || "Bouvet Island".equals(report.Country) || "French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country)) {
                found = true;
                assertTrue(found);
                found = false;
            } else {
                assertTrue(found);
            }
        }
        cities.displayCities(rep);
    }

    //Test with invalid continent
    @Test
    void testGetCitiesInContinentWithInvalidContinent() {
        ArrayList<CityReport> rep = cities.getCitiesIn(app.con, "invalid");
        assertTrue(rep.isEmpty());
    }

    //Test with null continent
    @Test
    void testGetCitiesInContinentWithNullContinent() {
        ArrayList<CityReport> rep = cities.getCitiesIn(app.con, null);
        assertTrue(rep.isEmpty());
    }

    //Test with null connection
    @Test
    void testGetCitiesInContinentWithNullConnection() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesIn(cons, "Antarctica");
        assertEquals(rep, null);
    }

    /**
     * Tests for getCitiesInRegion
     */
    //Test normal with region antarctica
    @Test
    void testGetCitiesInRegion() {
        boolean found = false;
        ArrayList<CityReport> rep = cities.getCitiesInRegion(app.con, "Antarctica");
        for (CityReport report : rep) {
            if ("South Georgia and the South Sandwich Islands".equals(report.Country) || "Heard Island and McDonald Islands".equals(report.Country) || "Bouvet Island".equals(report.Country) || "French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country)) {
                found = true;
                assertTrue(found);
                found = false;
            } else {
                assertTrue(found);
            }
        }
        cities.displayCities(rep);
    }

    //Test with invalid region
    @Test
    void testGetCitiesInRegionWithInvalidRegion() {
        ArrayList<CityReport> rep = cities.getCitiesInRegion(app.con, "invalid");
        assertTrue(rep.isEmpty());
    }

    //Test with null region
    @Test
    void testGetCitiesInRegionWithNullRegion() {
        ArrayList<CityReport> rep = cities.getCitiesInRegion(app.con, null);
        assertTrue(rep.isEmpty());
    }

    //Test with null connection
    @Test
    void testGetCitiesInRegionWithNullConnection() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesInRegion(cons, "Antarctica");
        assertEquals(rep, null);
    }

    /**
     * Test for getTopNCities
     */

    //Test for normal with n as 10
    @Test
    void testGetTopNCities() {
        ArrayList<CityReport> rep = cities.getCitiesInWorldTOPN(app.con, 10);
        assertEquals(rep.size(), 10);
        cities.displayCities(rep);
    }

    //Test n as 0
    @Test
    void testGetTopNCitiesWith0() {
        ArrayList<CityReport> rep = cities.getCitiesInWorldTOPN(app.con, 0);
        assertEquals(rep.size(), 0);
        cities.displayCities(rep);
    }

    //Test with null connection
    @Test
    void testGetTopNCitiesWithNullConnection() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesInWorldTOPN(cons, 10);
        assertEquals(rep, null);
        cities.displayCities(rep);
    }

    /**
     * Test for getTopNCitiesInContinent
     */

    //Test for normal with n as 2 and continent as Antarctica
    @Test
    void testGetTopNCitiesInContinent() {
        ArrayList<CityReport> rep = cities.getCitiesInContinent(app.con, "Antarctica", 2);
        assertEquals(rep.size(), 2);
        boolean found = false;
        for (CityReport report : rep) {
            if ("French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country)) {
                found = true;
                assertTrue(found);
                found = false;
            } else {
                assertTrue(found);
            }
        }
        cities.displayCities(rep);
    }

    //Test n as 0 and continent as antarctica
    @Test
    void testGetTopNCitiesInContinentWith0() {
        ArrayList<CityReport> rep = cities.getCitiesInContinent(app.con, "Antarctica", 0);
        assertEquals(rep.size(), 0);
        cities.displayCities(rep);
    }

    //Test with null connection and continent antarctica
    @Test
    void testGetTopNCitiesInContinentWithNullConnection() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesInContinent(cons, "Antarctica", 3);
        assertNull(rep);
        cities.displayCities(rep);
    }

    /**
     * Test for getTopNCitiesInRegion
     */

    //Test for normal with n as 2 and region antarctica
    @Test
    void testGetTopNCitiesInRegion() {
        ArrayList<CityReport> rep = cities.getCitiesInRegionWithN(app.con, "Antarctica", 2);
        assertEquals(rep.size(), 2);
        boolean found = false;
        for (CityReport report : rep) {
            if ("French Southern territories".equals(report.Country) || "Antarctica".equals(report.Country)) {
                found = true;
                assertTrue(found);
                found = false;
            } else {
                assertTrue(found);
            }
        }
        cities.displayCities(rep);
    }

    //Test n as 0 and region antarctica
    @Test
    void testGetTopNCitiesInRegionWith0() {
        ArrayList<CityReport> rep = cities.getCitiesInRegionWithN(app.con, "Antarctica", 0);
        assertEquals(rep.size(), 0);
        cities.displayCities(rep);
    }

    //Test with null connection
    @Test
    void testGetTopNCitiesInRegionWithNullConnection() {
        Connection cons = null;
        ArrayList<CityReport> rep = cities.getCitiesInRegionWithN(cons, "Antarctica", 2);
        assertNull(rep);
        cities.displayCities(rep);
    }



    /**
     * Tests for storeValues
     */

    //Test for normal
    @Test
    void testStoreValues() throws SQLException {
        ArrayList<CityReport> Cities = new ArrayList<>();
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT city.Name, country.Name, city.Population "
                + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                + "ORDER BY city.Population DESC");
        cities.storeValues(Cities, rset);
        assertNotNull(Cities);
    }

    //Test for null result set
    @Test
    void testStoreValuesNullResultSet()
    {
        ArrayList<CityReport> Cities = new ArrayList<>();
        ResultSet rset = null;
        cities.storeValues(Cities, rset);
        assertEquals(Cities.size(), 0);
    }

    //Test for null array
    @Test
    void testStoreValuesNullArray() throws SQLException {
        ArrayList<CityReport> Cities = null;
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT city.Name, country.Name, city.Population "
                + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                + "ORDER BY city.Population DESC");
        cities.storeValues(Cities, rset);
        assertNull(Cities);
    }
}
