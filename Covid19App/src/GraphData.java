import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.function.DoubleToIntFunction;

/**
 * Class for get all data for display in Covid19-Tracker Application.
 *
 * @author Bhatara Chaemchan Ske17
 */
public class GraphData {
    /**
     * Method t0 get data as BufferedReader from specific url
     *
     * @param url : url the want to connect and read the data from.
     * @return BufferedReader that read the data
     */
    public static BufferedReader urlBufferedReader(String url) {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        return new BufferedReader(new InputStreamReader(yc.getInputStream()));
    }

    /**
     * Method for get date for display.
     *
     * @return date ArrayList of date.
     * @throws Exception when URL not found.
     */
    public ArrayList<String> getDate() throws Exception {
        ArrayList<String> date = new ArrayList<>();
        BufferedReader in = urlBufferedReader("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        //initial a String for read the BufferedReader
        String inputLine;
        //read the input and add each line splited to the ArrayList
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            if (i >= 1) {
                date.add(inputLine.split(",")[0]);
            }
            i++;
        }
        return date;
    }

    /**
     * Method that return a data of country and date for display from URL.
     *
     * @param where : the location of data
     * @param date : the date of data
     * @return worldData : List of confirm cases, new cases, new deaths and total death for display.
     * @throws Exception when URL not found.
     */
    public String[] getWorldData(String where, String date) throws Exception {
        ArrayList<String> allData = new ArrayList<>();
        BufferedReader in = urlBufferedReader("https://covid.ourworldindata.org/data/ecdc/full_data.csv");

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.split(",")[0].equalsIgnoreCase(date) && inputLine.split(",")[1].equalsIgnoreCase(where)) {
                allData.add(inputLine);
            }
        }
        String[] worldData = allData.get(allData.size() - 1).split(",");

        return worldData;
    }

    /**
     * Method that return urls that related to the type.
     *
     * @param type : specific type (confirm cases, new cases, new deaths and total death for display)
     * @return urls that related to the specific type
     */
    public static String urlSelector(String type) {
        if (type.equals("Total confirmed cases")) return "https://covid.ourworldindata.org/data/ecdc/total_cases.csv";
        else if (type.equals("Total deaths")) return "https://covid.ourworldindata.org/data/ecdc/total_deaths.csv";
        else if (type.equals("New confirmed cases")) return "https://covid.ourworldindata.org/data/ecdc/new_cases.csv";
        else return "https://covid.ourworldindata.org/data/ecdc/new_deaths.csv";
    }

    public static int findIndexOfCountry(String line,String country) {
        int index = 0;
        String[] countryarray = new String[0];
        //if firstline is readable, split countries and contain them in contryarray
        if (line != null) {
            countryarray = inputLine.split(",");
        }
        //find index of specific country
        for (int i = 0; i < countryarray.length; i++) {
            //found specific country, set index value and break loop
            if (countryarray[i].equalsIgnoreCase(country)) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * Method that return a confirm cases, new cases, new deaths and total death for display from URL.
     *
     * @param type : a type of data.
     * @param country : a country of data.
     * @return ArrayList of confirm cases, new cases, new deaths and total death for display.
     * @throws Exception when URL not found.
     */
    public static ArrayList<String> getCountryConfirmCase(String type, String country) throws Exception {
        //initial String ArrayList for return
        ArrayList<String> data = new ArrayList<>();
        //get BufferedReader input using urlSelector
        BufferedReader in = urlBufferedReader(urlSelector(type));
        //initial String to read the BufferedReader firstline
        String inputLine = in.readLine();
        //find index of specfic country from the firstline
        int index = findIndexOfCountry(inputLine,country);
        //initial cummalative frequent of cases
        int cfofcase = 0;
        //use while-loop to collect data of each date
        //while each line is readable
        while ((inputLine = in.readLine()) != null) {
            try {
                boolean isEmpty = inputLine.split(",")[index].isEmpty();
                if (!(isEmpty) {
                    cfofcase = Integer.parseInt(inputLine.split(",")[index]);
                    data.add(inputLine.split(",")[index]);
                }
                else {
                    data.add(String.valueOf(cfofcase));
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                data.add("0");
            }
        }
        return data;
    }

    /**
     * Method that return a country named.
     *
     * @return data a List of Country named.
     * @throws IOException when URL not found.
     */
    public String[] getCountry() throws IOException {
        String[] data = new String[0];
        BufferedReader in = urlBufferedReader("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");

        String inputLine;
        if ((inputLine = in.readLine()) != null) {
            //use substring since index of 5 becase from data 'date,World,Afgha...'
            //we don't need 'date,' so we start from word World,....
            String ss = inputLine.substring(5, inputLine.length());
            //split them and re-initial value of the string array
            data = ss.split(",");
        }
        return data;
    }
}
