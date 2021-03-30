package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.security.Key;
import java.util.*;

public class TreeMap<T extends Comparable<T>,V>implements ITreeMap<T,V> {
    private RedBlackTree redBlackTree ;
    public TreeMap() {
        redBlackTree = new RedBlackTree() ;
    }

    @Override
    public Map.Entry<T, V> ceilingEntry(T key)
    {
        if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!redBlackTree.isEmpty()){
            if(redBlackTree.contains(key)){
                redBlackTree.inorder(redBlackTree.getRoot());
                Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
                Iterator<V> iteratorValue = redBlackTree.getCollectionValue().iterator();
                while(iteratorKey.hasNext()) {
                    T tokey   = iteratorKey.next();
                    V value   = iteratorValue.next() ;
                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
                        Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(tokey, value) ;
                        return mapEntry ;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public T ceilingKey(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!redBlackTree.isEmpty()){
            if(redBlackTree.contains(key)){
                redBlackTree.inorder(redBlackTree.getRoot());
                Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
                while(iteratorKey.hasNext()) {
                    T tokey   = iteratorKey.next();
                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
                        return tokey ;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void clear() {
        if(!redBlackTree.isEmpty())
            this.redBlackTree.clear();
    }

    @Override
    public boolean containsKey(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!redBlackTree.isEmpty()) {
            if(redBlackTree.contains(key)){
                return true ;
            }
        }
        return false ;
    }

    @Override
    public boolean containsValue(V value)
    {
        if (value == null) {
            throw new RuntimeErrorException(new Error());
        }
        if (!redBlackTree.isEmpty()) {
            redBlackTree.inorder(redBlackTree.getRoot());
            Collection<V> values = redBlackTree.getCollectionValue() ;
            return values.contains(value) ;
        }
        return false;
    }

    @Override
    public Set<Map.Entry<T, V>> entrySet() {
        Set<Map.Entry<T, V>> entrySet = new LinkedHashSet<>();
        if (!redBlackTree.isEmpty()) {
            redBlackTree.inorder( redBlackTree.getRoot());

            Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
            Iterator<V> iteratorValue = redBlackTree.getCollectionValue().iterator();

            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value   = iteratorValue.next() ;
                Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;

                entrySet.add( mapEntry );
            }

        }
        return entrySet;
    }

    @Override
    public Map.Entry<T, V> firstEntry() {

        if (!redBlackTree.isEmpty()) {
            INode<T,V> node  = redBlackTree.getFirst(redBlackTree.getRoot()) ;
            Map.Entry<T,V> mapEntry = new MapEntry<>( node.getKey(), node.getValue() ) ;
            return mapEntry ;
        }
        return null;
    }

    @Override
    public T firstKey()
    {
        if (!redBlackTree.isEmpty()) {
            INode<T,V> node  = redBlackTree.getFirst(redBlackTree.getRoot()) ;
            return node.getKey() ;
        }
        return null;

    }


    @Override
    public Map.Entry<T, V> floorEntry(T key) {
        if(key == null ){
            throw new RuntimeErrorException(new Error()) ;
        }
            if (!redBlackTree.isEmpty()) {
                if (redBlackTree.contains(key)) {
                    redBlackTree.inorder(redBlackTree.getRoot());
                    Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
                    Iterator<V> iteratorValue = redBlackTree.getCollectionValue().iterator();

                    while (iteratorKey.hasNext()) {
                        T tokey = iteratorKey.next();
                        V value = iteratorValue.next();
                        if (key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0) {
                            Map.Entry<T,V> mapEntry = new MapEntry<>(tokey, value) ;
                            return mapEntry ;
                        }
                    }
                }
            }
            return null;
    }


    @Override
    public T floorKey(T key) {
        if(key == null ){
        throw new RuntimeErrorException(new Error()) ;
    }
        if (!redBlackTree.isEmpty()){
            if(redBlackTree.contains(key)){
                redBlackTree.inorder(redBlackTree.getRoot());
                Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
                while(iteratorKey.hasNext()) {
                    T tokey   = iteratorKey.next();
                    if(key.compareTo(tokey) == 0 || key.compareTo(tokey) < 0 ){
                        return tokey ;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public V get(T key) {
        if (redBlackTree.contains(key)){
            return (V) redBlackTree.search(key);
        }
        return null;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey) {
        ArrayList<Map.Entry<T, V>> entryArrayList = new ArrayList<Map.Entry<T,V>>() ;
        if(redBlackTree.contains(toKey)){
            redBlackTree.inorder(redBlackTree.getRoot());
            Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
            Iterator<V> iteratorValue = redBlackTree.getCollectionValue().iterator();
            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value =  iteratorValue.next();
                if(key.compareTo(toKey) < 0){
                    Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;
                    entryArrayList.add( mapEntry );
                }
            }
        }
        return entryArrayList;
    }

    @Override
    public ArrayList<Map.Entry<T, V>> headMap(T toKey, boolean inclusive)
    {
        ArrayList<Map.Entry<T, V>> entryArrayList = new ArrayList<>();
        if(redBlackTree.contains(toKey)){
            redBlackTree.inorder(redBlackTree.getRoot());
            Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
            Iterator<T> iteratorValue = redBlackTree.getCollectionValue().iterator();
            while(iteratorKey.hasNext()) {
                T key   = iteratorKey.next();
                V value = (V) iteratorValue.next();
                if(key.compareTo(toKey) < 0 || (key.compareTo(toKey) == 0 && inclusive) ){
                    Map.Entry<T,V> mapEntry = new AbstractMap.SimpleEntry<T,V>(key, value) ;
                    entryArrayList.add( mapEntry );
                }
            }
        }
        return entryArrayList;
    }

    @Override
    public Set<T> keySet()
    {
        Set<T> entryArrayList = new LinkedHashSet<>();
        if (!redBlackTree.isEmpty()){
            if (!redBlackTree.isEmpty()){
                redBlackTree.inorder( redBlackTree.getRoot() ) ;
                Iterator<T> iteratorKey = redBlackTree.getCollectionKey().iterator();
                while(iteratorKey.hasNext()) {
                    T key   = iteratorKey.next();
                        entryArrayList.add( key );
                    }
                }
            }

        return entryArrayList ;
    }

    @Override
    public Map.Entry<T, V> lastEntry() {
        if (!redBlackTree.isEmpty()) {
            INode<T,V> node = redBlackTree.getLast(redBlackTree.getRoot()) ;
            Map.Entry<T,V> mapEntry = new MapEntry<>(node.getKey(), node.getValue()) ;
            return mapEntry ;
        }
        return null;
    }

    @Override
    public T lastKey() {
        if (!redBlackTree.isEmpty()) {
            INode<T,V> node =   redBlackTree.getLast(redBlackTree.getRoot());
            return  node.getKey();
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollFirstEntry() {
        try {

            if (!redBlackTree.isEmpty()) {
                INode<T, V> node = redBlackTree.getFirst(redBlackTree.getRoot());
                redBlackTree.delete(node.getKey());
                Map.Entry<T, V> mapEntry = new MapEntry<>(node.getKey(), node.getValue());
                return mapEntry;
            }
        } catch (NullPointerException e) {
            return null;
        }
        return null;
    }

    @Override
    public Map.Entry<T, V> pollLastEntry() {
        try {
            if (!redBlackTree.isEmpty()) {
                INode<T, V> node = redBlackTree.getLast(redBlackTree.getRoot());
                redBlackTree.delete(node.getKey());
                Map.Entry<T, V> mapEntry = new MapEntry<>(node.getKey(), node.getValue());
                return mapEntry;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }

    }
    @Override
    public void put(T key, V value) {
        if (key == null || value == null){
            throw new RuntimeErrorException(new Error()) ;
        }
        redBlackTree.insert(key,value);
    }

    @Override
    public void putAll(Map<T, V> map) {
        if (map == null) {
            throw new RuntimeErrorException(new Error());
        }

        for (Map.Entry<T, V> entry : map.entrySet()) {
            if (entry.getKey() == null || entry.getValue() == null) {
                throw new RuntimeErrorException(new Error());
            } else {
                redBlackTree.insert(entry.getKey(), entry.getValue());
            }
        }
    }

    @Override
    public boolean remove(T key) {
        if (!redBlackTree.isEmpty()){
            return  redBlackTree.delete(key) ;
        }
        return false;
    }

    @Override
    public int size() {
        return  redBlackTree.getSize() ;
    }

    @Override
    public Collection<V> values() {
        if (!redBlackTree.isEmpty()){
            INode root = redBlackTree.getRoot() ;
            redBlackTree.inorder((Node) root) ;
            return redBlackTree.getCollectionValue() ;
        }
        return null;
    }



}