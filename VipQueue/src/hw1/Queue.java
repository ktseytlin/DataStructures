package hw1;

public class Queue<T> {
	
	private Node<T> front, rear;
	int currentsize;
	
	public Queue(){
		front=null;
		rear=null;
		currentsize=0;
	}

	public boolean isEmpty(){
		return currentsize==0;
	}
	
	public T peek(){//PRE: not empty
		return front.data;
	}
	
	public T dequeue(){//PRE: not empty
		T tmp = front.data;
		front = front.next;
		currentsize -= 1;
		return tmp;
	}
	
	public void enqueue(T element){//PRE: not full
		Node<T> tmp = new Node<T>(element, null);
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
