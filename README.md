# Refactoring
## Introductions 
This is an assignment for practice refactoring.  
Using [Bhatara-Covid19Tracker](https://github.com/OOP2020/pa4-bhatara007)

## In Main.java
Since the code is just for running the application so I don't modify it, just add Java comments to make reader read more clear.
### Before

```
public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        primaryStage.setTitle("Covid19 Tracker");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
}
```
### After

```
public void start(Stage primaryStage) throws Exception {
        //set root and load the menu fxml file when application started.
        Parent root = FXMLLoader.load(getClass().getResource("fxml/Menu.fxml"));
        //set stage's title.
        primaryStage.setTitle("Covid19 Tracker");
        //set stage's screen to the root.
        primaryStage.setScene(new Scene(root));
        //fix stage size.
        primaryStage.setResizable(false);
        //show the application.
        primaryStage.show();
}
```

## In GraphData.java

The code we can extract some method to make the reader read clearer and I also add comments in parts to make it more understandable such as :

### Before
```
public ArrayList<String> getDate() throws Exception {
        ArrayList<String> date = new ArrayList<>();

        URL oracle = new URL("https://covid.ourworldindata.org/data/ecdc/total_cases.csv");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            if (i >= 1) {
                date.add(inputLine.split(",")[0]);
            }
            i++;
        }
        return date;
    }
```
### After 
```
public static BufferedReader urlBufferedReader(String url) {
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        return new BufferedReader(new InputStreamReader(yc.getInputStream()));
    }

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

```
### Before
```
public static ArrayList<String> getCountryConfirmCase(String type, String country) throws Exception {

        String url = "https://covid.ourworldindata.org/data/ecdc/new_deaths.csv";

        if (type.equals("Total confirmed cases")) url = "https://covid.ourworldindata.org/data/ecdc/total_cases.csv";
        else if (type.equals("Total deaths")) url = "https://covid.ourworldindata.org/data/ecdc/total_deaths.csv";
        else if (type.equals("New confirmed cases")) url = "https://covid.ourworldindata.org/data/ecdc/new_cases.csv";

        String[] c = new String[0];
        ArrayList<String> data = new ArrayList<>();
        int index = 0;

        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

        String inputLine;

        if ((inputLine = in.readLine()) != null) {
            c = inputLine.split(",");
        }

        for (int i = 0; i < c.length; i++) {
            if (c[i].equalsIgnoreCase(country)) {
                index = i;
            }
        }
        int i = 0;
        while ((inputLine = in.readLine()) != null) {
            try {
                if (!(inputLine.split(",")[index]).isEmpty()) {
                    i = Integer.parseInt(inputLine.split(",")[index]);
                }

                if (inputLine.split(",")[index].isEmpty()) {
                    data.add(String.valueOf(i));
                } else {
                    data.add(inputLine.split(",")[index]);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                data.add("0");
            }
        }
        return data;
    }
```

### After
```
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
```
## In LineChartController.java
Delete never-used method
```
public void setAlert(String alertText) {
        alert.setText(alertText);
    }
```

## For others
For other classes and methods, The code is quite clear and do its own work well.  
Especially in controller classes that each method do thier own work well.

