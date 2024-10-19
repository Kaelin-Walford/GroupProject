package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Class for creating population of countries reports
 * Worked on by: Kaelin
 */
public class PopulationOfCountries
{
    public void displayCountry(CountryReport country)
    {
        if(country != null)
        {
            System.out.println(
                    country.Code + " "
                            + country.Name + " "
                            + country.Continent + " "
                            + country.Region + " "
                            + country.Population + " "
                            + country.Capital + " "
            );
        }
    }

    public CountryReport getCountry(String code, Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Code = '" + code + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                CountryReport country = new CountryReport();
                country.Code = rset.getString("code");
                country.Name = rset.getString("name");
                country.Continent = rset.getString("continent");
                country.Region = rset.getString("region");
                country.Population = rset.getInt("population");
                country.Capital = rset.getString("capital");
                return country;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
