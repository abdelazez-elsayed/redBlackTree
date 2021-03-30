package eg.edu.alexu.csd.filestructure.redblacktree;

import java.util.Map;

public class MainClass {
    public static  void main(String[] args) {
        RedBlackTree<Integer,Integer> tree = new RedBlackTree<>();
        RBTreePrinter printer = new RBTreePrinter();

        tree.insert(2,2);
        tree.insert(4,4);
        tree.insert(3,3);
        tree.insert(5,5);
        tree.insert(9,9);
        tree.insert(8,8);
        tree.insert(3,3);
        tree.insert(4,4);
        tree.insert(1,1);
        tree.insert(5,5);

       tree.delete(1);
        printer.print(tree.getRoot());

        tree.delete(5);
       printer.print(tree.getRoot());














    }
}
