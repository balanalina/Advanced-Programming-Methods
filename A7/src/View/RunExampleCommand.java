package View;

import Controller.Controller;
import Model.Exception.myException;

public class RunExampleCommand extends Command {
    private Controller controller;
    //color red for printing
    public static final String ANSI_RED = "\u001B[31m";

    public RunExampleCommand(String key, String desc, Controller ctr) {
        super(key, desc);
        this.controller = ctr;
    }

    @Override
    public void execute() {
        try {
            controller.typeCheck();
            controller.allSteps();
        } catch (Exception e){
            System.err.print(ANSI_RED + (e.getMessage()));
            Runtime.getRuntime().exit(1);
        }
    }
}