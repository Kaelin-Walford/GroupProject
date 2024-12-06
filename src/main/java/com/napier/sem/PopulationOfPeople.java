package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for creating population of people reports
 * Worked on by: Logan
 */
public class PopulationOfPeople
{
    //function to display a population that is passed to it
    public void displayPop(WorldPopReport population)
    {

        //checks that population has something in it
        if(population != null)
        {

            if(population != null)
            {
                System.out.println(
                        population.Population + " "
                                + population.PopulationInCity + " "
                                + population.PopulationNotInCity + " ");
            }

        }
        else
        {
            System.out.println("No population found");
        }
    }

    public WorldPopReport getPop (Connection con) {

        try {
            WorldPopReport getWorld = new WorldPopReport();
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the world
            String strSelect =
                    "SELECT ROUND((SUM(country.Population)/1000000), 2) AS Population, ((SUM(city.Population)/SUM(country.Population))*100) AS PopulationInCity, (((SUM(country.Population) - SUM(city.Population))/SUM(country.Population))*100) AS PopulationNotInCity "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode ";
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

    public WorldPopReport storeValues(WorldPopReport world, ResultSet rset)
    {
        if (rset != null)
        {
            try {
                //loop through the world
                while (rset.next()) {
                    //create a variable worldTemp to store the world
                    world.Population = rset.getInt("Population");
                    world.PopulationInCity = rset.getFloat("PopulationInCity");
                    world.PopulationNotInCity = rset.getFloat("PopulationNotInCity");

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

        return world;
    }
}

