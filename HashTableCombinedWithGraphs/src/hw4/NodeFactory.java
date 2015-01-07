package hw4;

/**
 * Factory used for creating nodes
 */
public final class NodeFactory {
	
    public static Node create(String name) {
        return new NodeImpl(name);
    }

}
