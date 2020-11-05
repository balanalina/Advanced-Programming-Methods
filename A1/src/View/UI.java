package View;
import Domain.*;
import Repo.*;
import Controller.*;
import Exception.*;
import java.util.Scanner;

import static java.lang.System.*;

public class UI {
    public static void main(String[] args){
        Repository repo = new Repo(30);
        Controller c = new Controller(repo);
        init(c);
        //test();
        int command;
        Scanner scanner = new Scanner(System.in);
        do{
            printMenu();
            System.out.print("Enter command: ");
            command = scanner.nextInt();
            switch(command) {
                case 3:
                    System.out.print(c.filterByWeight(0.20));
                    break;
                case 0:
                    System.out.print(c.printAll());
                    break;
                case 2:
                    System.out.print("Enter the number of the vegetable to be removed: ");
                    int pos = scanner.nextInt();
                    try {
                        c.remove(pos - 1);
                    }catch(myException e) { System.err.print(e); }
                    break;
                case 1:
                    System.out.print("Enter the type of vegetable: ");
                    scanner.nextLine();
                    String type = scanner.nextLine();
                    System.out.print("Enter the weight: ");
                    double weight = scanner.nextDouble();
                    try {
                        validator(type,weight);
                        if(type.equals("Tomato")|| type.equals("tomato"))
                            c.add(new Tomato(weight));
                        else if( type.equals("Eggplant") || type.equals("eggplant"))
                            c.add(new Eggplant(weight));
                        else
                            c.add(new Peppers(weight));
                    }catch (myException e) { System.err.print(e); continue;}
            }
        }while(command >-1 && command < 4);


    }
    public static void printMenu(){
     System.out.println(" 0. Print all \n 1. Add vegetable \n 2. Remove vegetable \n 3. Print all vegetables with a weight greater than 0.2 kg \n 4. Exit \n");
    }
    private static void validator(String s, double w) throws myException
    {
        if(!s.equals("tomato") && !s.equals("Tomato") && !s.equals("eggplant") && !s.equals("Eggplant") && !s.equals("Pepper") && !s.equals("pepper"))
            throw new myException("There is no such vegetable. Try Tomato, Eggplant or Pepper! \n");
        if(w <= 0 || w >= 10)
            throw new myException("Try a weight between 0.1 and 10 kg!\n");
    }

    public static void test(){
        Tomato t2 = new Tomato(0.7);
        Eggplant e1 = new Eggplant(1);
        Peppers p1 = new Peppers(0.12);
        Peppers p2 = new Peppers(0.21);
        e1.setW(0.9);
        assert e1.getW() == 0.9;
        Repository myRepo = new Repo(10);
        Controller cont  = new Controller(myRepo);
        /*try {
            myRepo.add(t2);
            myRepo.add(e1);
            myRepo.add(p1);
            cont.add(p2);
            assert true;
        }catch (myException e);
        assert myRepo.getLength() == 4;
        assert cont.getLength() == 4;
         */
    }

    private static void init(Controller c){
        Tomato t1 = new Tomato(0.1);
        Tomato t2 = new Tomato(0.7);
        Eggplant e1 = new Eggplant(1);
        Eggplant e2 = new Eggplant(0.2);
        Peppers p1 = new Peppers(0.12);
        Peppers p2 = new Peppers(0.21);
        try{
        c.add(t1);
        c.add(t2);
        c.add(e1);
        c.add(e2);
        c.add(p1);
        c.add(p2);} catch (myException e) {System.err.print(e);}
   }

}
