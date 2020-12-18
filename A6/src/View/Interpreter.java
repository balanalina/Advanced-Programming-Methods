package View;

import ADT.myDictionary;
import ADT.myHeap;
import ADT.myList;
import ADT.myStack;
import Controller.Controller;
import Model.Expression.ArithmeticExpression;
import Model.Expression.Heap.ReadHeap;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Statement.File.CloseRFile;
import Model.Statement.File.OpenRFile;
import Model.Statement.File.ReadFile;
import Model.Statement.Heap.NewReference;
import Model.Statement.Heap.WriteHeap;
import Model.Type.*;
import Model.Value.*;
import Repository.IRepository;
import Repository.Repository;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        //int v; v=2;Print(v);
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()), new CompoundStatement(new AssignmentStatement(
                new ValueExpression(new IntValue(2)),"v"),new PrintStatement(new VariableExpression("v"))));
        ProgramState prg1 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(),new myHeap<Value>(), ex1);
        IRepository repo1 = new Repository(prg1,"ex1.txt");
        Controller c1 = new Controller(repo1);

        //int a;int b; a=2+3*5;b=a+1;Print(b);
        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignmentStatement(new ArithmeticExpression(new ValueExpression(new IntValue(2)),
                                new ArithmeticExpression(new ValueExpression(new IntValue(3)),new ValueExpression(new IntValue(5)),"MULTIPLICATION"),
                                "PLUS"),"a"),new CompoundStatement(new AssignmentStatement(new ArithmeticExpression(new VariableExpression("a"),
                                new ValueExpression(new IntValue(1)),"PLUS"),"b"),new PrintStatement(new VariableExpression("b"))))));
        ProgramState prg2 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex2);
        IRepository repo2 = new Repository(prg2,"ex2.txt");
        Controller c2 = new Controller(repo2);

        //bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v);
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignmentStatement(new ValueExpression(new BoolValue(true)),"a"),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignmentStatement(new ValueExpression(new IntValue(2)),"v"),
                                        new AssignmentStatement(new ValueExpression(new IntValue(3)),"v")), new PrintStatement(new VariableExpression("v"))))));
        ProgramState prg3 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex3);
        IRepository repo3 = new Repository(prg3,"ex3.txt");
        Controller c3 = new Controller(repo3);

        //int v; v=false; Print(v);
        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement(new ValueExpression(new BoolValue()),"v"),
                        new PrintStatement(new VariableExpression("v"))));
        ProgramState prg4 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex4);
        IRepository repo4 = new Repository(prg4,"ex4.txt");
        Controller c4 = new Controller(repo4);


        //string s; s=ana; Print(s);
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("s", new StringType()),
                new CompoundStatement( new AssignmentStatement(new ValueExpression(new StringValue("ana")),"s"),
                new PrintStatement(new VariableExpression("s"))));
        ProgramState prg5 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex5);
        IRepository repo5 = new Repository(prg5, "ex5.txt");
        Controller c5 = new Controller(repo5);

        /* string varf; varf="test.in"; openRFile(varf); int varc; readFile(varf,varc);
        print(varc); readFile(varf,varc); print(varc); closeRFile(varf); */
        IStatement ex6 =new CompoundStatement( new VariableDeclarationStatement("varf",new StringType()),
                new CompoundStatement(new AssignmentStatement(new ValueExpression(new StringValue("test.in")), "varf"),
                        new CompoundStatement(new OpenRFile(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc",new IntType()),
                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"),"varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFile(new VariableExpression("varf"),"varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFile(new VariableExpression("varf"))))))))));
        ProgramState prg6 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex6);
        IRepository repo6 = new Repository(prg6, "ex6.txt");
        Controller c6 = new Controller(repo6);

        //int a; int b; Print(a<b);
        IStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new PrintStatement(new RelationalExpression(new VariableExpression("a"),
                                new VariableExpression("b"),"SMALLER"))));
        ProgramState prg7 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex7);
        IRepository repo7 = new Repository(prg7, "ex7.txt");
        Controller c7 = new Controller(repo7);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); print(rH(v));wH(v,30);print(rH(rH(a))+5)
        IStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewReference("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewReference("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeap(new ReadHeap(new VariableExpression("a"))),
                                                        new ValueExpression(new IntValue(5)),"PLUS")))))));
        ProgramState prg8 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex8);
        IRepository repo8 = new Repository(prg8, "ex8.txt");
        Controller c8 = new Controller(repo8);

        //Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5)
        IStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(new NewReference("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new PrintStatement(new ReadHeap(new VariableExpression("v"))),
                                new CompoundStatement(new WriteHeap("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression(new ReadHeap(new VariableExpression("v")),
                                                new ValueExpression(new IntValue(5)),"PLUS"))))));
        ProgramState prg9 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex9);
        IRepository repo9 = new Repository(prg9, "ex9.txt");
        Controller c9 = new Controller(repo9);

        //int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignmentStatement(new ValueExpression(new IntValue(4)),"v"),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(new VariableExpression("v"),
                                new ValueExpression(new IntValue()),"GREATER"),new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                new AssignmentStatement(new ArithmeticExpression(new VariableExpression("v"),new ValueExpression(new IntValue(1)),"MINUS"),"v"))),
                                new PrintStatement(new VariableExpression("v")))));
        ProgramState prg10 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex10);
        IRepository repo10 = new Repository(prg10, "ex10.txt");
        Controller c10 = new Controller(repo10);

        //Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement ex11 = new CompoundStatement(new VariableDeclarationStatement("v",new ReferenceType(new IntType())),
                new CompoundStatement(new NewReference("v",new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a",new ReferenceType(new ReferenceType(new IntType()))),
                                new CompoundStatement(new NewReference("a",new VariableExpression("v")),
                                        new CompoundStatement(new NewReference("v",new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeap(new ReadHeap(new VariableExpression("a")))))))));
        ProgramState prg11 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex11);
        IRepository repo11 = new Repository(prg11, "ex11.txt");
        Controller c11 = new Controller(repo11);

        //int v; Ref int a; v=10;new(a,22);
        //   fork(wH(a,30);v=32;print(v);print(rH(a)));   print(v);print(rH(a))
        IStatement ex12 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new IntType())),
                        new CompoundStatement(new AssignmentStatement( new ValueExpression(new IntValue(10)), "v"),
                                new CompoundStatement(new NewReference("a",new ValueExpression(new IntValue(22))),
                                new CompoundStatement(
                                new ForkStatement(new CompoundStatement(new WriteHeap("a",new ValueExpression(new IntValue(30))),
                                        new CompoundStatement(new AssignmentStatement(new ValueExpression(new IntValue(32)), "v"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                                        new PrintStatement(new ReadHeap(new VariableExpression("a"))))))),new CompoundStatement(new PrintStatement(new VariableExpression("v")),
                                        new PrintStatement(new ReadHeap(new VariableExpression("a")))))))));
        ProgramState prg12 = new ProgramState(new myStack<IStatement>(), new myDictionary<String, Value>(), new myList<Value>(),
                new myDictionary<StringValue, BufferedReader>(), new myHeap<Value>(), ex12);
        IRepository repo12 = new Repository(prg12, "ex12.txt");
        Controller c12 = new Controller(repo12);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0","exit"));
        menu.addCommand(new RunExampleCommand("1",ex1.toString(),c1));
        menu.addCommand(new RunExampleCommand("2",ex2.toString(),c2));
        menu.addCommand(new RunExampleCommand("3",ex3.toString(),c3));
        menu.addCommand(new RunExampleCommand("4",ex4.toString(),c4));
        menu.addCommand(new RunExampleCommand("5",ex5.toString(),c5));
        menu.addCommand(new RunExampleCommand("6",ex6.toString(),c6));
        menu.addCommand(new RunExampleCommand("7",ex7.toString(),c7));
        menu.addCommand(new RunExampleCommand("8",ex8.toString(),c8));
        menu.addCommand(new RunExampleCommand("9",ex9.toString(),c9));
        menu.addCommand(new RunExampleCommand("10",ex10.toString(),c10));
        menu.addCommand(new RunExampleCommand("11",ex11.toString(),c11));
        menu.addCommand(new RunExampleCommand("12",ex12.toString(),c12));
        menu.show();
    }
}
