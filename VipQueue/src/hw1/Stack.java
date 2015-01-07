package hw1;

public class Stack<T> {
	Node <T> top;
	int currentsize;
	
	public Stack(){
		top=null;
		currentsize=0;
	}
	
	public boolean isEmpty(){
		return currentsize == 0;
	}
	
	public T peek(){
		return top.data;
	}
	
	public T pop(){ // PRE: not empty
		T tmp = top.data;
		top = top.next;
		currentsize-=1;
		return tmp;
	}
	
	public void push(T element){ // PRE: not full
		top = new Node<T>(element, top);
		currentsize+=1;
	}

	
}
