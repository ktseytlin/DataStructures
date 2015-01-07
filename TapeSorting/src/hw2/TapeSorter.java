package hw2;

/**
 * Represents a machine with limited memory that can sort tape drives.
 */
public class TapeSorter {

    private int memorySize;
    private int tapeSize;
    public int[] memory;

    public TapeSorter(int memorySize, int tapeSize) {
        this.memorySize = memorySize;
        this.tapeSize = tapeSize;
        this.memory = new int[memorySize];
    }

    /**
     * Sorts the first `size` items in memory via quicksort
     */
    public void quicksort(int size) {
        // TODO: Implement me for 10 points
    	quicksort(memory, 0, (size-1));
    }
    public static void quicksort(int[] array, int start_index, int end_index) {
        
        int idx = partition(array, start_index, end_index);

        // Recursively call quicksort with left part of the partitioned array
        if (start_index < idx - 1) {
            quicksort(array, start_index, idx - 1);
        }

        // Recursively call quick sort with right part of the partitioned array
        if (end_index > idx) {
            quicksort(array, idx, end_index);
        }
    }
    public static int partition(int[] array, int left, int right) {
        int pivot = array[left]; // taking first element as pivot

        while (left <= right) {
            //searching number which is greater than pivot, bottom up
            while (array[left] < pivot) {
                left++;
            }
            //searching number which is less than pivot, top down
            while (array[right] > pivot) {
                right--;
            }

            // swap the values
            if (left <= right) {
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;

                //increment left index and decrement right index
                left++;
                right--;
            }
        }
        return left;
    }
    
    /**
     * Reads in numbers from drive `in` into memory (a chunk), sorts it, then writes it out to a different drive.
     * It writes chunks alternatively to drives `out1` and `out2`.
     *
     * If there are not enough numbers left on drive `in` to fill memory, then it should read numbers until the end of
     * the drive is reached.
     *
     * Example 1: Tape size = 8, memory size = 2
     * ------------------------------------------
     *   BEFORE:
     * in: 4 7 8 6 1 3 5 7
     *
     *   AFTER:
     * out1: 4 7 1 3 _ _ _ _
     * out2: 6 8 5 7 _ _ _ _
     *
     *
     * Example 2: Tape size = 10, memory size = 3
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5
     *
     *   AFTER:
     * out1: 3 6 8 0 3 7 _ _ _ _
     * out2: 1 3 9 5 _ _ _ _ _ _
     *
     *
     * Example 3: Tape size = 13, memory size = 4
     * ------------------------------------------
     *   BEFORE:
     * in: 6 3 8 9 3 1 0 7 3 5 9 2 4
     *
     *   AFTER:
     * out1: 3 6 8 9 2 3 5 9 _ _ _ _ _
     * out2: 0 1 3 7 4 _ _ _ _ _ 
     * _ _ _
     */
    public void initialPass(TapeDrive in, TapeDrive out1, TapeDrive out2) {
    	// TODO: Implement me for 15 points!
    	int choosetape=0; 
    	
    	for(int length=0; length<tapeSize; ){	
    		int numMemory=0;
    		for(int i=0; i<memorySize; i++, numMemory++, length++){
    			if(length==tapeSize){
    				numMemory++;
    				break;
    			}
    			memory[i]=in.read();
    		}
    		
    		if(numMemory==memorySize){
    			quicksort(numMemory);
    		}
    		else{
    			quicksort(numMemory-1);
    		}

    		for(int j=0; j<numMemory; j++){
    			if(choosetape%2==0){
    				out1.write(memory[j]);
    			}
    			else{
    				out2.write(memory[j]);
    			}
    		}
    		choosetape++;		
    		resetMemory(memory);
    	}
    }
    
//helper method to write over previously used memory and clear it out    
public void resetMemory(int[] mem){
	for(int i=0; i<mem.length; i++){
		mem[i]=0;
	}
}
    
    /**
     * Merges the first chunk on drives `in1` and `in2` and writes the sorted, merged data to drive `out`.
     * The size of the chunk on drive `in1` is `size1`.
     * The size of the chunk on drive `in2` is `size2`.
     *
     *          Example
     *       =============
     *
     *  (BEFORE)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *             ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *             ^
     * out:  [ ... _ _ _ _ _ ... ]
     *             ^
     * size1: 4, size2: 4
     *
     *   (AFTER)
     * in1:  [ ... 1 3 6 8 9 ... ]
     *                     ^
     * in2:  [ ... 2 4 5 7 8 ... ]
     *                     ^
     * out:  [ ... 1 2 3 4 5 6 7 8 _ _ _ ... ]
     *                             ^
     */
    public void mergeChunks(TapeDrive in1, TapeDrive in2, TapeDrive out, int size1, int size2) {
    	// TODO: Implement me for 10 points
    	
    	int numread_in1=0;
    	int numread_in2=0;
    	int value_in1=in1.read();
    	int value_in2=in2.read();
    	
    	while(numread_in1<size1 && numread_in2<size2){
    		if(value_in1<=value_in2){
    			out.write(value_in1);
    			numread_in1++;
    			if(numread_in1==size1){break;}
    			value_in1=in1.read();
    			
    		}
    		else{
    			out.write(value_in2);
    			numread_in2++;
    			if(numread_in2==size2){break;}
    			value_in2=in2.read();
    		}
    	}
    	
    	while(numread_in1<size1){
    		out.write(value_in1);
    		numread_in1++;
    		if(numread_in1==size1){break;}
    		value_in1=in1.read();
    	}
    	
    	while(numread_in2<size2){
    		out.write(value_in2);
			numread_in2++;
			if(numread_in2==size2){break;}
			value_in2=in2.read();
    	}
    }
    

