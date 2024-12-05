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

    public void displayCities(ArrayList<CityReport> cities)
    {


        //try
        //{
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

        //}
        //catch (Exception e)
        //{
          //  System.out.println(e);
        //}
    }

    public ArrayList<CityReport> getCitiesIn(Connection con, String continent)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.Continent = '" + continent + "'"
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInContinent(Connection con, String continent, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.Continent = '" + continent + "'"
                            + "ORDER BY city.Population DESC "
                            + "LIMIT " + N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInWorld(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);



            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInWorldTOPN(Connection con, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "ORDER BY city.Population DESC "
                            + "LIMIT "+N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInRegion(Connection con, String cityregion)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a region
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.region = '" + cityregion
                            + "' ORDER BY city.Population DESC ";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInCountry(Connection con,String countryName)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in THE WORLD
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.name = '" + countryName
                            + "' ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> getCitiesInRegionWithN(Connection con, String cityregion, int N)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a region
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.region = '" + cityregion
                            + "' ORDER BY city.Population DESC "
                            + "LIMIT "+N;
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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

    public ArrayList<CityReport> storeValues(ArrayList<CityReport> cities, ResultSet rset)
    {
        if(rset != null)
        {
            try
            {
                //loop through all the capital cities
                while (rset.next())
                {
                    //create a variable capitalCity to store an individual capital city
                    CityReport City = new CityReport();
                    City.name = rset.getString("Name");
                    City.Country = rset.getString("country.Name");
                    City.population = rset.getInt("Population");

                    //add the current capital city to the arraylist
                    cities.add(City);
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
        //returns the arraylist of capital cities
        return cities;
    }

    public ArrayList<CityReport> getCitiesInDist(Connection con, String DistrictE)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the population of all the citys in a continent
            String strSelect =
                    "SELECT city.Name,country.Name,city.Population, city.District "
                            + "FROM country LEFT JOIN city ON country.Code = city.CountryCode "
                            + "WHERE country.Continent = '" + DistrictE + "'"
                            + "ORDER BY city.Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);

            //Create an arraylist to store all the citys in the world
            ArrayList<CityReport> city = new ArrayList<>();

            storeValues(city, rset);

            //loop through all the citys
            while (rset.next())
            {
                //create a variable citytemp to store an individual city
                CityReport cityTemp = new CityReport();
                cityTemp.name = rset.getString("name");
                cityTemp.Country = rset.getString("country.Name");
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







