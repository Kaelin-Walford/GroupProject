package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationOfContinents
{
    //function to display the all the continents in the report that was generated
    public void displayContinent(ArrayList<ContinentReport> continent)
    {

        //checks that continent has something in it
        if(continent != null)
        {

            //Loops through each entry in continent and outputs the contents of it
            for(ContinentReport continentTemp : continent){
                System.out.println(
                                continentTemp.Continent + " "
                                        + continentTemp.PopulationInMillions + " "
                                        + continentTemp.PercentageInCities + " "
                                        + continentTemp.PercentageOutwithCities + " ");
            }
        }
    }

    //function to get return all the continents in the world ordered by the population from greatest to smallest
    public ArrayList<ContinentReport> getContinent(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the continents
            String strSelect =
                    "SELECT Continent, ROUND(SUM(country.Population)/1000000,2) AS PopulationInMillions, ROUND(((SUM(city.Population)/SUM(country.Population))*100),0) AS PercentageInCities, ROUND((((SUM(country.Population) - SUM(city.Population))/SUM(country.Population))*100),0) AS PercentageOutwithCities "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            +"GROUP BY continent";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            //Create an arraylist to store all the continents in the world
            ArrayList<ContinentReport> continent = new ArrayList<>();

            //loop through all the countries
            while (rset.next())
            {
                //create a variable countryTemp to store an individual country
                ContinentReport continentTemp = new ContinentReport();
                continentTemp.Continent = rset.getString("continent");
                continentTemp.PopulationInMillions = rset.getInt("populationInMillions");
                continentTemp.PercentageInCities = rset.getInt("percentageInCities");
                continentTemp.PercentageOutwithCities = rset.getInt("percentageOutwithCities");

                //add the current continent to the arraylist
                continent.add(continentTemp);
            }
            //returns the arraylist of continents
            return continent;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the continent details
            System.out.println(e.getMessage());
            System.out.println("Failed to get continent details");
            return null;
        }
    }
}

