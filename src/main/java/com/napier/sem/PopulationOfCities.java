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

    public ArrayList<CityReport> getCity(Connection con)
    {

        return null;
    }
}
