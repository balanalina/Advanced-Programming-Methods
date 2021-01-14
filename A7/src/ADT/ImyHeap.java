package ADT;
import Model.Exception.myException;
import Model.Value.*;

import java.util.HashMap;
import java.util.Map;

public interface ImyHeap<Value> extends ImyDictionary<Integer, Value> {
    Integer generateAddress();
    Map<Integer, Value> getContent();
    public String toString();
    public void setContent(HashMap<Integer, Value> newHeap) throws myException;
}
