package com.napier.sem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PopulationOfLanguages {
    //function to display the all the languages in the report that was generated
    public void displayLanguages(ArrayList<LanguagesReport> language)
    {

        //checks that continent has something in it
        if(language != null)
        {

            //Loops through each entry in country and outputs the required contents of it
            for(LanguagesReport languageTemp : language){
                System.out.println(
                        languageTemp.Language + " "
                                + languageTemp.Population + " "
                                + languageTemp.PopulationPercentage + " ");
            }
        }
    }

    //function to get return all the required languages
    public ArrayList<LanguagesReport> getLanguages(Connection con)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement to get the requested languages and their populations
            String strSelect =
                    "SELECT DISTINCT Language, SUM(SUM(Population)*(percentage/100)) AS Population, (SUM(Population)*(percentage/100))/(SUM(Population)) AS PopulationPercentage "
                            + "FROM countrylanguage LEFT JOIN country ON country.Code = countrylanguage.CountryCode "
                            + "WHERE Language = 'Chinese' OR Language = 'English' OR Language = 'Hindi' OR Language = 'Spanish' OR Language = 'Arabic' "
                            + "GROUP BY Language, Population, PopulationPercentage";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);


            //Create an arraylist to store all the requested languages
            ArrayList<LanguagesReport> language = new ArrayList<>();

            //loop through all the countries
            while (rset.next())
            {
                //create a variable languageTemp to store an individual language
                LanguagesReport languageTemp = new LanguagesReport();
                languageTemp.Language = rset.getString("language");
                languageTemp.Population = rset.getInt("population");
                languageTemp.PopulationPercentage = rset.getInt("populationPercentage");

                //add the current language to the arraylist
                language.add(languageTemp);
            }
            //returns the arraylist of languages
            return language;
        }
        catch (Exception e)
        {
            //returns an error if the query failed to get the language details
            System.out.println(e.getMessage());
            System.out.println("Failed to get language details");
            return null;
        }
    }

}
