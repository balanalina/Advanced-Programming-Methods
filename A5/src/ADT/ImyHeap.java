package ADT;
import Model.Value.*;

import java.util.HashMap;

public interface ImyHeap<Value> extends ImyDictionary<Integer, Value> {
    Integer generateAddress();
    void setContent(HashMap<Integer, Value> newHeap);
    HashMap<Integer, Value> getContent();
    public String toString();
}
