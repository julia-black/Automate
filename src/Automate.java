import java.util.List;


public abstract class Automate {
    protected List<String> beginState;
    protected List<String> states;
    protected List<String> signs; //входные сигналы
    protected List<String> endStates; //завершающие сигналы


    protected boolean containsBeginSymbol(char begSymbol){
        if(signs.contains(begSymbol)){
            return true;
        }
        else
            return false;

    }
    protected abstract boolean execute(char input);

    protected abstract List<String> getCurrentState();


    protected abstract List<String> getBeginState();

    protected List<String> getEndState(){
        return endStates;
    }

    public Automate(List<String> states, List<String> signs, List<String> endStates) {
        this.states = states;
        this.signs = signs;
        this.endStates = endStates;
       // this.transaction = transaction;
    }

    public  abstract  void setCurrentState(List<String> currentState);

    public void setBeginState(List<String> beginState) {
        this.beginState = beginState;
    }
}
