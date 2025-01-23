package Controller;

import View.Printer;

public class ExceptionManager {
    
    public static void inputNotInRange(){
        Printer.printNotInRange();
    }

    public static void inputFormatException(){
        Printer.inputFormatException();
    }
}
