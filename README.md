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
