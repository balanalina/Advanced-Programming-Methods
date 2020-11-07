package Model.Statement;

import Model.Exception.myException;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Type.IntType;
import Model.Type.StringType;
import Model.Value.*;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression file_name_expression;
    private String variable_name;

    public ReadFile(IExpression exp,String var_name){
        this.file_name_expression = exp;
        this.variable_name = var_name;
    }

    @Override
    public ProgramState execute(ProgramState state) throws myException {
        if(state.getSymbol_table().containsKey(this.variable_name)){
            if(state.getSymbol_table().get(this.variable_name).getType().equals(new IntType())){
                Value file_name = this.file_name_expression.eval(state.getSymbol_table());
                if(file_name.getType().equals(new StringType())){
                    if(state.getFile_table().containsKey((StringValue) file_name)){
                        BufferedReader bufferedReader = state.getFile_table().get((StringValue)file_name);
                        try{
                            String line = bufferedReader.readLine();
                            if(line != null)
                                state.getSymbol_table().put(this.variable_name, new IntValue(Integer.parseInt(line)));
                            else
                                state.getSymbol_table().put(this.variable_name, new IntValue());
                        }
                        catch (IOException e){
                            throw new myException("Could not open the file!");
                        }
                    }
                    else
                        throw new myException("File " + ((StringValue) file_name).getValue() + "was not open for reading!");
                }
                else
                    throw new myException("File name must be of StringType!");
            }
            else
                throw new myException("Variable " + this.variable_name + "must be of IntType!");
        }
        else
            throw new myException("Variable " + this.variable_name + " hasn't been declared!");
        return state;
    }

    @Override
    public String toString(){
        return "ReadFile("+ this.file_name_expression + ", " + this.variable_name + ");";
    }
}
