package Model.Statement.File;

import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Statement.IStatement;
import Model.Type.StringType;
import Model.Value.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private IExpression file_name_expression;

    public CloseRFile(IExpression exp){
        this.file_name_expression = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        Value file_name = this.file_name_expression.eval(state.getSymbol_table(), state.getHeap_table());
        if(file_name.getType().equals(new StringType())){
            if(state.getFile_table().containsKey((StringValue) file_name)){
                BufferedReader bufferedReader = state.getFile_table().get((StringValue)file_name);
                try{
                    bufferedReader.close();
                    state.getFile_table().remove((StringValue)file_name);
                } catch (IOException e){
                    throw new myException("Cannot close file " + file_name);
                }
            }
            else
                throw new myException("File " + this.file_name_expression + "hasn't been open!");
        }
        else
            throw new myException("File name must be of StringType!");
        return null;
    }

    @Override
    public String toString(){ return "CloseRFile(" + this.file_name_expression + ");"; }
}
