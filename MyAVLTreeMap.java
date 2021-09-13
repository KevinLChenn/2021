package Assignment3;

import net.datastructures.*;
import java.util.Comparator;


public class MyAVLTreeMap<K,V> extends TreeMap<K,V> {
	
  /** Constructs an empty map using the natural ordering of keys. */
  public MyAVLTreeMap() { super(); }

  /**
   * Constructs an empty map using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the map
   */
  public MyAVLTreeMap(Comparator<K> comp) { super(comp); }

  /** Returns the height of the given tree position. */
  protected int height(Position<Entry<K,V>> p) {
    return tree.getAux(p);
  }

  /** Recomputes the height of the given position based on its children's heights. */
  protected void recomputeHeight(Position<Entry<K,V>> p) {
    tree.setAux(p, 1 + Math.max(height(left(p)), height(right(p))));
  }

  /** Returns whether a position has balance factor between -1 and 1 inclusive. */
  protected boolean isBalanced(Position<Entry<K,V>> p) {
    return Math.abs(height(left(p)) - height(right(p))) <= 1;
  }

  /** Returns a child of p with height no smaller than that of the other child. */
  protected Position<Entry<K,V>> tallerChild(Position<Entry<K,V>> p) {
    if (height(left(p)) > height(right(p))) return left(p);     // clear winner
    if (height(left(p)) < height(right(p))) return right(p);    // clear winner
    // equal height children; break tie while matching parent's orientation
    if (isRoot(p)) return left(p);                 // choice is irrelevant
    if (p == left(parent(p))) return left(p);      // return aligned child
    else return right(p);
  }

  /**
   * Utility used to rebalance after an insert or removal operation. This traverses the
   * path upward from p, performing a trinode restructuring when imbalance is found,
   * continuing until balance is restored.
   */
  protected void rebalance(Position<Entry<K,V>> p) {
    int oldHeight, newHeight;
    do {
      oldHeight = height(p);                       // not yet recalculated if internal
      if (!isBalanced(p)) {                        // imbalance detected
        // perform trinode restructuring, setting p to resulting root,
        // and recompute new local heights after the restructuring
        p = restructure(tallerChild(tallerChild(p)));
        recomputeHeight(left(p));
        recomputeHeight(right(p));
      }
      recomputeHeight(p);
      newHeight = height(p);
      p = parent(p);
    } while (oldHeight != newHeight && p != null);
  }

  /** Overrides the TreeMap rebalancing hook that is called after an insertion. */
  @Override
  protected void rebalanceInsert(Position<Entry<K,V>> p) {
    rebalance(p);
  }

  /** Overrides the TreeMap rebalancing hook that is called after a deletion. */
  @Override
  protected void rebalanceDelete(Position<Entry<K,V>> p) {
    if (!isRoot(p))
      rebalance(parent(p));
  }

  /** Ensure that current tree structure is valid AVL (for debug use only). */
  private boolean sanityCheck() {
    for (Position<Entry<K,V>> p : tree.positions()) {
      if (isInternal(p)) {
        if (p.getElement() == null)
          System.out.println("VIOLATION: Internal node has null entry");
        else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
          System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
          dump();
          return false;
        }
      }
    }
    return true;
  }
  
  public String [][] printTree1(Position<Entry<K,V>> p, int col, int row, String [][] display)
  {
	  //checks if current element is null if not it will be put inside the display array at variables col and row
	  if (p.getElement().getKey() != null) {
		  if(p!=null) {
			  display[row][col] = String.valueOf(p.getElement().getKey());
		  }
      }
      
	  //checks for left child if so puts a branch and recursively recalls printTree1 with the left child
      if (left(p).getElement() != null) {
    	  display[row+2][(int)(col-(12.5/(row+1)))] = "/";
	      printTree1(left(p), col-(25/(row+1)), row+4, display);
      }
    //checks for riight child if so puts a branch and recursively recalls printTree1 with the right child
      if (right(p).getElement() != null) {
    	  display[row+2][(int)(col+(12.5/(row+1)))] = "\\";
	      printTree1(right(p), col+(25/(row+1)), row+4, display);
      }
      return display;
  }
  
  public void printTree() {
	  // Put your code to print AVL tree here
	 //instantiate height variable
	  int height = height(root());
	  System.out.println("Print of tree");
	   String[][] display = new String[100][100];
	   for (int y = 0; y<100;y++) {
		   for (int z =0; z<100;z++) {
			   	display[y][z] = " ";
		   }
	   }
	  //starts the root at col 50 (around the middle)
	  printTree1(tree.root(), 50, 0 , display);
	  for (int y = 0; y<(height*4);y++) {
		   for (int z =0; z<100;z++) {
			   System.out.print(display[y][z]);
		   }
		   System.out.println();
	  }
  }
}
