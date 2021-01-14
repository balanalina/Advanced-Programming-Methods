package GUI.Controller;

import ADT.ImyDictionary;
import ADT.ImyHeap;
import ADT.ImyStack;
import Controller.Controller;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Value.*;
import com.sun.jdi.IntegerValue;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InterpreterController {
    private Integer currentThreadID = -1;
    private Controller controller;
    private Stage stage;

    @FXML
    private TextField nrOfProgramStatesTxtField;

    @FXML
    private Label textLabelProgramState;

    @FXML
    private Button oneStepButton;

    @FXML
    private TableView<Map.Entry<Integer, String>> heapTable;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, Integer> addressHeapTable;

    @FXML
    private TableColumn<Map.Entry<Integer, String>, String> valueHeapTable;

    @FXML
    private ListView<String> outList;

    @FXML
    private ListView<?> fileTable;

    @FXML
    private ListView<Integer> threadIdList;

    @FXML
    private TableView<Map.Entry<String,String>> symbolTable;

    @FXML
    private TableColumn<Map.Entry<String,String>,String> variableSymbolColumn;

    @FXML
    private TableColumn<Map.Entry<String,String>,String> valueSymbolColumn;

    //DATA RELATED TO  EXECUTION STACK LIST
    @FXML
    private ListView<String> executionStackList;

    @FXML
    public void oneStepButtonClicked(MouseEvent mouseEvent) {
        try{
            if(currentThreadID == -1){
                currentThreadID = controller.getListOfThreadID().get(0);
            }
            if(!controller.getListOfThreadID().contains(currentThreadID))
                currentThreadID = controller.getListOfThreadID().get(0);

            controller.oneStepGUI();
            nrOfProgramStatesTxtField.setText(controller.getNumberOfStates().toString());
            populate();
            if(controller.getNumberOfStates() == 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setResizable(true);
                alert.setHeaderText("Enf of the program execution!");
                alert.setContentText("The window will close soon!");
                alert.showAndWait();
                stage.close();
            }
            if(!controller.getListOfThreadID().contains(currentThreadID))
                currentThreadID = controller.getListOfThreadID().get(0);
        }
        catch (myException | InterruptedException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setResizable(true);
            alert.setHeaderText("Execution error!");
            currentThreadID = -1;
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            stage.close();
        }
    }

    private void populate() {
        this.nrOfProgramStatesTxtField.setText(controller.getNumberOfStates().toString());
        populateThreadID();
        populateOutList();
        populateExecutionStack(controller.getProgramWithID(currentThreadID));
        populateHeapTable();
        populateSymbolTable(controller.getProgramWithID(currentThreadID));
    }

    private void populateSymbolTable(ProgramState program) {
        ImyDictionary<String,Value> symbol_table = program.getSymbol_table();
        Map<String,String> symbolTable= new HashMap<>();

        for(String key:symbol_table.keys())
            symbolTable.put(key,symbol_table.get(key).toString());

        Map<String,String> symbolMap = new HashMap<>();
        for(Map.Entry<String,String> entry: symbolTable.entrySet())
            symbolMap.put(entry.getKey(),entry.getValue());


        List<Map.Entry<String,String>> symbolList = new ArrayList<>(symbolMap.entrySet());
        this.symbolTable.setItems(FXCollections.observableList(symbolList));
        this.symbolTable.refresh();

    }

    private void populateOutList() {
        List<String> outStr = controller.getOutTable().getList().stream().map(Object::toString).collect(Collectors.toList());
        ObservableList<String> outList = FXCollections.observableArrayList(outStr);
        this.outList.setItems(outList);
    }

    private void populateHeapTable() {
        ImyHeap originalHeap = controller.getHeapTable();
        Map<Integer, String> heap = new HashMap<>();
        for(Object k: originalHeap.getContent().keySet())
            heap.put(Integer.parseInt(k.toString()),originalHeap.getContent().get(k).toString());

        Map<Integer,String> heapMap = new HashMap<>();
        for(Map.Entry<Integer,String> entry : heap.entrySet())
            heapMap.put(entry.getKey(),entry.getValue());

        List<Map.Entry<Integer,String>> heapTableList = new ArrayList<>(heapMap.entrySet());
        this.heapTable.setItems(FXCollections.observableList(heapTableList));
        this.heapTable.refresh();
    }

    private void populateThreadID() {
        ObservableList<Integer> list = FXCollections.observableArrayList();
        list.addAll(controller.getListOfThreadID());
        threadIdList.setItems(list);
    }

    @FXML
    public void selectedThreadID(MouseEvent mouseEvent) {
        this.currentThreadID = threadIdList.getSelectionModel().getSelectedItem();
        if(controller.getListOfThreadID().contains(currentThreadID))
            populateExecutionStack(controller.getProgramWithID(currentThreadID));
    }

    private void populateExecutionStack(ProgramState program) {
        ImyStack<IStatement> currentStack = program.getExecution_stack();
        List<String> list = new ArrayList<>();
        for(IStatement statement: currentStack.getStack())
            list.add(statement.toString());
        this.executionStackList.setItems(FXCollections.observableList(list));
    }

    public void setProgramController(Controller c){
        this.controller = c;
        this.nrOfProgramStatesTxtField.setText(c.getNumberOfStates().toString());
    }

    public void setPage(Stage stage){
        this.stage = stage;
    }

    @FXML
    public void initialize(){
        //nrOfProgramStatesTextField.setEditable(false);
        addressHeapTable.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        valueHeapTable.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue() + ""));

        variableSymbolColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        valueSymbolColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getValue() + ""));
    }


}
