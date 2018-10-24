
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

	public LinkStrand() {
		
		this("");
		
	}
	
	public LinkStrand(String s) {
		
		initialize(s);
		
	}
	
	@Override
	public long size() {

		return 0;
	}

	@Override
	public void initialize(String source) {
		
		Node node = new Node(source);
		myFirst = node;
		myLast = node;
		
		mySize = node.info.length();
		myAppends = 0;
		
	}

	@Override
	public IDnaStrand getInstance(String source) {

		return null;
	}

	@Override
	public IDnaStrand append(String dna) {

		return null;
	}

	@Override
	public IDnaStrand reverse() {

		return null;
	}

	@Override
	public int getAppendCount() {

		return 0;
	}

	@Override
	public char charAt(int index) {

		return 0;
	}
	
		
	
}
