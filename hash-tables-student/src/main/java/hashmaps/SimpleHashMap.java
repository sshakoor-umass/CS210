
/*
 * Copyright 2026 Marc Liberatore.
 */

package hashmaps;

import java.util.Set;
import java.util.HashSet;

import hashtables.ChainingHashTable;

public class SimpleHashMap<K, V> implements SimpleMap<K, V> {

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
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, v);

        if (table.contains(entry)) {
            table.remove(entry);
        }

        table.add(entry);
    }

    @Override
    public V get(K k) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);

        if (table.contains(entry)) {
            return table.get(entry).v;
        }

        return null;
    }

    @Override
    public V getOrDefault(K k, V defaultValue) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);

        if (table.contains(entry)) {
            return table.get(entry).v;
        }

        return defaultValue;
    }

    @Override
    public V remove(K k) {
        SimpleMapEntry<K, V> entry = new SimpleMapEntry<>(k, null);

        if (table.contains(entry)) {
            V value = table.get(entry).v;
            table.remove(entry);
            return value;
        }

        return null;
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<>();

        for (SimpleMapEntry<K, V> entry : table) {
            keys.add(entry.k);
        }

        return keys;
    }
}
