package com.napier.sem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;

public class App
{
    public static void main(String[] args)
    {
        // Create new Application
        App a = new App();

        if(args.length < 1){
            // Connect to database
            a.connect("localhost:33060", 90000);
        }
        // Connect to database
        else{

            a.connect(args[0], Integer.parseInt(args[1]));
        }

        {
            //Creating an object of PopulationOfCountries and running the functions
            PopulationOfCountries populationOfCountries = new PopulationOfCountries();

            //Print Country header
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("Country Reports");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the countries in the world
            ArrayList<CountryReport> country = populationOfCountries.getCountry(a.con);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the countries in a continent
            String continent = "Europe";
            country = populationOfCountries.getCountriesInContinent(a.con, continent);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the countries in a region
            String region = "Southern Europe";
            country = populationOfCountries.getCountriesInRegion(a.con, region);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in the world
            int N = 5;
            country = populationOfCountries.getTheTopNCountries(a.con, N);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in a continent
            country = populationOfCountries.getTheTopNCountriesInContinent(a.con, N, continent);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in a region
            country = populationOfCountries.getTheTopNCountriesInRegion(a.con, N, region);
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
        }

        {
            //Creating an object of PopulationOfCities and running the main function
            PopulationOfCities populationOfCities = new PopulationOfCities();

            //Print City header
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City Reports World");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            ArrayList<CityReport> city = populationOfCities.getCitiesInWorld(a.con);
            populationOfCities.displayCities(city);

            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City Reports World TOP N");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            int N = 10;
            city = populationOfCities.getCitiesInWorldTOPN(a.con,N);
            populationOfCities.displayCities(city);

            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City continent");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the cities in a continent
            String continent = "Europe";
            city = populationOfCities.getCitiesIn(a.con, continent);
            populationOfCities.displayCities(city);

            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City continent top N");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the cities in a continent
            String continentN = "Europe";
            N = 10;
            city = populationOfCities.getCitiesInContinent(a.con, continentN,N);
            populationOfCities.displayCities(city);

            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City Region");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            String cityregion = "South America";
            city = populationOfCities.getCitiesInRegion(a.con, cityregion);
            populationOfCities.displayCities(city);

            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City country");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            String countryName = "Brazil";
            city = populationOfCities.getCitiesInCountry(a.con, countryName);
            populationOfCities.displayCities(city);


            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("City district");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            String district = "Zuid-Holland";
            city = populationOfCities.getCitiesInDist(a.con, district);
            populationOfCities.displayCities(city);


        }

        {
            //Creating an object of PopulationOfCapitalCities and running the main function
            PopulationOfCapitalCities populationOfCapitalCities = new PopulationOfCapitalCities();

            //Print capital cities header
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("Capital Cities Report");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the capital cities in the world
            ArrayList<CapitalCityReport> capitalCities = populationOfCapitalCities.getCapitalCities(a.con);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for all the capital cities in a continent
            String continent = "Europe";
            capitalCities = populationOfCapitalCities.getCapitalCitiesInContinent(a.con, continent);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for all the capital cities in a region
            String region = "Southern Europe";
            capitalCities = populationOfCapitalCities.getCapitalCitiesInRegion(a.con, region);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in the world
            int N = 5;
            capitalCities = populationOfCapitalCities.getTopNCapitalCities(a.con, N);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in a continent
            capitalCities = populationOfCapitalCities.getTopNCapitalCitiesInContinent(a.con, N, continent);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in a region
            capitalCities = populationOfCapitalCities.getTopNCapitalCitiesInRegion(a.con, N, region);
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
        }

        {
            //Creating an object of PopulationOfPeople and running the main function
            PopulationOfPeople populationOfPeople = new PopulationOfPeople();
            populationOfPeople.main(args, a);
        }

        {
            //Creating an object of PopulationInformation and running the main function
            PopulationInformation populationInformation = new PopulationInformation();
            populationInformation.main(args, a);
        }

        {
            //Creating an object of LanguageInformation and running the main function
            LanguageInformation languageInformation = new LanguageInformation();
            languageInformation.main(args, a);
        }


        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */

    public Connection con = null;

    /**
     * Connect to the MySQL database.
     */

    public void connect(String location, int delay)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Connection to the database
        //Connection con = null;
        int retries = 10;
        boolean shouldWait = true;
        for (int i = 0; i < retries; ++i)
        {

            System.out.println("Connecting to database...");
            try
            {
                if (shouldWait){
                    // Wait a bit for db to start
                    Thread.sleep(delay);
                }

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */

    public  void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
        else
        {
            System.out.print("Connection is null");
        }
    }


}