package View;

import ADT.myDictionary;
import ADT.myList;
import ADT.myStack;
import Controller.Controller;
import Model.Exception.myException;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Value.IntValue;
import Repository.IRepository;
import Repository.Repository;
import Model.Value.*;

import java.util.Scanner;

public class UI {
    //return statements corresponding to the following programs

    // int v; v=2;Print(v);
    private IStatement ex1(){
        return new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement(
        new ValueExpression(new IntValue(2)),"v"),new PrintStatement(new VariableExpression("v"))));
    }

    //int a;int b; a=2+3*5;b=a+1;Print(b);
    private IStatement ex2(){
        return new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement(new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),"MULTIPLICATION"),
                                "PLUS"),"a"),new CompoundStatement(new AssignmentStatement(new ArithmeticExpression(new VariableExpression("a"),
                                new ValueExpression(new IntValue(1)),"PLUS"),"b"),new PrintStatement(new VariableExpression("b"))))));
    }

    //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v);
    private IStatement ex3(){
        return new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement(new ValueExpression(new BoolValue(true)),"a"),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement(new ValueExpression(new IntValue(2)),"v"),
                                        new AssignmentStatement(new ValueExpression(new IntValue(3)),"v")), new PrintStatement(new VariableExpression("v"))))));
    }

    //int v; v=false; Print(v);
    private IStatement ex4(){
        return new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement(new ValueExpression(new BoolValue()),"v"),
                        new PrintStatement(new VariableExpression("v"))));
    }

    private void printMenu(){
        String s="\n";
        s += "1 - Execute program: \n int v; \n v=2; \n Print(v); \n\n";
        s += "2 - Execute program: \n int a; \n int b; \n a=2+3*5; \n b=a+1; \n Print(b) \n\n";
        s += "3 - Execute program: \n bool a; \n int v; \n a=true; \n (If a Then v=2 Else v=3); \n Print(v) \n\n";
        s += "4 - Execute program: \n int v; \n v=false; \n print(v) \n\n";
        System.out.println(s);
    }

    public void exe() throws myException{
        Scanner scan = new Scanner(System.in);
        String command = "";
        IStatement statement;
        printMenu();
        System.out.println("Enter command: ");
        command = scan.nextLine();
        switch (command){
            case "1":
                statement = ex1();
                break;
            case "2":
                statement = ex2();
                break;
            case "3":
                statement = ex3();
                break;
            case "4":
                statement = ex4();
                break;
            default:
                statement = new NopStatement();
        }
        ProgramState prg = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(), statement);
        IRepository repo = new Repository(prg,"ok.txt");
        Controller c = new Controller(repo);
        c.allSteps();
    }
}
