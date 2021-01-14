package ADT;

import Model.Exception.myException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class myDictionary<K,V> implements ImyDictionary<K,V> {
    private HashMap<K,V> dictionary;
    private ConcurrentHashMap<K, V> dict;
    private Semaphore semaphore;

    public myDictionary(){
        this.dictionary = new HashMap<K,V>();
        this.dict = new ConcurrentHashMap<K,V>();
        this.semaphore = new Semaphore(1);
    }

    @Override
    public int size() {
        return this.dictionary.size();
    }

    @Override
    public V put(K key, V value) {
        this.dict.put(key, value);
        return this.dictionary.put(key,value);
    }

    @Override
    public V get(K key) throws myException {
        return this.dict.get(key);
    }

    @Override
    public V remove(K key) {
        this.dict.remove(key);
        return this.dictionary.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return this.dictionary.isEmpty();
    }

    @Override
    public Set<K> keys() {
        return this.dict.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.dict.values();
    }

    @Override
    public boolean containsKey(K key){ return this.dict.containsKey(key); }

    @Override
    public ImyDictionary<K, V> cloneDictionary() {
        try {
            this.semaphore.acquire();
            ImyDictionary<K,V> clone  = new myDictionary<>();
            HashMap<K, V> nc = new HashMap<>();
            for(K key:this.dict.keySet())
                nc.put(key,this.dictionary.get(key));
            clone.setDictionary(nc);
            this.semaphore.release();
            return clone;
        } catch (InterruptedException e) {
            throw new myException(e.getMessage());
        }
    }

    @Override
    public void setDictionary(HashMap<K, V> newDictionary) {
        try{
            this.semaphore.acquire();
            this.dictionary = newDictionary;
            this.dict = new ConcurrentHashMap<>();
            for(K key: newDictionary.keySet()){
                this.dict.put(key, this.dictionary.get(key));
            }
            this.semaphore.release();
        }catch (InterruptedException e) {
            throw new myException(e.getMessage());
        }
    }


    @Override
    public String toString(){
        return this.dict.toString();
    }
}
