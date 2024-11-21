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

            //Outputs the population of the world

            System.out.println(
                    "World" + " "
                            +   population.Population + " "
                            +   population.PopulationInCity + " "
                            +   population.PopulationNotInCity + " ");

        }
    }

    public WorldPopReport getPop(Connection con) {

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of the world
            String strSelect =
                    "SELECT SUM(country.Population), ROUND(((SUM(city.population)/SUM(country.Population))*100),0), ROUND(((SUM(country.Population) - SUM(city.population))/SUM(country.Population))*100),0) "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            //Create an arraylist to store the world
            WorldPopReport world = new WorldPopReport();

            //loop through the world
            while (rset.next())
            {
                //create a variable popTemp to store the world
                WorldPopReport worldTemp = new WorldPopReport();
                worldTemp.Population = rset.getInt("population");
                worldTemp.PopulationInCity = rset.getInt("populationInCity");
                worldTemp.PopulationNotInCity = rset.getInt("populationNotInCity");

                //add the population to arraylist
                world = worldTemp;
            }
            //returns the arraylist of continents
            return world;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the population details
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }
}
