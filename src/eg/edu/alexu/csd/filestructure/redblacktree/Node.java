package eg.edu.alexu.csd.filestructure.redblacktree;

public class Node<T extends Comparable<T>,V> implements INode<T,V>{
    private INode parent;
    private INode rightChild;
    private INode leftChild;
    private T key=null;
    private V value=null;
    private boolean red=true;
    public Node(T key,V value){
        this.value=value;
        this.key=key;
        this.setLeftChild(new Node());
        this.setRightChild(new Node());
    }
    public Node (){
        red=false;
    }
    @Override

    public void setParent(INode parent) {
        this.parent=parent;
    }

    @Override
    public INode getParent() {
        return this.parent;
    }

    @Override
    public void setLeftChild(INode leftChild) {
        this.leftChild=leftChild;
        if(leftChild==null)return;

        leftChild.setParent(this);
    }

    @Override
    public INode getLeftChild() {

        return this.leftChild;
    }

    @Override
    public void setRightChild(INode rightChild) {

        this.rightChild=rightChild;
        if(rightChild==null)return;
        rightChild.setParent(this);
    }

    @Override
    public INode getRightChild() {
        return this.rightChild;
    }

    @Override
    public T getKey() {
        return this.key;
    }

    @Override
    public void setKey(T key) {
        this.key=key;
    }

    @Override
    public V getValue() {
        return this.value;
    }

    @Override
    public void setValue(V value) {
            this.value=value;
    }

    @Override
    public boolean getColor() {
        if(this==null)return INode.BLACK;
        return red;
    }

    @Override
    public void setColor(boolean color) {
            this.red=color;
    }

    @Override
    public boolean isNull() {
        return this.value == null;
    }
}
