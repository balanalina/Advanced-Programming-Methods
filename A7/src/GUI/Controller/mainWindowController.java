package GUI.Controller;

import ADT.myDictionary;
import ADT.myHeap;
import ADT.myList;
import ADT.myStack;
import Controller.Controller;
import Model.Exception.myException;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.Type;
import Repository.IRepository;
import Repository.Repository;
import View.Interpreter;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class mainWindowController {
    private List<IStatement> programs = null;
    private Integer index;
    private Stage startWindow;
    private Stage mainPage;

    @FXML
    private ListView<String> programList;

    @FXML
    private Button executeButton;

    @FXML
    private Label textLabel;

    @FXML
    private TextArea textArea;

    @FXML
    public void initialize(){
        textArea.setEditable(false);
        textLabel.setFont(new Font(15));
        textLabel.setText("ToyLanguage Interpreter \nPlease choose one of\n the available programs\n and press\n execute!");
        textLabel.setTextAlignment(TextAlignment.CENTER);
        textLabel.autosize();
        this.programs = Interpreter.getAll();
        this.populateList();
    }

    private void populateList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(programs.stream().map(Object::toString).collect(Collectors.toList()));
        programList.setItems(list);
    }

    public void setStartWindow(Stage startWindow) { this.startWindow = startWindow; }

    public void itemClicked(MouseEvent mouseEvent) {
        this.index = programList.getSelectionModel().getSelectedIndex();
        textArea.setFont(Font.font("Roboto Mono"));
        textArea.setText("          Selected Program:\n");
        textArea.appendText("\n\n");
        textArea.appendText(displyOnRows(programs.get(index).toString()));
    }

    private String displyOnRows(String toString) {
        return toString.replace(";",";\n");
    }

    public void executeClicked(MouseEvent mouseEvent) {
        System.out.println(index);
        if(index == null){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("No program selected!");
            errorAlert.setContentText("Select o program from the list!");
            errorAlert.showAndWait();
            return;
        }
        setUpProgramStateController(programs.get(index), index);
        textArea.setText("");
        index = null;
    }

    private void setUpProgramStateController(IStatement selectedProgram, Integer index) {
        try{
            selectedProgram.typeCheck(new myDictionary<String, Type>());
            ProgramState programState = new ProgramState(new myStack<>(),new myDictionary<>(), new myList<>(),selectedProgram);
            IRepository repo = new Repository(programState, "ex" + Integer.toString(index) + ".txt");
            Controller c = new Controller(repo);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1150, 800);
            InterpreterController interpreterController = fxmlLoader.getController();
            interpreterController.setProgramController(c);
            Stage stage = new Stage();
            scene.getStylesheets().add(getClass().getResource("mainWindow.css").toExternalForm());
            stage.setTitle("Interpreter");
            stage.setScene(scene);
            stage.initOwner(startWindow);
            stage.initModality(Modality.WINDOW_MODAL);
            interpreterController.setPage(stage);
            stage.show();
        }
        catch (myException | IOException exc){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setHeaderText("TypeCheck Error");
            System.err.println(exc.getMessage());
            error.setContentText(exc.getMessage());
            error.showAndWait();

        }
    }
}
