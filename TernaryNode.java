package cs445.a5;
import java.lang.Math;
import java.util.ArrayList;

class TernaryNode<E> {
    private E data;
    private TernaryNode <E> [] children;

    public TernaryNode() {
        this(null); // Call next constructor
    }

    public TernaryNode(E dataPortion) {
        this(dataPortion, null, null, null); // Call next constructor
    }


    @SuppressWarnings("unchecked")
    public TernaryNode(E dataPortion, TernaryNode<E> newLeftChild, TernaryNode<E> newRightChild, TernaryNode<E> newMiddleChild) {
        data = dataPortion;
        children = (TernaryNode <E>[]) new TernaryNode <?>[3];
        children [0] = newLeftChild;
        children [1] = newMiddleChild;
        children [2] = newRightChild;
    }

    /** Retrieves the data portion of this node.
     *  @return  The object in the data portion of the node. */
    public E getData() {
        return data;
    }

    /** Sets the data portion of this node.
     *  @param newData  The data object. */
    public void setData(E newData) {
        data = newData;
    }

    /** Retrieves the left child of this node.
     *  @return  The node’s left child. */
    public TernaryNode<E> getLeftChild() {
      return children[0];
    }

    /** Sets this node’s left child to a given node.
     *  @param newLeftChild  A node that will be the left child. */
    public void setLeftChild(TernaryNode<E> newLeftChild) {
      children [0] = newLeftChild;
    }

    /** Detects whether this node has a left child.
     *  @return  True if the node has a left child. */
    public boolean hasLeftChild() {
      return children [0] != null;
    }

    /** Retrieves the right child of this node.
     *  @return  The node’s right child. */
    public TernaryNode<E> getRightChild() {
        return children [2];
    }

    /** Sets this node’s right child to a given node.
     *  @param newRightChild  A node that will be the right child. */
    public void setRightChild(TernaryNode<E> newRightChild) {
        children [2] = newRightChild;
    }

    /** Detects whether this node has a right child.
     *  @return  True if the node has a right child. */
    public boolean hasRightChild() {
      return children [2] != null;
    }

    public TernaryNode<E> getMiddleChild(){
      return children [1];
    }

    public void setMiddleChild(TernaryNode<E> newMiddleChild){
      children [1] = newMiddleChild;
    }

    public boolean hasMiddleChild(){
      return children [1] != null;
    }

    /** Detects whether this node is a leaf.
     *  @return  True if the node is a leaf. */
    public boolean isLeaf() {
        return (children[0] == null) && (children[1] == null) && (children[2] == null);
    }

    /** Counts the nodes in the subtree rooted at this node.
     *  @return  The number of nodes in the subtree rooted at this node. */
    public int getNumberOfNodes() {
        int leftNumber = 0;
        int rightNumber = 0;
        int middleNumber = 0;

        if (children[0] != null) {
            leftNumber = children[0].getNumberOfNodes();
        }

        if (children[1] != null) {
            middleNumber = children[1].getNumberOfNodes();
        }

        if (children[2] != null) {
            rightNumber = children[2].getNumberOfNodes();
        }



        return 1 + leftNumber + rightNumber + middleNumber;
    }

    /** Computes the height of the subtree rooted at this node.
     *  @return  The height of the subtree rooted at this node. */
    public int getHeight() {
        return getHeight(this); // Call private getHeight
    }

    private int getHeight(TernaryNode<E> node) {
        int height = 0;

        if (node != null)
            height = 1 + Math.max(Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild())),
                Math.max(getHeight(node.getLeftChild()), getHeight(node.getMiddleChild())) ) ;

        return height;
    }

    /** Copies the subtree rooted at this node.
     *  @return  The root of a copy of the subtree rooted at this node. */
    public TernaryNode<E> copy() {
        TernaryNode<E> newRoot = new TernaryNode<>(data);

        if (children[0] != null) {
            newRoot.setLeftChild(children[0].copy());
        }

        if (children[1] != null) {
            newRoot.setMiddleChild(children[1].copy());
        }

        if (children[2] != null) {
            newRoot.setRightChild(children[2].copy());
        }



        return newRoot;
    }
}
