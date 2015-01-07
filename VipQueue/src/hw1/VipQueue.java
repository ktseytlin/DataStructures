package hw1;

public class VipQueue<T> {

	Stack<T> stack;
	Queue<T> queue;
	
	public VipQueue(){
		stack = new Stack<T> ();
		queue = new Queue<T> ();
	}
	
	public boolean isEmpty(){
		if(queue.isEmpty()&&stack.isEmpty()){return true;}
		else{return false;}
	}
	
	public T peek(){
		 if(!stack.isEmpty()){return stack.peek();}
		 else{return queue.peek();}
	}
	
	public T dequeue(){ //PRE: vipqueue not empty
		if(!stack.isEmpty()){return stack.pop();}
		else{return queue.dequeue();}
	}
	
	public void enqueue (T element){
		queue.enqueue(element);
	}
	
	public void vipEnqueue(T element){
		stack.push(element);
	}
}		