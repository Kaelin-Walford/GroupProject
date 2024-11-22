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

public class PopulationOfCountriesIntegrationTest
{
    static PopulationOfCountries countries;
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33060", 300000);
        countries = new PopulationOfCountries();
    }

    /**
     * Tests for getCountry
     */
    //Test normal
    @Test
    void testGetCountry()
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
        countries.displayCountry(rep);
    }

    //Test Null
    @Test
    void testGetCountryNull()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getCountry(cons);
        countries.displayCountry(rep);
    }

    /**
     * Tests for getCountryInContinent
     */

    //test normal with continent asia
    @Test
    void testGetCountryInContinent()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInContinent(app.con,"Asia");
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Continent, "Asia");
        }
        countries.displayCountry(rep);
    }

    //Test with invalid continent
    @Test
    void testGetCountryInContinentWithInvalidContinent()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInContinent(app.con,"invalid");
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Continent, "Invalid");
        }
        countries.displayCountry(rep);
    }

    //Test with null continent
    @Test
    void testGetCountryInContinentWithNull()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInContinent(app.con,null);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Continent, "null");
        }
        countries.displayCountry(rep);
    }

    //Test with null connection
    @Test
    void testGetCountryInContinentWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getCountriesInContinent(cons, "Asia");
        assertEquals(rep, null);
        countries.displayCountry(rep);
    }

    /**
     * Tests for getCountryInRegion
     */

    //Test normal with eastern asia as continent
    @Test
    void testGetCountryInRegion()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInRegion(app.con,"Eastern Asia");
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "Eastern Asia");
        }
        countries.displayCountry(rep);
    }

    //test with invalid region
    @Test
    void testGetCountryInRegionWithInvalidRegion()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInRegion(app.con,"invalid");
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "Invalid");
        }
        countries.displayCountry(rep);
    }

    //test with null region
    @Test
    void testGetCountryInRegionWithNull()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInRegion(app.con,null);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "null");
        }
        countries.displayCountry(rep);
    }

    //test with null connection
    @Test
    void testGetCountryInRegionWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getCountriesInRegion(cons, "Eastern Asia");
        countries.displayCountry(rep);
    }

    /**
     * Tests for getTheTopNCountries
     */

    //Test for normal with n as 10
    @Test
    void testGetTheTopNCountries()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(app.con, 10);
        assertEquals(rep.size(), 10);
        countries.displayCountry(rep);
    }

    //Test with n as 0
    @Test
    void testGetTheTopNCountriesWith0()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(app.con, 0);
        assertEquals(rep.size(), 0);
        countries.displayCountry(rep);
    }

    //Test with null connection
    @Test
    void testGetTheTopNCountriesWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(cons, 10);
        assertEquals(rep, null);
        countries.displayCountry(rep);
    }

    /**
     * Tests for getTheTopNCountriesInContinent
     */

    //test with n as 10 and continent asia
    @Test
    void testGetTheTopNCountriesInContinent()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInContinent(app.con, 10, "Asia");
        assertEquals(rep.size(), 10);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Continent, "Asia");
        }
        countries.displayCountry(rep);
    }

    //test with n as 0 and continent asia
    @Test
    void testGetTheTopNCountriesInContinentWith0()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInContinent(app.con, 0, "Asia");
        assertEquals(rep.size(), 0);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Continent, "Asia");
        }
        countries.displayCountry(rep);
    }

    //test with n as null and continent asia
    @Test
    void testGetTheTopNCountriesInContinentWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInContinent(cons, 10, "Asia");
        countries.displayCountry(rep);
    }

    /**
     * Tests for getTheTopNCountriesInRegion
     */

    //test with n as 10 and region as eastern asia
    @Test
    void testGetTheTopNCountriesInRegion()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInRegion(app.con, 10, "Eastern Asia");
        assertEquals(rep.size(), 8);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "Eastern Asia");
        }
        countries.displayCountry(rep);
    }

    //test with n as 0 and region as eastern asia
    @Test
    void testGetTheTopNCountriesInRegiontWith0()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInRegion(app.con, 0, "Eastern Asia");
        assertEquals(rep.size(), 0);
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "Eastern Asia");
        }
        countries.displayCountry(rep);
    }

    //test with n as null and region as eastern asia
    @Test
    void testGetTheTopNCountriesInRegionWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getTheTopNCountriesInRegion(cons, 10, "Eastern Asia");
        countries.displayCountry(rep);
    }

    /**
     * Tests for storeValues
     */

    //Test for normal
    @Test
    void testStoreValues() throws SQLException {
        ArrayList<CountryReport> country = new ArrayList<>();
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                                           + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                                           + "ORDER BY country.Population DESC");
        countries.storeValues(country, rset);
        assertNotNull(country);
    }

    //Test for null result set
    @Test
    void testStoreValuesNullResultSet()
    {
        ArrayList<CountryReport> country = new ArrayList<>();
        ResultSet rset = null;
        countries.storeValues(country, rset);
        assertEquals(country.size(), 0);
    }

    //Test for null array
    @Test
    void testStoreValuesNullArray() throws SQLException {
        ArrayList<CountryReport> country = null;
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                + "ORDER BY country.Population DESC");
        countries.storeValues(country, rset);
        assertNull(country);
    }
}
