package cs445.a5;
import cs445.StackAndQueuePackage.*;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class TernaryTree<E> implements TernaryTreeInterface<E>, TernaryTreeBonus<E> {
  private TernaryNode<E> root;

  public TernaryTree(){
    root = null;
  }

  private TernaryNode<E> getRoot(){
    return root;
  }

  public TernaryTree(E rootData){
    setTree(rootData);
  }

  public TernaryTree(E rootData, TernaryTree<E> leftTree,TernaryTree<E> middleTree,TernaryTree<E> rightTree){
    setTree(rootData,leftTree,middleTree,rightTree);
  }

  public void setTree(E rootData){
    root = new TernaryNode<>(rootData);
  }
  public void setTree(E rootData, TernaryTreeInterface<E> leftTree, TernaryTreeInterface<E> middleTree, TernaryTreeInterface<E> rightTree) {
      privateSetTree(rootData, (TernaryTree<E>)leftTree, (TernaryTree<E>)middleTree, (TernaryTree<E>)rightTree);
  }

  public void privateSetTree(E rootData, TernaryTree<E> leftTree,TernaryTree<E> middleTree,TernaryTree<E> rightTree){
    TernaryNode<E> root = new TernaryNode<>(rootData);

    if ((leftTree != null) && !leftTree.isEmpty()) {
        root.setLeftChild(leftTree.root);
    }

    if ((middleTree != null) && !middleTree.isEmpty()) {
      if (middleTree != leftTree) {
          root.setMiddleChild(middleTree.root);
      } else {
          root.setMiddleChild(middleTree.root.copy());
      }
    }

    if ((rightTree != null) && !rightTree.isEmpty()) {
        if (rightTree != middleTree) {
            root.setRightChild(rightTree.root);
        } else {
            root.setRightChild(rightTree.root.copy());
        }
    }

    this.root = root;

    if ((leftTree != null) && (leftTree != this)) {
        leftTree.clear();
    }

    if ((middleTree != null) && (middleTree != this)) {
        middleTree.clear();
    }

    if ((rightTree != null) && (rightTree != this)) {
        rightTree.clear();
    }
  }

  @Override
  public E getRootData(){
    if (isEmpty()) {
        throw new EmptyTreeException();
    } else {
        return root.getData();
    }
  }

  @Override
  public int getHeight(){
    if (isEmpty()) {
        throw new EmptyTreeException();
    } else {
        return root.getHeight();
    }
  }

  @Override
  public int getNumberOfNodes(){
    int numberOfNodes = 0;
    if (!isEmpty()) {
        numberOfNodes = root.getNumberOfNodes();
    }
    return numberOfNodes;
  }

  @Override
  public boolean isEmpty(){
      return root == null;
  }

  @Override
  public void clear(){
      root = null;
  }

  @Override
  public Iterator<E> getPreorderIterator() {
      return new PreorderIterator();
  }

// TernaryTree does not support in order iteration because there is no way to determine which
// value should precede the other, the parent or middle node if applicable. Both elements are in
// the same position following the rules of inorder iteration, so this tree type throws an UnsupportedOperationException
  @Override
  public Iterator<E> getInorderIterator() {
      throw new UnsupportedOperationException();
  }

  @Override
  public Iterator<E> getPostorderIterator() {
      return new PostorderIterator();
  }

  @Override
  public Iterator<E> getLevelOrderIterator() {
      return new LevelOrderIterator();
  }

  private class PreorderIterator implements Iterator<E> {
      private StackInterface<TernaryNode<E>> nodeStack;

        public PreorderIterator() {
          nodeStack = new LinkedStack<>();
          if (root != null) {
              nodeStack.push(root);
                }
          }

        public boolean hasNext() {
          return !nodeStack.isEmpty();
              }

        public E next() {
        TernaryNode<E> nextNode;
        if (hasNext()) {
          nextNode = nodeStack.pop();
          TernaryNode<E> leftChild = nextNode.getLeftChild();
          TernaryNode<E> rightChild = nextNode.getRightChild();
          TernaryNode<E> middleChild = nextNode.getMiddleChild();

          // Push into stack in reverse order of recursive calls
          if (rightChild != null) {
              nodeStack.push(rightChild);
            }

          if (middleChild != null) {
              nodeStack.push(middleChild);
            }

          if (leftChild != null) {
              nodeStack.push(leftChild);
            }

        } else {
              throw new NoSuchElementException();
            }

          return nextNode.getData();
              }

      public void remove() {
          throw new UnsupportedOperationException();
        }

    }

  private class PostorderIterator implements Iterator<E> {
    private StackInterface<TernaryNode<E>> nodeStack;
    private TernaryNode<E> currentNode;

    public PostorderIterator() {
        nodeStack = new LinkedStack<>();
        currentNode = root;
    }

    public boolean hasNext() {
        return !nodeStack.isEmpty() || (currentNode != null);
    }

    public E next() {
        TernaryNode<E> leftChild = null;
        TernaryNode<E> middleChild = null;
        TernaryNode<E> rightChild = null;
        TernaryNode<E> nextNode = null;

        // Find leftmost leaf
        while (currentNode != null) {
            nodeStack.push(currentNode);
            leftChild = currentNode.getLeftChild();
            if (leftChild == null && currentNode.getMiddleChild() == null) {
                currentNode = currentNode.getRightChild();
            } else if (leftChild == null ){
                currentNode = currentNode.getMiddleChild();
            } else {
                currentNode = leftChild;
            }
        }

        if (!nodeStack.isEmpty()) {
            nextNode = nodeStack.pop();
            TernaryNode<E> parent = null;
            if (!nodeStack.isEmpty()) {
                parent = nodeStack.peek();
                if (nextNode == parent.getLeftChild() && parent.getMiddleChild() != null) {
                    currentNode = parent.getMiddleChild();
                } else if (nextNode == parent.getLeftChild()) {
                    currentNode = parent.getRightChild();
                } else if (nextNode == parent.getMiddleChild()){
                    currentNode = parent.getRightChild();
                }  else  {
                    currentNode = null;
                }
            } else {
                currentNode = null;
            }
        } else {
            throw new NoSuchElementException();
        }

        return nextNode.getData();
    }


    public void remove() {
        throw new UnsupportedOperationException();
      }
    }

  private class LevelOrderIterator implements Iterator<E> {
    private QueueInterface<TernaryNode<E>> nodeQueue;

    public LevelOrderIterator() {
        nodeQueue = new LinkedQueue<>();
        if (root != null) {
            nodeQueue.enqueue(root);
        }
    }

    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }

    public E next() {
        TernaryNode<E> nextNode;

        if (hasNext()) {
            nextNode = nodeQueue.dequeue();
            TernaryNode<E> leftChild = nextNode.getLeftChild();
            TernaryNode<E> rightChild = nextNode.getRightChild();
            TernaryNode<E> middleChild = nextNode.getMiddleChild();

            if (leftChild != null) {
                nodeQueue.enqueue(leftChild);
            }

            if (middleChild != null) {
                nodeQueue.enqueue(middleChild);
            }

            if (rightChild != null) {
                nodeQueue.enqueue(rightChild);
            }

        } else {
            throw new NoSuchElementException();
        }

        return nextNode.getData();
    }

    public void remove() {
        throw new UnsupportedOperationException();
      }
  }

  public boolean contains(E elem){
		return contains(root, elem);
	}

  private boolean contains(TernaryNode<E> root, E elem){
		if(root != null){
		    if(elem.equals(root.getData())) {
          return true;
        }
        if (!elem.equals(root.getData())) {
          return contains(root.getLeftChild(), elem) || contains(root.getMiddleChild(), elem) || contains(root.getRightChild(), elem);
		    }
    }
    return false;
	}

  public boolean isBalanced(){
    return false;
  }

}
