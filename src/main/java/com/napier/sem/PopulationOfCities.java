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

    //Function to display cities
    public void displayCities(ArrayList<CityReport> cities)
    {
            if (cities != null)
            {
                for (CityReport city : cities)
                {
                    if (city != null)
                    {
                        System.out.println
                                (
                                                  city.name + " "
                                                + city.Country + " "
                                                + city.district + " "
                                                + city.population + " "
                                );

                    }
                }
            }
            else
            {
                System.out.println("Array Is Null");
            }
    }

    //Function to get the cities in a continent
    public ArrayList<CityReport> getCitiesInContinent(Connection con, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM city LEFT JOIN country ON country.Code = city.CountryCode "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //Function to get the top n cities in a continent
    public ArrayList<CityReport> getCitiesInContinentTopN(Connection con, String continent, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District,city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //Get all cities in the world
    public ArrayList<CityReport> getCitiesInWorld(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM city LEFT JOIN country ON country.Code = city.CountryCode "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //get the top n cities in the world
    public ArrayList<CityReport> getCitiesInWorldTOPN(Connection con, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name AS city_name,country.Name AS country_name, city.District,city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT "+ N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //get the cities in a region
    public ArrayList<CityReport> getCitiesInRegion(Connection con, String cityregion)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a region
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.region = '" + cityregion
                            + "' ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //get the cities in a country
    public ArrayList<CityReport> getCitiesInCountry(Connection con,String countryName)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.name = '" + countryName
                            + "' ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //get the top n cities in a country
    public ArrayList<CityReport> getCitiesInCountryWithN(Connection con, String citycountry, int N) {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a country
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.name = '" + citycountry
                            + "' ORDER BY city.Population DESC "
                            + "LIMIT " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the country
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //returns the arraylist of citys
            return city;
        } catch (Exception e) {
            //returns an error if the query failed to get the city details
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }
    }

    //get the top n cities in a region
    public ArrayList<CityReport> getCitiesInRegionWithN(Connection con, String cityregion, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a region
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.region = '" + cityregion
                            + "' ORDER BY city.Population DESC "
                            + "LIMIT "+N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    public ArrayList<CityReport> storeValues(ArrayList<CityReport> cities, ResultSet rset)
    {
        if(rset != null)
        {
            try
            {
                //loop through all the cities
                while (rset.next())
                {
                    //create a variable City to store an individual capital city
                    CityReport cityTemp = new CityReport();
                    cityTemp.name = rset.getString("city_name");
                    cityTemp.Country = rset.getString("country_name");
                    cityTemp.district = rset.getString("city.district");
                    cityTemp.population = rset.getInt("city.population");

                    //add the current city to the arraylist
                    cities.add(cityTemp);
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("result set empty");
        }
        //returns the arraylist of cities
        return cities;
    }

    //get cities in a district
    public ArrayList<CityReport> getCitiesInDist(Connection con, String DistrictE)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE city.district = '" + DistrictE + "'"
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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

    //get the top n cities in a district
    public ArrayList<CityReport> getCitiesInDistrictWithN(Connection con, String citydistrict, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a district
            String strSelect =
                    "SELECT city.Name AS city_name, country.Name AS country_name, city.District, city.Population "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE city.district = '" + citydistrict
                            + "' ORDER BY city.Population DESC "
                            + "LIMIT "+N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the district
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

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







