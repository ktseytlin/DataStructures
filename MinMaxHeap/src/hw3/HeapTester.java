package hw3;

public class HeapTester {
	
	public static void main(String[]args){
		MinMaxHeap test = new MinMaxHeap(11);
		
		//TEST IF NOT ALL THE POSITIONS ARE FULL
		
		test.insert(10);
		//test.insert(30);
		test.insert(-5);
		test.insert(4);
		test.insert(3);
		test.insert(30);
		test.insert(0);
		test.insert(40);
		test.insert(25);
		
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMax();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		//fix up the print method and try to output this all over again just so I can watch the outputs of the parents and children
		//run this with a random number generator to make sure that the values are outputting correctly
		
		//test.insert(0);
		
		System.out.println("_____________________");
		
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
	
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
		
		test.deleteMin();
		test.printHeap();
		System.out.println();
		System.out.println("Max: "+test.max());
		System.out.println("Min: "+test.min());
		
		System.out.println("_____________________");
	}

}
