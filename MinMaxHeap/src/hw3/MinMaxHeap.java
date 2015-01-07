package hw3;

public class MinMaxHeap	{
	private int currentSize;
	private int[] arr;

	public MinMaxHeap(int capacity){
		//Constructor
		arr = new int[capacity + 1];
		currentSize = 0;
	}

	public boolean isFull(){
		return currentSize == arr.length - 1;
	}

	public boolean isEmpty(){
		return currentSize == 0;
	}

	//PRE: The heap is not full
	public void insert(int x){
		int location=++currentSize;
		arr[location]=x;
		BubbleUp(location);
	}

	//PRE: The heap is not empty
	public int min(){
		return arr[1];
	}

	//PRE: The heap is not empty
	public int max(){
		if(currentSize==1){
			return arr[1];
		}
		else if(arr[getLeftChild(1)]>=arr[getRightChild(1)]){
			return arr[getLeftChild(1)];
		}
		else {return arr[getRightChild(1)];}
	}

	//PRE: The heap is not empty
	public int deleteMin(){
		int tmp=min();
		arr[1]=arr[currentSize];
		currentSize--;
		TrickleDown(1);
		return tmp;
	}

	//PRE: The heap is not empty
	public int deleteMax(){
		int tmp=max();
		arr[maxIndex()]=arr[currentSize];
		currentSize--;
		TrickleDown(maxIndex());
		return tmp;
	}


	//Private methods go here.
	
	private int maxIndex(){
		if(currentSize==1){
			return 1;
		}
		else if(arr[getLeftChild(1)]>arr[getRightChild(1)]){
			return 2;
		}
		else {return 3;}
	}

	private int getLeftChild (int pos){
		return pos*2; 
	}

	private int getRightChild (int pos){
		return pos*2+1;
	}

	private int getParent (int pos){
		return pos/2;
	}

	private int getGrandparent (int pos){
		return pos/4;
	}

	private void swap (int index1, int index2){
		int tmp = arr[index1];
		arr[index1]=arr[index2];
		arr[index2]=tmp;
	}

	private void BubbleUp(int i){
		int height = (int) (Math.log(i)/Math.log(2));

		if(height%2==0){
			if(getParent(i)>0 && arr[i]>arr[getParent(i)]){
				swap(i, getParent(i));
				BubbleUpMax(getParent(i));
			}
			else{
				BubbleUpMin(i);
			}
		}
		else {
			if(getParent(i)>0 && arr[i]<arr[getParent(i)]){
				swap(i, getParent(i));
				BubbleUpMin(getParent(i));
			}
			else{
				BubbleUpMax(i);
			}
		}
	}

	private void BubbleUpMin(int i){
		if(getGrandparent(i)>0){
			if(arr[i]<arr[getGrandparent(i)]){
				swap(i, getGrandparent(i));
				BubbleUpMin(getGrandparent(i));
			}
		}
	}

	private void BubbleUpMax(int i){
		if(getGrandparent(i)>0){
			if(arr[getGrandparent(i)]<arr[i]){
				swap(getGrandparent(i), i);
				BubbleUpMax(getGrandparent(i));
			}
		}
	}

	private void TrickleDown(int i){
		int height = (int) (Math.log(i)/Math.log(2));

		if(height%2==0){
			TrickleDownMin(i);
		}
		else{
			TrickleDownMax(i);
		}
	}

	private void TrickleDownMin(int i){
		if( getLeftChild(i)<=currentSize ){
			int m=getIndexSmallestChildGrand(i);
			if( getGrandparent(m) == i ){
				if(arr[m]<arr[i]){
					swap(i, m);
					if(arr[m]>arr[getParent(m)]){
						swap(m, getParent(m));
					}
					TrickleDownMin(m);
				}
			}
			else{
				if(arr[m]<arr[i]){
					swap(i, m);
				}
			}
		}
	}

	private void TrickleDownMax(int i){
		if (getLeftChild(i) <= currentSize){
			int m = getIndexLargestChildGrand(i);
			if( getGrandparent(m)==i){
				if(arr[m]>arr[i]){
					swap(i, m);
					if(arr[m]<arr[getParent(m)]){
						swap(m, getParent(m));
					}
				}
				TrickleDownMax(m);
			}
			else{
				if(arr[m]>arr[i]){
					swap(i, m);
				}
			}
		}
	}

	private int getIndexSmallestChildGrand(int pos){
		int smallest = getLeftChild(pos);

		if(getRightChild(pos)<=currentSize && arr[getRightChild(pos)]<arr[smallest]){
			smallest=getRightChild(pos);
		}
		if(getLeftChild(getLeftChild(pos))<=currentSize && arr[getLeftChild(getLeftChild(pos))]<arr[smallest]){
			smallest=getLeftChild(getLeftChild(pos));
		}
		if(getRightChild(getLeftChild(pos))<=currentSize && arr[getRightChild(getLeftChild(pos))]<arr[smallest]){
			smallest=getRightChild(getLeftChild(pos));
		}
		if(getLeftChild(getRightChild(pos))<=currentSize && arr[getLeftChild(getRightChild(pos))]<arr[smallest]){
			smallest=getLeftChild(getRightChild(pos));
		}
		if(getRightChild(getRightChild(pos))<=currentSize && arr[getRightChild(getRightChild(pos))]<arr[smallest]){
			smallest=getRightChild(getRightChild(pos));
		}

		return smallest;
	}

	private int getIndexLargestChildGrand(int pos){
		int largest = getLeftChild(pos);

		if(getRightChild(pos)<=currentSize && arr[getRightChild(pos)]>arr[largest]){
			largest=getRightChild(pos);
		}
		if(getLeftChild(getLeftChild(pos))<=currentSize && arr[getLeftChild(getLeftChild(pos))]>arr[largest]){
			largest=getLeftChild(getLeftChild(pos));
		}
		if(getRightChild(getLeftChild(pos))<=currentSize && arr[getRightChild(getLeftChild(pos))]>arr[largest]){
			largest=getRightChild(getLeftChild(pos));
		}
		if(getLeftChild(getRightChild(pos))<=currentSize && arr[getLeftChild(getRightChild(pos))]>arr[largest]){
			largest=getLeftChild(getRightChild(pos));
		}
		if(getRightChild(getRightChild(pos))<=currentSize && arr[getRightChild(getRightChild(pos))]>arr[largest]){
			largest=getRightChild(getRightChild(pos));
		}

		return largest;
	}

	public void print(){
		int level=0;
		for (int i = 1; i <= currentSize; i++, level++){
			if (2*i+1>currentSize){break; }
			if(level%2==0){System.out.print("MIN ");}
			else{System.out.print("MAX ");}
			System.out.print(" PARENT : " + arr[i] + " LEFT CHILD : " + arr[2*i] + " RIGHT CHILD :" + arr[2 * i  + 1]);
			System.out.println();
		}
	}
	
	public void printHeap(){
		for (int i =1; i <= currentSize; i++){
			System.out.print(arr[i] + "    ");
		}
	}

}

