package DataAnalysis;

import java.util.*;


/*
  ProjectName: DSAAProject
  Author: Archibald Chain
  CreateDate: 2018/12/13 19:24
  Version: 1.0
  <p>Copyright: Copyright (c) 2018</p>
 */

/**
 *
 * @param <K>
 * @param <V>
 */
public class MyTreeMap<K, V extends ComparableNode<V>>
        implements Map<K, V>{

    private HashMap<K, V> elementData;
    private BinarySearchTree<V> sortedElement;


    public MyTreeMap() {
        this.elementData = new HashMap<>();
        this.sortedElement = new BinarySearchTree<>();
    }

    MyTreeMap(HashMap<K, V> elementData) {
        this.elementData = elementData;
        this.sortedElement = new BinarySearchTree<>();
        for (V v: this.elementData.values()) {
            sortedElement.add(v);
        }
    }

    public ArrayList<V> getArrayList(){
        return sortedElement.getElement();
    }

    public Iterator<V> iterator(){
        return sortedElement.getElement().iterator();
    }

    <v> v[] toArray(v[] v){
        return sortedElement.getElement().toArray(v);
    }

    @Override
    public int size() {
        return elementData.size();
    }

    @Override
    public boolean isEmpty() {
        return elementData.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return elementData.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return elementData.containsValue(value);
    }

    /**
     * cause the problem cannot be solved, we using get as remove
     * @param key key
     * @return the value
     */
    @Override
    public V get(Object key) {
        return elementData.get(key);
    }

    @Override
    public V put(K key, V value) {
        elementData.put(key, value);
        sortedElement.add(value);
        return value;
    }

    @Override
    public V remove(Object key) {
        V temp = elementData.remove(key);
        sortedElement.delete(temp);
        return temp;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        elementData.putAll(m);
    }

    @Override
    public void clear() {
        sortedElement.clear();
        elementData.clear();
    }

    @Override
    public Set<K> keySet() {
        return elementData.keySet();
    }

    @Override
    public Collection<V> values() {
        return elementData.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return elementData.entrySet();
    }
}

class BinarySearchTree<T extends ComparableNode<T>> {

    private Node root;
    private Node parent;
    private ArrayList<T> element;

    class Node{
        private Node(T key) {
            this.key = key;
            if (root == null)
                root = this;
        }

        T key;
        Node leftChild;
        Node rightChild;
        Node parent;

        private boolean hasChild(){
            return !(leftChild == null && rightChild == null);
        }

        private boolean hasLeftChild(){
            return !(leftChild == null);
        }

        private boolean hasRightChild(){
            return !(rightChild == null);
        }

        private boolean hasOneChild(){
            return this.hasLeftChild() ^ this.hasRightChild();
        }

        private Node getOnlyOneChild(){
            if (this.hasOneChild()){
                if (this.hasLeftChild())
                    return leftChild;
                if (this.hasRightChild())
                    return rightChild;
            }
            throw new NullPointerException("Not Has One Child");

        }
    }

    void clear(){
        root = null;
    }

    void add(T key){
        if (root == null){
            root = new Node(key);
            return;
        }
        Node T = root;
        Node temp = new Node(key);
        do {
            if (!key.compareTo(T.key)){
                if (T.hasLeftChild())
                    T = T.leftChild;
                else{
                    T.leftChild = temp;
                    temp.parent = T;
                    break;
                }
            } else {
                if (T.hasRightChild())
                    T = T.rightChild;
                else{
                    T.rightChild = temp;
                    temp.parent = T;
                    break;
                }
            }
        }while(true);
    }

    private Node find(T key)throws NoSuchElementException{
        Node t = root;
        while(t != null){
            if (key.equals(t.key)){
                return t;
            }else if (t.key.compareTo(key)){
                t = t.leftChild;
            } else{
                t = t.rightChild;
            }
        }
        throw new NoSuchElementException("Not Found");
    }

    private Node findParent(Node node){
        T key = node.key;
        Node t = root;

        while(t != null){
            if (t.leftChild == node){
                return t;
            }
            if (t.rightChild == node){
                return t;
            }
            if (!key.compareTo(t.key)){
                t = t.leftChild;
            } else{
                t = t.rightChild;
            }
        }
        return null;
    }

    void delete(T key){

        Node node = find(key);
        parent = findParent(node);
        deleteNode(node);

    }

    private void deleteNode(Node node){
        Node smallest;
        if (!node.hasChild()){
            if (parent == null){
                root = null;
                return;
            }
            if (parent.leftChild == node){
                parent.leftChild = null;
            }
            if (parent.rightChild == node){
                parent.rightChild = null;
            }
        } else if (node.hasOneChild()){
            if (parent == null){
                root = node.getOnlyOneChild();
                return;
            }
            if (parent.leftChild == node){
                parent.leftChild = node.getOnlyOneChild();
            }
            if (parent.rightChild == node){
                parent.rightChild = node.getOnlyOneChild();
            }
        } else {
            parent = node;
            smallest = getSmallest(node.rightChild);
            T temp = smallest.key;

            deleteNode(smallest);
            node.key = temp;
        }
    }

    private Node getSmallest(Node node) {
        Node temp = node;

        while (temp.hasLeftChild()){
            parent = temp;
            temp = temp.leftChild;
        }

        return temp;
    }

    private void inOrder(Node node){
        if (node == null)
            return;
        inOrder(node.leftChild);
        element.add(node.key);
        inOrder(node.rightChild);
    }

    ArrayList<T> getElement() {
        element = new ArrayList<>();
        inOrder(root);
        return element;
    }
}
