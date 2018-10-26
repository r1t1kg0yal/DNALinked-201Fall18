 
public class LinkStrand implements IDnaStrand {
	
	/**
	 * Create the Node class
	 * for use in LinkStrand
	 */
	private class Node {
		
		String info;
	   	Node next;
	   	
	   	/**
	   	 * Construct a Node
	   	 * @param s is a String 
	   	 * containing info of Node
	   	 */
	   	public Node(String s) {
	      	
	   		info = s;
	      	next = null;
	   
	   	}
	}
	
	private Node myFirst;
	private Node myLast;
	private long mySize;
	private int myAppends;
	
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;

	/**
	 * Default constructor for a LinkStrand Object
	 */
	public LinkStrand() {
		
		this("");
		
	}
	
	/**
	 * Create a LinkStrand object given info 
	 * for a single Node in the strand, using
	 * initialize method
	 * @param s is info for the first/only Node
	 */
	public LinkStrand(String s) {
		
		initialize(s);
		
	}
	
	/**
	 * Returns the number of elements/base-pairs/nucleotides in this strand.
	 * @return the number of base-pairs in this strand
	 */
	@Override
	public long size() {

		return mySize;
	}

	/**
	 * Initialize by copying DNA data from the string into this strand,
	 * replacing any data that was stored. The parameter should contain only
	 * valid DNA characters, no error checking is done by the this method.
	 * MyFirst and myLast are set to the node with info of source, while
	 * all index-related instance variables are set to first value in strand
	 * 
	 * @param source
	 *            is the string used to initialize this strand
	 */
	@Override
	public void initialize(String source) {
		
		Node node = new Node(source);
		myFirst = node;
		myLast = node;
		
		mySize = node.info.length();
		myAppends = 0;
		
		myIndex = 0;
		myLocalIndex = 0;
		myCurrent = myFirst;
	}
	
	/**
	 * Return this object, useful to obtain
	 * an object without knowing its type, e.g.,
	 * calling dna.getInstance() returns an IDnaStrand
	 * that will be the same concrete type as dna
	 * @param source is data from which object constructed
	 * @return a LinkStrand whose .toString() method will be source
	 */
	@Override
	public IDnaStrand getInstance(String source) {
		
		return new LinkStrand(source);
		
	}

	@Override
	/**
	 * Append dna to the end of this strand.
	 * @param dna
	 *            is the string appended to this strand
	 * @return this strand after the data has been added
	 */
	public IDnaStrand append(String dna) {

		Node node = new Node(dna);
		
		myLast.next = node;
		myLast = node; 
		
		mySize += dna.length(); 
		myAppends++; 
		
		return this;
	}
	
	/**
	 * Returns an IDnaStrand that is the reverse of this strand, e.g., for
	 * "CGAT" returns "TAGC"
	 * 
	 * @return reverse strand
	 */
	@Override
	public IDnaStrand reverse() {
		
		LinkStrand reversed = new LinkStrand();
		
		StringBuilder sbuild = new StringBuilder(myFirst.info);
		sbuild = sbuild.reverse();
		String s = sbuild.toString();
		
		reversed.myLast.info = s;
		reversed.myFirst = reversed.myLast;
		reversed.myAppends++;
		reversed.mySize += s.length();
		
		Node first = myFirst;
		myFirst = myFirst.next;
		
		while(myFirst!=null) {
			
			StringBuilder sb = new StringBuilder(myFirst.info);
			sb = sb.reverse();
			String ss = sb.toString();
			
			Node temp = new Node(ss);
			temp.next = reversed.myFirst;
			reversed.myFirst = temp;
			reversed.myAppends++;
			reversed.mySize += ss.length();
			
			myFirst = myFirst.next;
		}
		
		myFirst = first;
				
		return reversed;
		
	}
	
	/**
	 * Returns the number of times append has been called.
	 * 
	 * @return myAppends
	 */
	@Override
	public int getAppendCount() {

		return myAppends;
	}

	/**
	 * Returns character at a specified index, where 0 <= index < size()
	 * @param index specifies which character will be returned
	 * @return the character at index
	 * @throws IndexOutOfBoundsException if index < 0 or inde >= size()
	 */
	@Override
	public char charAt(int index) {
		
		if(index>=mySize||index<0)
			throw new IndexOutOfBoundsException();
		
		Node list = myCurrent;
		
		if(index < myIndex)
		{
			myIndex = 0;
			myLocalIndex = 0;
			list = myFirst;
		}
		
		while (myIndex != index) {
			
			myIndex++;
			myLocalIndex++;
			if (myLocalIndex >= list.info.length()) {
				
				myLocalIndex = 0;
				list = list.next;
				
			}
			
		}
		
		myCurrent = list;
		
        return list.info.charAt(myLocalIndex);	
	}
	
	/**
	 * Returns a string representation of data
	 * stored in this LinkStrand. Uses StringBuilder
	 * class to easily manipulate data by adding
	 * info in each Node to a single string.
	 * @return String representation of strands combined
	 */
	public String toString() {
		
		StringBuilder finalString = new StringBuilder();
		
		Node first = myFirst;
		
		while(myFirst!=null)
		{
			finalString.append(myFirst.info);
			myFirst = myFirst.next;
		}
		
		myFirst = first;
		
		return finalString.toString();
		
	}
	
}
