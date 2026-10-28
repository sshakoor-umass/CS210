/*
 * Copyright 2026 Marc Liberatore.
 */
package hashmaps;

import java.util.HashSet;
import java.util.Set;


import hashtables.ChainingHashTable;

/**
 * An implementation of a SimpleMap, built using the ChainingHashTable and 
 * SimpleMapEntry classes. This class should behave similarly to the built-in
 * java.util.HashMap, though it is much simpler!
 */
public class SimpleHashMap<K, V> implements SimpleMap<K, V> {
    // Use this table to implement the map
    ChainingHashTable<SimpleMapEntry<K, V>> table;

    public SimpleHashMap() {
        table = new ChainingHashTable<>();
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void put(K k, V v) {
        table.add(new SimpleMapEntry<>(k, v));
    }

    @Override
    public V get(K k) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);
        if (table.contains(entry)) {
            return table.get(entry).getValue();
        }
        return null;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);
        if (table.contains(entry)) {
            return table.get(entry).getValue();
        }
        return defaultValue;
    }

    @Override
    public V remove(K k) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);
        if (table.contains(entry)) {
            V value = table.get(entry).getValue();
            table.remove(entry);
            return value;
        }
        return null;
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<>();
        for (SimpleMapEntry<K, V> entry : table) {
            keys.add(entry.getKey());
        }
        return keys;
    }
}
