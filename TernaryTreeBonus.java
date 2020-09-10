package cs445.a5;

/**
 * Bonus methods for a ternary tree.
 * @author William C. Garrison
 * @version 4.5
 */
public interface TernaryTreeBonus<E> extends TernaryTreeInterface<E> {

    /**
     * Determines whether the tree contains the given entry. Equality is
     * determined by using the .equals() method.
     * @param elem The entry to be searched for
     * @return true if elem is in the tree, false if not
     */
    public boolean contains(E elem);

    /**
     * Determines if the tree is "balanced," where a balanced tree is one where,
     * for any node in the tree, the heights of its subtrees differ by no more
     * than one.
     * @return true if the tree is balanced, false if there is a node whose
     * children have heights that differ by more than 1.
     */
    public boolean isBalanced();

}

