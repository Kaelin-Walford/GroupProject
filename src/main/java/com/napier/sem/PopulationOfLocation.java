package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for creating population of people in a location reports
 * Worked on by: Logan
 */
public class PopulationOfLocation
{

    //function to display a population that is passed to it
    public void displayPop(LocationPopulation population)
    {

        //checks that population has something in it
        if(population != null)
        {
            if(population != null)
            {
                System.out.println(
                        population.name + " "
                                + population.population + " ");
            }
        }
        else
        {
            System.out.println("No population found");
        }
    }

    //function to get the world population
    public LocationPopulation getPopulation (Connection con) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the world
            String strSelect =
                    "SELECT 'world' AS 'Name', SUM(population) AS 'Population' "
                            + "FROM country";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    //function to get the continent population
    public LocationPopulation getPopulationContinent (Connection con, String continent) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the continent
            String strSelect =
                    "SELECT continent AS 'Name', SUM(population) AS 'Population' "
                            + "FROM country "
                            + "WHERE country.continent = '" + continent + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    //function to get the region population
    public LocationPopulation getPopulationRegion (Connection con, String region) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the region
            String strSelect =
                    "SELECT region AS 'Name', SUM(population) AS 'Population' "
                            + "FROM country "
                            + "WHERE country.region = '" + region + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    //function to get the country population
    public LocationPopulation getPopulationCountry (Connection con, String country) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the region
            String strSelect =
                    "SELECT name AS 'Name', population AS 'Population' "
                            + "FROM country "
                            + "WHERE country.name = '" + country + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    //function to get the district population
    public LocationPopulation getPopulationDistrict (Connection con, String district) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the district
            String strSelect =
                    "SELECT district AS 'Name', SUM(population) AS 'Population' "
                            + "FROM city "
                            + "WHERE city.district = '" + district + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    //function to get the city population
    public LocationPopulation getPopulationcity (Connection con, String city) {

        try {
            LocationPopulation getWorld = new LocationPopulation();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the city
            String strSelect =
                    "SELECT name AS 'Name', population AS 'Population' "
                            + "FROM city "
                            + "WHERE city.name = '" + city + "' ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            storeValues(getWorld, rset);

            //returns the arraylist
            return getWorld;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    public LocationPopulation storeValues(LocationPopulation population, ResultSet rset)
    {
        if (rset != null)
        {
            try {
                //loop through the world
                while (rset.next()) {
                    //create a variable worldTemp to store the world
                    population.name = rset.getString("Name");
                    population.population = rset.getLong("population");

                }

            } catch (Exception e) {
                //returns an error if the query failed to get the population details
                System.out.println(e.getMessage());
                System.out.println("Failed to get population details");
                return null;
            }
        }
        else
        {
            System.out.println("result set empty");
        }

        return population;
    }
}