    /**
     * Merges chunks from drives `in1` and `in2` and writes the resulting merged chunks alternatively to drives `out1`
     * and `out2`.
     *
     * The `runNumber` argument denotes which run this is, where 0 is the first run.
     *
     * -- Math Help --
     * The chunk size on each drive prior to merging will be: memorySize * (2 ^ runNumber) --> on output drive
     * The number of full chunks on each drive is: floor(tapeSize / (chunk size * 2)) --> Math.floor
     *   Note: If the number of full chunks is 0, that means that there is a full chunk on drive `in1` and a partial
     *   chunk on drive `in2`.
     * The number of leftovers is: tapeSize - 2 * chunk size * number of full chunks
     *
     * To help you better understand what should be happening, here are some examples of corner cases (chunks are
     * denoted within curly braces {}):
     *
     * -- Even number of chunks --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 6 9 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 6 7 8 9 9 }
     *
     * -- Odd number of chunks --
     * in1 ->   { 1 3 5 } { 6 7 9 } { 3 4 8 }
     * in2 ->   { 2 4 6 } { 2 7 8 } { 0 3 9 }
     * out1 ->  { 1 2 3 4 5 6 } { 0 3 3 4 8 9 }
     * out2 ->  { 2 6 7 7 8 9 }
     *
     * -- Number of leftovers <= the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 5 7 8 9 }
     *
     * -- Number of leftovers > the chunk size --
     * in1 ->   { 1 3 5 6 } { 5 7 8 9 }
     * in2 ->   { 2 3 4 7 } { 3 5 }
     * out1 ->  { 1 2 3 3 4 5 6 7 }
     * out2 ->  { 3 5 5 7 8 9 }
     *
     * -- Number of chunks is 0 --
     * in1 ->   { 2 4 5 8 9 }
     * in2 ->   { 1 5 7 }
     * out1 ->  { 1 2 4 5 5 7 8 9 }
     * out2 ->
     */
    
    
    public void doRun(TapeDrive in1, TapeDrive in2, TapeDrive out1, TapeDrive out2, int runNumber) {
        // TODO: Implement me for 15 points
    	int chunksize = (int) (memorySize * Math.pow(2,runNumber));
    	int numoffullchunks = (int)Math.floor(tapeSize / (chunksize * 2));
    	int leftover = tapeSize - 2 * chunksize * numoffullchunks;

    	boolean switch_out1=true;

    	in1.reset();
    	in2.reset();
    	clearDrive(out1);
    	clearDrive(out2);
    	
    	for(int i=0; i<numoffullchunks; i++){
    		if(switch_out1){mergeChunks(in1, in2, out1, chunksize, chunksize);}
    		else{mergeChunks(in1, in2, out2, chunksize, chunksize);}
    		switch_out1 = (switch_out1) ? false : true;
    	}
    	
    	if(leftover>0){
    		if(leftover>chunksize){
    			if(switch_out1){mergeChunks(in1, in2, out1, chunksize, (leftover-chunksize));}
    			else{mergeChunks(in1, in2, out2, chunksize, (leftover-chunksize));}
    		}
    		else{
    			if(switch_out1){mergeChunks(in1, in2, out1, leftover, 0);}
    			else{mergeChunks(in1, in2, out2, leftover, 0);}
    		}
    	}

    	in1.reset();
    	in2.reset();
    	out1.reset();
    	out2.reset();

    }
    
    /**
     * Sorts the data on drive `t1` using the external sort algorithm. The sorted data should end up on drive `t1`.
     *
     * Initially, drive `t1` is filled to capacity with unsorted numbers.
     * Drives `t2`, `t3`, and `t4` are empty and are to be used in the sorting process.
     */
    public void clearDrive(TapeDrive clearing){
		for(int i=0; i<tapeSize; i++){
			clearing.write(0);
		}
		clearing.reset();
    }
    
    public void sort(TapeDrive t1, TapeDrive t2, TapeDrive t3, TapeDrive t4) {
        // TODO: Implement me for 15 points
    	//alternate between tapes because want out to become in and vice versa
    	
    	initialPass(t1, t2, t3);
    	
    	clearDrive(t1);
		
    	int i;
    	for(i=0; memorySize * Math.pow(2,i)<tapeSize; i++){

    		if(i%2==0){
    			doRun(t2,t3,t1,t4, i);
    			clearDrive(t2);
    			clearDrive(t3);
    		}
    		else{
    			doRun(t1,t4,t2,t3, i);
    			clearDrive(t1);
    			clearDrive(t4);
    		}
    	}
    	
    	//if mod%2 of run times (i.e. i) is equal to 0 then tape is in t2 and need to move to t1
    	int movet2_to_t1;
    	if (i%2==0){
    		//clear everything
    		clearDrive(t1);
    		t2.reset();
    		for (int j=0; j<tapeSize; j++){
    			movet2_to_t1=t2.read();
    			t1.write(movet2_to_t1);
    		}
    		//clearing t2 so that everything returns the same way it came in except for t1 now sorted
    		clearDrive(t2);
    	}
    }

    public static void main(String[] args) {

    	// For this part I have removed all the testers I used and put back in the assignment tester.
    	
    	TapeSorter tapeSorter = new TapeSorter(10, 80);
        TapeDrive t1 = TapeDrive.generateRandomTape(80);
        TapeDrive t2 = new TapeDrive(80);
        TapeDrive t3 = new TapeDrive(80);
        TapeDrive t4 = new TapeDrive(80);

        tapeSorter.sort(t1, t2, t3, t4);
        int last = Integer.MIN_VALUE;
        boolean sorted = true;
        for (int i = 0; i < 80; i++) {
            int val = t1.read();
            sorted &= last <= val;
            last = val;
        }
        if (sorted)
            System.out.println("Sorted!");
        else
            System.out.println("Not sorted!");
    }
}
