package Model.Statement;

import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.StringType;
import Model.Value.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFile implements IStatement {
    private IExpression file_name_expression;

    public OpenRFile(IExpression exp){
        this.file_name_expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        Value file_name = this.file_name_expression.eval(state.getSymbol_table());
        if(file_name.getType().equals(new StringType())){
            if(!state.getFile_table().containsKey((StringValue) file_name)){
                try{
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(((StringValue) file_name).getValue()));
                    state.getFile_table().put((StringValue)file_name,bufferedReader);
                }
                catch (IOException e){
                    throw new myException("Cannot open file " + this.file_name_expression + "!11");
                }
            }
            else
                throw new myException("The file is already open for reading!");
        }
        else
            throw new myException("Expression " + this.file_name_expression + " must be of StringType!");
        return state;
    }

    @Override
    public String toString(){ return "OpenRFile(" + this.file_name_expression + ");";}
}
