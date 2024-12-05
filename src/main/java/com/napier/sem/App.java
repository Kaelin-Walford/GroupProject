package com.napier.sem;

import java.io.*;
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
            a.connect("localhost:33060", 10000);
        }
        else{
            // Connect to database
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
            a.outputcountries(country, "CountryReport.md");
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the countries in a continent
            String continent = "Europe";
            country = populationOfCountries.getCountriesInContinent(a.con, continent);
            a.outputcountries(country, "CountryContinentReport.md");
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the countries in a region
            String region = "Southern Europe";
            country = populationOfCountries.getCountriesInRegion(a.con, region);
            a.outputcountries(country, "CountryRegionReport.md");
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in the world
            int N = 5;
            country = populationOfCountries.getTheTopNCountries(a.con, N);
            a.outputcountries(country, "TheTopNCountriesReport.md");
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in a continent
            country = populationOfCountries.getTheTopNCountriesInContinent(a.con, N, continent);
            a.outputcountries(country, "TheTopNCountriesContinentReport.md");
            populationOfCountries.displayCountry(country);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report on the top N countries in a region
            country = populationOfCountries.getTheTopNCountriesInRegion(a.con, N, region);
            a.outputcountries(country, "TheTopNCountriesRegionReport.md");
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

            //Print capital cities header
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");
            System.out.println("Capital Cities Report");
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //report for all the capital cities in the world
            ArrayList<CapitalCityReport> capitalCities = populationOfCapitalCities.getCapitalCities(a.con);
            a.outputCapitalCities(capitalCities, "CapitalCitiesReport.md");
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for all the capital cities in a continent
            String continent = "Europe";
            capitalCities = populationOfCapitalCities.getCapitalCitiesInContinent(a.con, continent);
            a.outputCapitalCities(capitalCities, "CapitalCitiesContinentReport.md");
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for all the capital cities in a region
            String region = "Southern Europe";
            capitalCities = populationOfCapitalCities.getCapitalCitiesInRegion(a.con, region);
            a.outputCapitalCities(capitalCities, "CapitalCitiesRegionReport.md");
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in the world
            int N = 5;
            capitalCities = populationOfCapitalCities.getTopNCapitalCities(a.con, N);
            a.outputCapitalCities(capitalCities, "TheTopNCapitalCitiesReport.md");
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in a continent
            capitalCities = populationOfCapitalCities.getTopNCapitalCitiesInContinent(a.con, N, continent);
            a.outputCapitalCities(capitalCities, "TheTopNCapitalCitiesContinentReport.md");
            populationOfCapitalCities.displayCapitalCities(capitalCities);
            System.out.println("--------------------------------------------------------\n\n--------------------------------------------------------");

            //Report for the top N capital cities in a region
            capitalCities = populationOfCapitalCities.getTopNCapitalCitiesInRegion(a.con, N, region);
            a.outputCapitalCities(capitalCities, "TheTopNCapitalCitiesRegionReport.md");
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

    public int retries = 10;

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

        boolean shouldWait = false;
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
                shouldWait = true;
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

    /**
     * Outputs to Markdown
     *
     * @param countries
     */
    public void outputcountries(ArrayList<CountryReport> countries, String filename) {
        // Check countries is not null
        if (countries == null) {
            System.out.println("No countries");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Code | Name | Continent | Region | Population | Capital |\r\n");
        sb.append("| --- | --- | --- | --- | --- | --- |\r\n");
        // Loop over all countries in the list
        for (CountryReport country : countries) {
            if (country == null) continue;
            sb.append("| " + country.Code + " | " +
                    country.Name + " | " + country.Continent + " | " +
                    country.Region + " | " + country.Population + " | "
                    + country.Capital +  " |\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param capitalCities
     */
    public void outputCapitalCities(ArrayList<CapitalCityReport> capitalCities, String filename) {
        // Check capitalCities is not null
        if (capitalCities == null) {
            System.out.println("No capital cities");
            return;
        }

        StringBuilder sb = new StringBuilder();
        // Print header
        sb.append("| Name | Country | Population |\r\n");
        sb.append("| --- | --- | --- |\r\n");
        // Loop over all capital cities in the list
        for (CapitalCityReport city : capitalCities) {
            if (city == null) continue;
            sb.append("| " + city.Name + " | " +
                    city.Country + " | " + city.Population + " |\r\n");
        }
        try {
            new File("./reports/").mkdir();
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("./reports/" + filename)));
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}