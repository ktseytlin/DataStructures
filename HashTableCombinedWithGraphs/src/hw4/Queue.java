package hw4;

public class Queue {
	//create a a queue of node entries
	
	private NodeEntry front, rear;
	int currentsize;
	
	public Queue(){
		front=null;
		rear=null;
		currentsize=0;
	}

	public Node peek(){//PRE: not empty
		return front.node;
	}
	
	public boolean isEmpty(){
		return currentsize==0;
	}
	
	public Node dequeue(){//PRE: not empty
		Node tmp = front.node;
		front = front.next;
		currentsize -= 1;
		return tmp;
	}
	
	public void enqueue(Node element){//PRE: not full
		NodeEntry tmp = new NodeEntry(element, null);
		if (currentsize ==0){
			rear = tmp;
			front = tmp;
		}
		else{
			rear.next = tmp;
			rear = tmp;
		}
		currentsize +=1;
	}
}
