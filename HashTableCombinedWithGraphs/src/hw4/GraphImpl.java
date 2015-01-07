package hw4;

public class GraphImpl extends Graph{
	private NodeEntry[] edges;
	private Node[] nodes;
	HashTable <String,Node> lookupTable;
	private int currentIndex; //next available index to add a node to
	private int graphsize;
	
	public GraphImpl(int graphsize){		
		this.graphsize=graphsize;
		edges=new NodeEntry [graphsize];
		nodes=new Node[graphsize];
		currentIndex=0;
		lookupTable=HashTableFactory.create();
	}
	
	//pre: node does not already exist
	public void addNode(Node node){ 
		NodeImpl currentNode=(NodeImpl)node;
		currentNode.setId(currentIndex);
		nodes[node.getId()]=node;
		currentIndex++;
		lookupTable.put(node.getName(), node);
		
	}

    public void addEdge(Node node1, Node node2){
    	edges[node1.getId()]=new NodeEntry(node2, edges[node1.getId()]);
    }

    public Node lookupNode(int id){
    	return nodes[id];
    }

    public Node lookupNode(String name){
    	return lookupTable.get(name);
    }

    public boolean isAcyclic(){
    	
    	int sorted[]=sort();
    	int[] checkCyclic = new int [currentIndex];
    	
    	for(int i=0; i<currentIndex; i++){
    		int value=sorted[i];
    		if(checkCyclic[value]==1){return false;}
    		checkCyclic[value]=1;
    	}
    	return true; 
    }
    
    public int[] sort() {
    	int numNodesLeft=currentIndex;
		int [] inDegrees = new int[currentIndex];
		int [] topologicalSortArr = new int [currentIndex];
		int topologicalSortLoc=0;
		int[] enqueuedItems = new int [currentIndex];

		Queue zeroIndegrees = new Queue();
		
		for(int i: inDegrees){
    		i=0;
    	}
		
		for(int i: enqueuedItems){
			i=0;
		}
    	
		int pointer=0;
    	for(NodeEntry j: edges){
			NodeEntry currentLoc = edges[pointer];
    		while(currentLoc!=null){
    			Node currentNode = currentLoc.getNode();
    			int currentLocID = currentNode.getId();
    			inDegrees[currentLocID]++;
    			currentLoc=currentLoc.getNext();
    		}
    		pointer++;
    	}
		
    	while(numNodesLeft!=0){
    		for(int i=0; i<currentIndex; i++){
    			if (inDegrees[i]==0&&enqueuedItems[i]==0){
    				zeroIndegrees.enqueue(nodes[i]);
    				enqueuedItems[i]=-1;
    			}
    		}
		
    		if(zeroIndegrees.isEmpty()==false){
    			Node remove = zeroIndegrees.dequeue();
    			int removedID = remove.getId();
    			topologicalSortArr[topologicalSortLoc]=remove.getId();
    			topologicalSortLoc++;

    			NodeEntry currentLoc = edges[remove.getId()];
    			while(currentLoc!=null){
    				int currentLocID = currentLoc.getNode().getId();
    				inDegrees[currentLocID]--;
    				currentLoc=currentLoc.getNext();
    			}
    		}
    		numNodesLeft--;
    	}
    	return topologicalSortArr;
    }
}
