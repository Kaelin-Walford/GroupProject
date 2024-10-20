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

    //function to display the all the countries in the report that was generated
    public void displayCountry(ArrayList<CountryReport> country)
    {

        //checks that country has something in it
        if(country != null)
        {

            //Loops through each entry in country and outputs the contents of it
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

    //function to get return all the countries in the world ordered by the population from greatest to smallest
    public ArrayList<CountryReport> getCountry(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the countries in a continent
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the countries in the world
            ArrayList<CountryReport> country = new ArrayList<>();

            //loop through all the countries
            while (rset.next())
            {
                //create a variable countryTemp to store an individual country
                CountryReport countryTemp = new CountryReport();
                countryTemp.Code = rset.getString("code");
                countryTemp.Name = rset.getString("name");
                countryTemp.Continent = rset.getString("continent");
                countryTemp.Region = rset.getString("region");
                countryTemp.Population = rset.getInt("population");
                countryTemp.Capital = rset.getString("city.Name");

                //add the current country to the arraylist
                country.add(countryTemp);
            }
            //returns the arraylist of countries
            return country;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the country details
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    //function to get return all the countries in a continent ordered by the population from greatest to smallest
    public ArrayList<CountryReport> getCountriesInContinent(Connection con, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the countries in a continent
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = '" + continent + "'"
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the countries in the continent
            ArrayList<CountryReport> country = new ArrayList<>();

            //loop through all the countries
            while (rset.next())
            {
                //create a variable countryTemp to store an individual country
                CountryReport countryTemp = new CountryReport();
                countryTemp.Code = rset.getString("code");
                countryTemp.Name = rset.getString("name");
                countryTemp.Continent = rset.getString("continent");
                countryTemp.Region = rset.getString("region");
                countryTemp.Population = rset.getInt("population");
                countryTemp.Capital = rset.getString("city.Name");

                //add the current country to the arraylist
                country.add(countryTemp);
            }
            //returns the arraylist of countries
            return country;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the country details
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }


    public ArrayList<CountryReport> getCountriesInRegion(Connection con, String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the countries in a region
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, city.Name "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = '" + region + "'"
                            + "ORDER BY country.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the countries in the region
            ArrayList<CountryReport> country = new ArrayList<>();

            //loop through all the countries
            while (rset.next())
            {
                //create a variable countryTemp to store an individual country
                CountryReport countryTemp = new CountryReport();
                countryTemp.Code = rset.getString("code");
                countryTemp.Name = rset.getString("name");
                countryTemp.Continent = rset.getString("continent");
                countryTemp.Region = rset.getString("region");
                countryTemp.Population = rset.getInt("population");
                countryTemp.Capital = rset.getString("city.Name");

                //add the current country to the arraylist
                country.add(countryTemp);
            }
            //returns the arraylist of countries
            return country;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the country details
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }
}
