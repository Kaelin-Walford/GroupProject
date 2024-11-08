package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Class for creating population of cities reports
 * Worked on by: Louis
 */
public class PopulationOfCities
{

    public static void displayCities(ArrayList<CityReport> cities)
    {


        try
        {
            if (cities != null)
            {
                for (CityReport city : cities)
                {
                    System.out.println
                            (
                                    city.name + " "
                                    + city.country + " "
                                    + city.district + " "
                                    + city.population + " "
                            );
                }
            }
            else
            {
                throw new Exception("Array Is Null");
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public ArrayList<CityReport> getCity(Connection con, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.name,country.name,city.Population,  city.district "
                            + "FROM country LEFT JOIN city ON country.Capital = city.ID "
                            + "WHERE continent = country.Continent "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual country
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.country = rset.getString("country");
                cityTemp.district = rset.getString("district");
                cityTemp.population = rset.getInt("population");


                //add the current city to the arraylist
                city.add(cityTemp);
            }
            //returns the arraylist of citys
            return city;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }
}
