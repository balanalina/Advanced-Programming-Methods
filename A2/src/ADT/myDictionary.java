package ADT;

import Model.Exception.myException;

import java.util.*;

public class myDictionary<K,V> implements ImyDictionary<K,V> {
    private HashMap<K,V> dictionary;
    public myDictionary(){
        this.dictionary = new HashMap<K,V>();
    }

    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public V put(K key, V value) { return this.dictionary.put(key,value); }

    @Override
    public V get(K key) throws myException {
        return this.dictionary.get(key);
    }

    @Override
    public V remove(K key) {
        return this.dictionary.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }

    @Override
    public Set<K> keys() {
        return this.dictionary.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.dictionary.values();
    }

    @Override
    public boolean containsKey(K key){ return this.dictionary.containsKey(key); }

    @Override
    public String toString(){
        return super.toString();
    }
}
