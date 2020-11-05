package ADT;

import Model.Exception.myException;

import java.util.Collection;
import java.util.Set;

public interface ImyDictionary<K,V> {
    //Returns the number of entries (distinct keys) in this dictionary.
    public int size();

    //Associates the specified value with the specified key in this map.
    public V put(K key, V value);

    //Returns the value to which the key is mapped in this dictionary, or null if this map contains no mapping for the key.
    public V get(K key) throws myException;

    //Removes the entry for the specified key only if it is currently mapped to the specified value.
    public V remove(K key);

    //Tests if this dictionary maps no keys to value.
    public boolean isEmpty();

    //Returns an enumeration of the keys in this dictionary.
    public Set<K> keys();

    //Returns an enumeration of the values in this dictionary.
    public Collection<V> values();

    //Returns true if this map contains a mapping for the specified key.
    public boolean containsKey(K key);

    public String toString();

}
