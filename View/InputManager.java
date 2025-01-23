package View;

import Controller.ExceptionManager;
import Model.Database;

import java.util.ArrayList;
import java.util.Scanner;

public class InputManager {

    public static Scanner sc = new Scanner(System.in);

    public static void clearConsole() {
        //IMPLEMENTATION IS CONSOLE SPECIFIC
    }


    public static String getPassword(){
        Printer.enterPassword();
        String password = sc.nextLine();
        clearConsole();
        return password;
    }

    public static String getReason(){
        Printer.printGetReason();
        String reason = sc.nextLine();
        clearConsole();
        return reason;
    }
    
    public static ArrayList getMovie(){
        ArrayList res = new ArrayList();
        res.add(Database.movieCount + 1);
        Printer.enterMovieName();
        res.add(sc.nextLine());
        Printer.enterStreamableOrNot();
        String streamable = sc.nextLine();
        if(streamable.equals("Y")) {
            res.add(true);
            Printer.enterPath();
            res.add(sc.nextLine());
        }
        else if(streamable.equals("N")) {
            res.add(false);
            res.add(null);
        }
        else {
            clearConsole();
            return null;
        }
        clearConsole();
        return res;
    }

    public static int getItemInput(int maxOption){
        Printer.enterItem();
        int pos = 0;
        try {
            pos = Integer.parseInt(sc.nextLine());
        }
        catch(Exception e){
            ExceptionManager.inputFormatException();
            return 0;
        }
        finally {
            clearConsole();
        }

        if(pos > maxOption) {
            ExceptionManager.inputNotInRange();
            clearConsole();
            return 0;
        }
        clearConsole();
        return pos;
    }

    public static String getUserName(){
        Printer.enterUserName();
        String userName = sc.nextLine();
        clearConsole();
        return userName;
    }

    public static String confirmPassword(){
        Printer.enterConfirmPassword();
        String password = sc.nextLine();
        clearConsole();
        return password;
    }

    public static int getChoiceInput(int maxOption){
        Printer.enterChoice();
        int pos = 0;
        try {
            pos = Integer.parseInt(sc.nextLine());
        }
        catch(Exception e){
            ExceptionManager.inputFormatException();
            return 0;
        }
        finally {
            clearConsole();
        }
        if(pos > maxOption) {
            ExceptionManager.inputNotInRange();
            clearConsole();
            return 0;
        }
        clearConsole();
        return pos;
    }

    public static String getMessage(){
        Printer.getMessage();
        String message = sc.nextLine();
        return message;
    }

    public static int getAmount(){
        Printer.enterAmount();
        int inputAmount = -1;
        try {
            inputAmount = Integer.parseInt(sc.nextLine());
        }
        catch(Exception e){
            ExceptionManager.inputFormatException();
        }
        finally {
            clearConsole();
        }
        return inputAmount;
    }
}
