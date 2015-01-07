package hw4;

public class NodeImpl implements Node{
	
	private String nodeName;
	private int nodeID=-1;
	
	NodeImpl (String nodename){
		this.nodeName=nodename;
		this.nodeID=nodeID;
	}

    public String getName(){
    	return nodeName;
    }

    public void setName(String nodename){
    	this.nodeName=nodename;
    }
    
    public int getId(){
    	return nodeID;
    }
    
    public void setId(int nodeID){
    	this.nodeID=nodeID;
    }
   
}
