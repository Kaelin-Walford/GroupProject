package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        app.connect("localhost:33060", 30000);
        countries = new PopulationOfCountries();

    }

    /**
     * Tests for getCountry
     */
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

    @Test
    void testGetCountryInContinentWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getCountriesInContinent(cons, "Asia");
        countries.displayCountry(rep);
    }

    /**
     * Tests for getCountryInRegion
     */

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
    @Test
    void testGetCountryInRegionWithInvalidContinent()
    {
        ArrayList<CountryReport> rep = countries.getCountriesInRegion(app.con,"invalid");
        for(CountryReport cr : rep)
        {
            assertEquals(cr.Region, "Invalid");
        }
        countries.displayCountry(rep);
    }

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

    @Test
    void testGetTheTopNCountries()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(app.con, 10);
        assertEquals(rep.size(), 10);
        countries.displayCountry(rep);
    }

    @Test
    void testGetTheTopNCountriesWith0()
    {
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(app.con, 0);
        assertEquals(rep.size(), 0);
        countries.displayCountry(rep);
    }


    @Test
    void testGetTheTopNCountriesWithNullConnection()
    {
        Connection cons = null;
        ArrayList<CountryReport> rep = countries.getTheTopNCountries(cons, 10);
        countries.displayCountry(rep);
    }

    /**
     * Tests for getTheTopNCountriesInContinent
     */

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

    @Test
    void testStoreValues() throws SQLException {
        ArrayList<CountryReport> country = new ArrayList<>();
        Statement statement = app.con.createStatement();
        ResultSet rset = statement.executeQuery("SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                                           + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                                           + "ORDER BY country.Population DESC");
        countries.storeValues(country, rset);
    }

    @Test
    void testStoreValuesNullResultSet()
    {
        ArrayList<CountryReport> country = new ArrayList<>();
        ResultSet rset = null;
        countries.storeValues(country, rset);
    }

    @Test
    void testStoreValuesNullArray()
    {
        ResultSet rset = null;
        countries.storeValues(null, rset);
    }
}
