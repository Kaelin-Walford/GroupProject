package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for creating population of countries reports
 * Worked on by: Kaelin
 */
public class PopulationOfCountries
{
    public void displayCountry(ArrayList<CountryReport> country)
    {
        if(country != null)
        {
            for(CountryReport countryTemp : country){
                System.out.println(
                        countryTemp.Code + " "
                                + countryTemp.Name + " "
                                + countryTemp.Continent + " "
                                + countryTemp.Region + " "
                                + countryTemp.Population + " "
                                + countryTemp.Capital + " ");
            }
        }
    }

    public ArrayList<CountryReport> getCountry(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<CountryReport> country = new ArrayList<>();

            // Return new employee if valid.
            // Check one is returned
            while (rset.next())
            {
                CountryReport countryTemp = new CountryReport();
                countryTemp.Code = rset.getString("code");
                countryTemp.Name = rset.getString("name");
                countryTemp.Continent = rset.getString("continent");
                countryTemp.Region = rset.getString("region");
                countryTemp.Population = rset.getInt("population");
                countryTemp.Capital = rset.getString("capital");
                country.add(countryTemp);
            }
            return country;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
