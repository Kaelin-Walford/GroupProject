package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for creating population of capital cities reports
 * Worked on by: Kaelin
 */
public class PopulationOfCapitalCities
{
    //Function to display all the capital cities that was generated
    public void displayCapitalCities(ArrayList<CapitalCityReport> capitalCities)
    {
        //Checks that city has something in it
        if(capitalCities != null)
        {
            //Loops through each entry in capitalCities and outputs its contents
            for(CapitalCityReport capitalCity : capitalCities)
            {
                if(capitalCity != null)
                {
                    System.out.println(
                            capitalCity.Name + " "
                                    + capitalCity.Country + " "
                                    + capitalCity.Population + " ");
                }
            }
        }
        else
        {
            System.out.println("No capital cities found");
        }
    }

    //function to return all the capital cities in the world ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getCapitalCities(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the capital cities in the world
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the capital cities in the world
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details");
            return null;
        }
    }

    //function to return all the capital cities in a continent ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getCapitalCitiesInContinent(Connection con, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the capital cities in a continent
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the capital cities in a continent
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details in the selected continent");
            return null;
        }
    }

    //function to return all the capital cities in a region ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getCapitalCitiesInRegion(Connection con, String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the capital cities in a region
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = '" + region + "' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the capital cities in a region
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get capital city details in the selected region");
            return null;
        }
    }

    //function to return all the top N capital cities in the world ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getTopNCapitalCities(Connection con, int n)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the top N population of all the capital cities in the world
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store the top N capital cities in the world
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N capital city details in the world");
            return null;
        }
    }

    //function to return all the top N capital cities in a continent ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getTopNCapitalCitiesInContinent(Connection con, int n, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the top N population of all the capital cities in a continent
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store the top N capital cities in a continent
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N capital city details in the selected continent");
            return null;
        }
    }

    //function to return all the top N capital cities in a continent ordered from population largest to smallest
    public ArrayList<CapitalCityReport> getTopNCapitalCitiesInRegion(Connection con, int n, String region)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the top N population of all the capital cities in a region
            String strSelect =
                    "SELECT city.Name, country.Name, city.Population "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE country.Region = '" + region + "' "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + n;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store the top N capital cities in a region
            ArrayList<CapitalCityReport> capitalCities = new ArrayList<>();

            //loop through all the capital cities
            while (rset.next())
            {
                //create a variable capitalCity to store an individual capital city
                CapitalCityReport capitalCity = new CapitalCityReport();
                capitalCity.Name = rset.getString("Name");
                capitalCity.Country = rset.getString("country.Name");
                capitalCity.Population = rset.getInt("Population");

                //add the current capital city to the arraylist
                capitalCities.add(capitalCity);
            }
            //returns the arraylist of countries
            return capitalCities;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the capital city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get the top N capital city details in the selected region");
            return null;
        }
    }
}
