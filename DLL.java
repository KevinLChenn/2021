/*
 * Kevin Chen
 * August 4th 2021
 */
public class DLL{
	private static class Node<String> {

		private String element;               // reference to the element stored at this node
		private Node<String> west;            // reference to the west node
		private Node<String> east;    		  // reference to the east node
		private Node<String> north;           // reference to the north node
		private Node<String> south;			  // reference to the south node

		/**
		 * Creates a node with the given element and north, south, east, west node.
		 */
		public Node(String e, Node<String> n, Node<String> s,  Node<String> ea, Node<String> w) {
			element = e;
			north = n;
			south = s;
			east = ea;
			west = w;
		}

		public String getElement() {return element;}
		public Node<String> getEast() {return east;}
		public Node<String> getWest() { return west; }
		public Node<String> getNorth() { return north; }
		public Node<String> getSouth() { return south; }

		public void setEast(Node<String> ea) { east = ea; }
		public void setWest(Node<String> w) { west = w; }
		public void setNorth(Node<String> n) { north = n; }
		public void setSouth(Node<String> s) { south = s; }

	}

	/** Number of elements in the list (not including sentinels) */
	private int size = 0;                      // number of elements in the list
	private Node<String> position;			 // the current position you can add nodes to
	private Node<String> root = new Node<String>(null,null,null,null,null);		// the root node with no value

	/** Constructs a new empty list. */
	public DLL() {
	}

	/** Constructs the root with the value of letter, a parameter. */
	public void Root(String letter) {
		root = new Node<String>(letter,null,null,null,null);
		position = root;
	}

	/** Adds a node with value letter in the direction of the parameter. */
	public void Add(String letter, String direction) {
		Node<String> newNode = new Node<String>(letter, null, null, null, null);
		if (direction == "West")
		{
			position.setWest(newNode);
			newNode.setEast(position);
		}
		else if (direction == "East")
		{
			position.setEast(newNode);
			newNode.setWest(position);
		}
		else if (direction == "North")
		{
			position.setNorth(newNode);
			newNode.setSouth(position);
		}
		else if (direction == "South")
		{
			position.setSouth(newNode);
			newNode.setNorth(position);
		}
		position = newNode;
	}

	/** Moves the position using directions. */
	public void Move(String direction) {
		if (direction == "West")
		{
			position = position.getWest();
		}
		else if (direction == "East")
		{
			position = position.getEast();
		}
		else if (direction == "North")
		{
			position = position.getNorth();
		}
		else if (direction == "South")
		{
			position = position.getSouth();
		}
	}

	public void Print() {
		Print(position,0);
	}

	/** Helper function for the Print() function */
	public void Print(Node<String> position, int lastDirect) {
		System.out.print(position.getElement()+ ",");
		if (position.getEast() !=null && lastDirect!=1)
		{
			Print(position.getEast(),2);
		}
		if (position.getWest() !=null&& lastDirect!=2)
		{
			Print(position.getWest(),1);
		}
		if (position.getSouth() !=null && lastDirect!=3)
		{
			Print(position.getSouth(),4);
		}
		if (position.getNorth() !=null && lastDirect!=4)
		{
			Print(position.getNorth(),3);
		}
	}

	/** formats the model in a 2D array so it is able to print out a configured model. */
	public void extraCredit() {
		String [][] format = new String[25][25];
		for(int i = 0; i<25; i++)
		{
			for(int j = 0; j<25; j++)
			{
				format[i][j] = " ";
			}
		}
		int initX = 12;
		int initY = 12;
		format[initX][initY]= root.getElement();

		extraCredit(root,initX, initY, 0 , format);

		for(int i = 0; i<25; i++)
		{
			System.out.println();
			for(int j = 0; j<25; j++)
			{
				System.out.print(format[i][j]);
			}
		}
	}

	public void extraCredit(Node<String> position, int initX, int initY, int x, String[][] format) {
		if (position.getEast() !=null && x!=1)
		{
			format[initX][initY+1] = "-";
			format[initX][initY+2] = position.getEast().getElement();
			extraCredit(position.getEast(),initX, initY+2, 2, format);
		}
		if (position.getWest() !=null&& x!=2) {
			format[initX][initY-1] = "-";
			format[initX][initY-2] = position.getWest().getElement();
			extraCredit(position.getWest(),initX, initY-2, 1, format);
		}
		if (position.getSouth() !=null && x !=3) {
			format[initX+1][initY] = "|";
			format[initX+2][initY] = position.getSouth().getElement();
			extraCredit(position.getSouth(),initX+2, initY, 4, format);
		}
		if (position.getNorth() !=null && x!=4) {
			format[initX-1][initY] = "|";
			format[initX-2][initY] = position.getNorth().getElement();
			extraCredit(position.getNorth(),initX-2, initY, 3, format);
		}
	}

	/**
	 * Returns the number of elements in the linked list.
	 * @return number of elements in the linked list
	 */
	public int size() { return size; }

	/**
	 * Tests whether the linked list is empty.
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() { return size == 0; }

	/**
	 * Returns (but does not remove) the first element of the list.
	 * @return element at the front of the list (or null if empty)
	 */

}