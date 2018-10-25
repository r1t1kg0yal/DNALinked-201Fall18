 
public class LinkStrand implements IDnaStrand {
	
	private class Node {
		
		String info;
	   	Node next;
	   
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

	public LinkStrand() {
		
		this("");
		
	}
	
	public LinkStrand(String s) {
		
		initialize(s);
		
	}
	
	@Override
	public long size() {

		return mySize;
	}

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

	@Override
	public IDnaStrand getInstance(String source) {
		
		return new LinkStrand(source);
		
	}

	@Override
	public IDnaStrand append(String dna) {

		Node node = new Node(dna);
		
		myLast.next = node;
		myLast = node; 
		
		mySize += dna.length(); 
		myAppends++; 
		
		return this;
	}

	@Override
	public IDnaStrand reverse() {
		
		LinkStrand reversed = new LinkStrand();
		
		StringBuilder sbuild = new StringBuilder(myFirst.info);
		sbuild = sbuild.reverse();
		String s = sbuild.toString();
		
		reversed.myLast.info = s;
		reversed.myFirst = reversed.myLast;
		reversed.myAppends++;
		
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
			
			myFirst = myFirst.next;
		}
		
		myFirst = first;
		
		reversed.mySize = reversed.size();
		
		return reversed;
		
	}

	@Override
	public int getAppendCount() {

		return myAppends;
	}

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
