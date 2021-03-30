package eg.edu.alexu.csd.filestructure.redblacktree;

import javax.management.RuntimeErrorException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RedBlackTree<T extends Comparable<T>, V> implements IRedBlackTree<T,V> {

    private INode<T,V> root;
    private int size=0;

    @Override
    public INode<T, V> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size=0;
        root=null;
    }

    @Override
    public V search(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        try {
            INode<T,V> node = root;
            int c = node.getKey().compareTo(key);

            while (c!=0) {
                if (c>0){
                    node=node.getLeftChild();
                    c = node.getKey().compareTo(key);

                }else if(c<0) {
                    node = node.getRightChild();
                    c = node.getKey().compareTo(key);
                }
            }
            return  node.getValue();
        }catch (NullPointerException e){
            return null;
        }
    }
    private INode<T,V> searchNode(T key){
        try {
            INode<T,V> node = root;
            int c = node.getKey().compareTo(key);

            while (c!=0) {
                if (c>0){
                    node=node.getLeftChild();
                    c = node.getKey().compareTo(key);

                }else if(c<0) {
                    node = node.getRightChild();
                    c = node.getKey().compareTo(key);
                }
            }
            return  node;
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public boolean contains(T key) {
        if(key==null)throw new RuntimeErrorException(new Error());
        try {
            INode<T,V> node = root;
            int c = node.getKey().compareTo(key);

            while (c!=0) {
                if (c>0){
                    node=node.getLeftChild();
                    c = node.getKey().compareTo(key);

                }else if(c<0) {
                    node = node.getRightChild();
                    c = node.getKey().compareTo(key);
                }
            }
            return true;
        }catch (NullPointerException e){
            return false;
        }
    }
    private void adjustDeletion(INode<T,V> x) {
        INode<T,V> s;
        while (x != root && x.getColor() == INode.BLACK) {
            if (x == x.getParent().getLeftChild()) {
                s = x.getParent().getRightChild();
                if (s.getColor() == INode.RED) {
                    s.setColor(INode.BLACK);
                    x.getParent().setColor(INode.RED);
                    rotateLeft(x.getParent());
                    s = x.getParent().getRightChild();
                }

                if (s.getLeftChild().getColor() == INode.BLACK && s.getRightChild().getColor() == INode.BLACK) {
                    s.setColor(INode.RED);
                    x = x.getParent();
                } else {
                    if (s.getRightChild().getColor() == INode.BLACK) {
                        s.getLeftChild().setColor(INode.BLACK);
                        s.setColor(INode.RED);
                        rotateRight(s);
                        s = x.getParent().getRightChild();
                    }

                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(INode.BLACK);
                    s.getRightChild().setColor(INode.BLACK);
                    rotateLeft(x.getParent());
                    x = root;
                }
            } else {
                s = x.getParent().getLeftChild();
                if (s.getColor()== INode.RED) {
                    s.setColor(INode.BLACK);
                    x.getParent().setColor(INode.RED);
                    rotateRight(x.getParent());
                    s = x.getParent().getLeftChild();
                }

                if (s.getRightChild().getColor()== INode.BLACK) {
                    s.setColor(INode.RED);
                    x =x.getParent();
                } else {
                    if (s.getLeftChild().getColor() == INode.BLACK) {
                        s.getRightChild().setColor(INode.BLACK);
                        s.setColor(INode.RED);
                        rotateLeft(s);
                        s = x.getParent().getLeftChild();
                    }

                    s.setColor(x.getParent().getColor());
                    x.getParent().setColor(INode.BLACK);
                   s.getLeftChild().setColor(INode.BLACK);
                    rotateRight(x.getParent());
                    x = root;
                }
            }
        }
        x.setColor(INode.BLACK);
    }

    @Override
    public void insert(T key, V value) {
        if (key == null || value == null) {
            throw new RuntimeErrorException(new Error());
        }
        if(!this.isEmpty()){
        if(contains(key)){
            searchNode(key).setValue(value);
            return;
        }}
        this.size++;
            INode<T,V> newNode = new Node(key,value);
            if(this.root == null || this.root.isNull() ){
                newNode.setColor(INode.BLACK);
                this.root=newNode;
                return;
            }
            INode<T,V> node = this.root;

            int c = key.compareTo(node.getKey());
            while (true) {
                if (c > 0) {
                    if(!node.getRightChild().isNull())
                        node=node.getRightChild();
                    else {
                        node.setRightChild(newNode);
                        break;
                    }

                }else if(c<0) {
                    if(!node.getLeftChild().isNull())
                        node=node.getLeftChild();
                    else {
                        node.setLeftChild(newNode);
                        break;
                    }
                }else{
                    if(node.getLeftChild().isNull()) {
                        node.setLeftChild(newNode);
                        break;
                    }
                    else if(node.getRightChild().isNull()) {
                        node.setRightChild(newNode);
                        break;
                    }
                    else {
                        node = node.getLeftChild();
                    }

                }
                c = key.compareTo(node.getKey());
            }
            adjust(newNode);

    }

    @Override
    public boolean delete(T key) {
        if (key == null) {
            throw new RuntimeErrorException(new Error());
        }
        return deleteNode(this.root,key);

    }
    private boolean deleteNode(INode<T,V> node,T key){
        if (node.isNull())return false;

        int c = node.getKey().compareTo(key);
        while (c!=0){
            try {
                if(c>0){
                    node=node.getLeftChild();
                    c=node.getKey().compareTo(key);
                }
                if(c<0){
                    node=node.getRightChild();
                    c=node.getKey().compareTo(key);

                }

            }catch (NullPointerException e ){
                return false;
            }
        }
        size--;

        if(node.getRightChild().isNull() && node.getLeftChild().isNull()) {
            if(node==root){root=new Node<T,V>();return true;}
            adjustDeletion(node);
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(new Node<T,V>());
            if(node.getParent().getLeftChild()==node)node.getParent().setLeftChild(new Node<T,V>());

        }
        else if( node.getRightChild().isNull()){
            if(node.getColor()==INode.BLACK)node.getLeftChild().setColor(INode.BLACK);
            if(node==root){
                root=node.getLeftChild();
                root.setParent(null);
                return true;
            }
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(node.getLeftChild());
            else
                node.getParent().setLeftChild(node.getLeftChild());

        }
        else if(node.getLeftChild().isNull()) {
            if(node.getColor()==INode.BLACK)node.getRightChild().setColor(INode.BLACK);
            if(node==root){
                root=node.getRightChild();
                root.setParent(null);
                return true;
            }
            if(node.getParent().getRightChild()==node)node.getParent().setRightChild(node.getRightChild());
            else
                node.getParent().setLeftChild(node.getRightChild());

        }
        else {
            INode<T, V> successor = findSuccessor(node);
            node.setKey(successor.getKey());
            node.setValue(successor.getValue());
            deleteNode(successor,successor.getKey());

        }
        return true;
    }

    private INode<T,V> getUncle(INode<T,V> node){
        try {
            INode<T, V> grandParent = node.getParent().getParent();
            if (node.getParent() == grandParent.getRightChild()) return grandParent.getLeftChild();
            else return grandParent.getRightChild();
        }catch (NullPointerException e){
            return null;
        }
    }
    private void adjust(INode<T,V> node){
        //If parent is black then return
        if(node==root||node.getParent().getColor()==INode.BLACK)return;
        //else if the parent is RED --> Do some work
        //if Node's uncle is Black or Null
        if(getUncle(node).isNull()||getUncle(node).getColor()==INode.BLACK ){
            //RR , LL , RL or LR
            if(node.getParent()==node.getParent().getParent().getRightChild()){
                //R?
                if(node==node.getParent().getRightChild()){
                    //RR
                    node.getParent().getParent().setColor(INode.RED);
                    node.getParent().setColor(INode.BLACK);
                    rotateLeft(node.getParent().getParent());


                }
                else {
                    //RL

                    rotateRight(node.getParent());
                    adjust(node.getRightChild());
                }
            }else {
                //L?
                if(node==node.getParent().getRightChild()){
                    //LR
                    rotateLeft(node.getParent());
                    adjust(node.getLeftChild());
                }else {
                    //LL
                    node.getParent().getParent().setColor(INode.RED);
                    node.getParent().setColor(INode.BLACK);
                    rotateRight(node.getParent().getParent());
                }
            }

        }else {
            node.getParent().setColor(INode.BLACK);
            getUncle(node).setColor(INode.BLACK);
            if(node.getParent().getParent()!=root)node.getParent().getParent().setColor(INode.RED);
            adjust(node.getParent().getParent());
        }
    }
    private void rotateLeft(INode<T,V> node){

        INode y = node.getRightChild();
        node.setRightChild( y.getLeftChild());
        if(!y.getLeftChild().isNull()){
            y.getLeftChild().setParent( node);
        }
        y.setParent(node.getParent());
        if(node.getParent()==null)
            this.root = y;
        else if(node == node.getParent().getLeftChild()){
            node.getParent().setLeftChild(y);
        }
        else{
            node.getParent().setRightChild(y);
        }
        y.setLeftChild(node);
        node.setParent(y);
    }
    private void rotateRight(INode<T,V> x){
        INode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if(!y.getRightChild().isNull()){
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if(x.getParent() == null ){
            this.root = y;
        }
        else if(x == x.getParent().getRightChild()){
            x.getParent().setRightChild(y);
        }
        else{
            x.getParent().setLeftChild(y);
        }
        y.setRightChild(x);
        x.setParent(y);

    }
    private INode<T,V> findSuccessor(INode<T,V> node){
        node=node.getLeftChild();
        while(!node.getRightChild().isNull())node=node.getRightChild();
        return node;
    }
    private void adjustDeletions(INode<T,V> node){
        if(node==root|| node.getColor()==INode.RED)return;
        //CASE 1
        //Sibiling y is black
        System.out.println("Adjust "+node.getValue());
        if(getSibiling(node).isNull()|| getSibiling(node).getColor()==INode.BLACK){
            //Sibiling is not null
       //     if(getSibiling(node)!=null){
                INode<T,V> sib = getSibiling(node);



                if((!sib.getRightChild().isNull() &&sib.getRightChild().getColor()==INode.RED )||(!sib.getLeftChild().isNull()&&sib.getLeftChild().getColor() == INode.RED)){
                    // and has a red Child
                    sib.setColor(sib.getParent().getColor());
                    node.getParent().setColor(INode.BLACK);
                    node.setColor(INode.BLACK);
                    if(!sib.getRightChild().isNull() && sib.getRightChild().getColor()==INode.RED)
                        sib.getRightChild().setColor(INode.BLACK);
                    else
                        sib.getLeftChild().setColor(INode.BLACK);

                    if(node.getParent().getRightChild()==node)
                        rotateRight(node.getParent());
                    else
                        rotateLeft(node.getParent());
                }else{
                    //Sibiling has no RED Child
                    sib.setColor(INode.RED);
                    if(node.getParent().getColor()==INode.RED)node.getParent().setColor(INode.BLACK);
                    else if(node.getParent()!=getRoot()) adjustDeletion(node.getParent());

                }
           // }
        }else{
            //Sibiling Y is RED
            getSibiling(node).setColor(INode.BLACK);
            node.getParent().setColor(INode.RED);
            if(node.getParent().getRightChild()==node)
                rotateRight(node.getParent());
            else
                rotateLeft(node.getParent());
            adjustDeletion(node);
        }

    }
    private INode<T,V> getSibiling(INode<T,V> node){
      if(node==root)return null;
      try {
          if(node.getParent().getLeftChild()==node)
              return node.getParent().getRightChild();
          else return node.getParent().getLeftChild();
      }catch (NullPointerException e){return null;}

    }
    private Collection<V> collectionValue  = new ArrayList<>() ;
    private Collection<T> collectionKey = new ArrayList<>();


    public Collection<T> getCollectionKey() {
        return collectionKey;
    }


    void inorder(INode<T,V> node)
    {
        if (node.isNull())
            return  ;

        //  left subtree
        inorder( node.getLeftChild());
        collectionKey.add(node.getKey());
        collectionValue.add(node.getValue());
        // right subtree
        inorder(node.getRightChild());
    }

    public Collection<V> getCollectionValue() {
        return collectionValue;
    }


    public int getSize() {
        return size;
    }
    INode<T,V> getLast(INode<T,V>   node){

        while(! node.getRightChild().isNull()){
            node = node.getRightChild() ;
        }
        return node ;
    }
    INode<T,V> getFirst(INode<T,V>   node){

        while(!node.getLeftChild().isNull()){
            node = node.getLeftChild() ;
        }
        return node ;
    }
}
