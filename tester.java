package cs445.a5;
import java.util.Iterator;

public class tester{

  public static void main(String [] args) {
    TernaryTree<Integer> subtree1 = new TernaryTree<Integer>();
    TernaryTree<Integer> subtree2 = new TernaryTree<Integer>(2);
    TernaryTree<Integer> subtree3 = new TernaryTree<Integer>(3);

    TernaryTree<Integer> subtree4 = new TernaryTree<Integer>();
    TernaryTree<Integer> subtree5 = new TernaryTree<Integer>(5);
    TernaryTree<Integer> subtree6 = new TernaryTree<Integer>(6);

    TernaryTree<Integer> subtree7 = new TernaryTree<Integer>(7);
    TernaryTree<Integer> subtree8 = new TernaryTree<Integer>();
    TernaryTree<Integer> subtree9 = new TernaryTree<Integer>();




    TernaryTree<Integer> sub1 = new TernaryTree<Integer>(100,subtree1, subtree2, subtree3);
    TernaryTree<Integer> sub2 = new TernaryTree<Integer>(200,subtree4, subtree5, subtree6);
    TernaryTree<Integer> sub3 = new TernaryTree<Integer>(300,subtree7, subtree8, subtree9);

    TernaryTree<Integer> tree = new TernaryTree<Integer>(400, sub1, sub2, sub3);

    Iterator testPreorder = tree.getPreorderIterator();

    System.out.println("\n" + "--------------------------" + "\n");

    System.out.println("PreOrder" + "\n");
    while(testPreorder.hasNext()){
      System.out.println(testPreorder.next());
    }

    System.out.println("\n" + "--------------------------" + "\n");

    Iterator testLevel = tree.getLevelOrderIterator();

    System.out.println("LevelOrder" + "\n" );
    while(testLevel.hasNext()){
      System.out.println(testLevel.next());
    }

    System.out.println("\n" + "--------------------------" + "\n");

    Iterator testPost = tree.getPostorderIterator();

    System.out.println("PostOrder" + "\n" );
    while(testPost.hasNext()){
      System.out.println(testPost.next());
    }

    System.out.println("\n" + tree.getNumberOfNodes() + "\n" + "\n" + tree.contains(1) + "\n" + tree.isBalanced());

    System.out.println("\n" + "------ end of tests ------" + "\n");
  }

}
