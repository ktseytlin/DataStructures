package hw4;

public class HashEntry <K, V> {
	
	private K key;
	private V value;
	private HashEntry <K, V> next;
	
	HashEntry (K key, V value, HashEntry<K,V> next){
		this.key=key;
		this.value=value;
		this.next=next;
	}
	
	public V getValue(){
		return value;
	}
	
	public void setValue(V value){
		this.value=value;
	}

	public K getKey(){
		return key;
	}
	
	public HashEntry <K,V> getNext(){
		return next;
	}
	
	public void setNext(HashEntry <K,V> next){
		this.next=next;
	}
}
