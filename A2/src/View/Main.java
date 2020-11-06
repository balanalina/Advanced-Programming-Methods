package View;

import ADT.ImyList;
import ADT.myList;
import Model.Exception.myException;

public class Main {
    //color red for printing
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
	    UI ui = new UI();
	    try{
	        ui.exe();
        }
	    catch (Exception e){
            System.out.println(ANSI_RED + (e.getMessage()));
            Runtime.getRuntime().exit(1);
        }
    }
}
