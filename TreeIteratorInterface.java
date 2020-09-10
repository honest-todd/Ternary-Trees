package cs445.a5;

import java.util.Iterator;

/**
 * An interface of iterators for the ADT tree.
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @author William C. Garrison
 * @version 4.5
 */
public interface TreeIteratorInterface<E> {
   /** Creates an iterator to traverse the tree in preorder fashion
    *  @return  the iterator */
    public Iterator<E> getPreorderIterator();

   /** Creates an iterator to traverse the tree in postorder fashion
    *  @return  the iterator */
    public Iterator<E> getPostorderIterator();

   /** Creates an iterator to traverse the tree in inorder fashion
    *  @return  the iterator */
    public Iterator<E> getInorderIterator();

   /** Creates an iterator to traverse the tree in level-order fashion
    *  @return  the iterator */
    public Iterator<E> getLevelOrderIterator();
}

