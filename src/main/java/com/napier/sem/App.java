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

        // Connect to database
        a.connect();

        {
            //Creating an object of PopulationOfCountries and running the functions
            PopulationOfCountries populationOfCountries = new PopulationOfCountries();

            //create bufferreader to get an input from the console
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

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
            populationOfCities.main(args, a);
        }

        {
            //Creating an object of PopulationOfCapitalCities and running the main function
            PopulationOfCapitalCities populationOfCapitalCities = new PopulationOfCapitalCities();
            populationOfCapitalCities.main(args, a);
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

    public void connect()
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
        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(3000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                // Wait a bit
                Thread.sleep(10000);
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
    }


}