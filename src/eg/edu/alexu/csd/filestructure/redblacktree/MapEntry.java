package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.Map;

public class MapEntry<K extends Comparable,V> implements Map.Entry<K, V> {

    public MapEntry(K key, V value) {
        this.key = key;
        Value = value;
    }

    private   K key ;
    private   V Value ;

    @Override
    public K getKey() {
        return this.key ;
    }

    @Override
    public V getValue() {
        return this.Value;
    }

    @Override
    public V setValue(V value) {
        return null;
    }


}
