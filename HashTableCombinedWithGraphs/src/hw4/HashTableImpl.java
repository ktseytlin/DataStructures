package hw4;

public class HashTableImpl<K,V> implements HashTable <K,V>{

	private static final int tablesize = 1031;
	private HashEntry <K,V> [] hashtable;
	
	public HashTableImpl(){
		hashtable= new HashEntry [tablesize];
	}

	//pre: the value is not already in the list
	public void put(K key, V value){
		int index = Math.abs(key.hashCode() % tablesize);
		if(hashtable[index]!=null){
			hashtable[index]=new HashEntry <K,V> (key, value, hashtable[index]);
		}
		else{
			hashtable[index]=new HashEntry <K,V> (key, value, null);
		}
	}
	
    public V get(K key){
    	int index = Math.abs(key.hashCode() % tablesize);
    	HashEntry <K,V> rowNodes = hashtable[index];
    	if(rowNodes==null){
    		return null;
    	}
    	else{
    		while(rowNodes!=null){
    			if(rowNodes.getKey().equals(key)){
    				V foundvalue=rowNodes.getValue();
    				return foundvalue;
    			}
				rowNodes=rowNodes.getNext();
    		}
    		return null;
    	}

    }
    
    public V remove(K key){
    	int index=Math.abs(key.hashCode() % tablesize);
    	HashEntry <K,V> currentNode = hashtable[index];
    	
    	if(currentNode==null){
    		return null;
    	}
    	else{
    		HashEntry<K,V> previous=null;
    		while (currentNode!=null){
    			if(currentNode.getKey().equals(key)){
    				previous.setNext(currentNode.getNext());
    				return currentNode.getValue();
    			}
    			previous=currentNode;
    			currentNode=currentNode.getNext();
    		}
    	}
    	return null;
    }
	
}
