import database.Database;
import handler.CSVFileHandler;
import handler.FileHandler;
import menu.Menu;

import java.io.IOException;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Database database = Database.getInstance();
        FileHandler fileHandler = new CSVFileHandler();
        Scanner scanner = new Scanner(System.in);
        Menu chosenMenu = null;
        while (true) {
            if (chosenMenu != null) {
                try {
                    chosenMenu.showMenu();
                    boolean continueWork = chosenMenu.make(scanner.nextInt());
                    if (!continueWork) {
                        chosenMenu = null;
                    }
                } catch (IOException e) {
                    System.out.println("You got exception while executing command: " + e.getMessage());
                }
            } else {
                try {
                    Menu.selectMenu();
                    chosenMenu = Menu.getMenu(scanner.nextInt());
                    if (chosenMenu != null) {
                        chosenMenu.initialize(database, fileHandler);
                    }
                } catch (Exception e) {
                    System.out.println("You closed the app.");
                    break;
                }
            }
        }
        database.close();
    }
}
