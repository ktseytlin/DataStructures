package hw4;

public class NodeEntry {
	public Node node;
	public NodeEntry next;
	
	public NodeEntry(Node node, NodeEntry next){
		this.node=node;
		this.next=next;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public NodeEntry getNext() {
		return next;
	}

	public void setNext(NodeEntry next) {
		this.next = next;
	}
	
	
}
