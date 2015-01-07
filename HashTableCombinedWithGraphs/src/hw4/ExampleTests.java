package hw4;

/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests {

    private static interface Animal {
        public String speak();
    }
    private static class Dog implements Animal {
        @Override
        public String speak() {
            return "woof";
        }
    }

    private static class Cat implements Animal {
        @Override
        public String speak() {
            return "meow";
        }
    }

    public static void main(String[] args) {

        // Hash table
        HashTable<String, Animal> animals = HashTableFactory.create();
        animals.put("dog", new Dog());
        animals.put("cat", new Cat());
        System.out.println("Expecting 'woof': " + animals.get("dog").speak());
        System.out.println("Expecting 'meow': " + animals.get("cat").speak());

        // Graph building
        Graph graph = GraphFactory.create(7);
        Node a = NodeFactory.create("a");
        Node b = NodeFactory.create("b");
        Node c = NodeFactory.create("c");
        Node d = NodeFactory.create("d");
        Node e = NodeFactory.create("e");
        Node f = NodeFactory.create("f");
        Node g = NodeFactory.create("g");
        graph.addNodes(a, b, c, d, e, f, g);
        graph.addEdge(a, b);
        graph.addEdge(a, c);
        graph.addEdge(a, d);
        graph.addEdge(b, d);
        graph.addEdge(b, e);
        graph.addEdge(d, c);
        graph.addEdge(d, f);
        graph.addEdge(d, g);
        graph.addEdge(e, d);
        graph.addEdge(e, g);
        graph.addEdge(c, f);
        graph.addEdge(g, f);
        System.out.println("Expecting unique ids from nodes c, b, a:");
        System.out.println("ID for d: " + graph.lookupNode("d").getId());
        System.out.println("ID for c: " + graph.lookupNode("c").getId());
        System.out.println("ID for b: " + graph.lookupNode("b").getId());
        System.out.println("ID for a: " + graph.lookupNode("a").getId());
        System.out.println("ID for 1: " + graph.lookupNode(1).getName());
        // Graph analysis
        System.out.println("Expecting an acyclic graph with sorted output: a, b, c");
        graph.analyze();
        //graph.sort();
        
        System.out.println("_______________");
        
        Graph graph2 = GraphFactory.create(3);
        Node x = NodeFactory.create("x");
        Node y = NodeFactory.create("y");
        Node z = NodeFactory.create("z");
        graph2.addNodes(x,y,z);
        graph2.addEdge(x, y);
        graph2.addEdge(y, z);
        graph2.addEdge(z, x);
        //graph2.sort();
        graph2.analyze();
        
        System.out.println("_______________");
        //issue with the case when there is 1 or 2 nodes in cyclic
        
        Graph graph3 = GraphFactory.create(9);
        Node h = NodeFactory.create("h");
        Node i = NodeFactory.create("i");
        Node j = NodeFactory.create("j");
        Node k = NodeFactory.create("k");
        Node l = NodeFactory.create("l");
        Node m = NodeFactory.create("m");
        Node n = NodeFactory.create("n");
        Node o = NodeFactory.create("o");
        Node p = NodeFactory.create("p");
        graph3.addNodes(h, i, j, k, l, m, n, o, p);
        graph3.addEdge(h, i);
        graph3.addEdge(i, j);
        graph3.addEdge(j, l);
        graph3.addEdge(k, l);
        graph3.addEdge(k, h);
        graph3.addEdge(j, m);
        graph3.addEdge(l, n);
        graph3.addEdge(m, p);
        graph3.addEdge(n, o);
        graph3.addEdge(p, o);
        System.out.println("ID for j: " + graph3.lookupNode("j").getId());
        System.out.println("ID for k: " + graph3.lookupNode("k").getId());
        System.out.println("ID for l: " + graph3.lookupNode("l").getId());
        System.out.println("ID for m: " + graph3.lookupNode("m").getId());
        System.out.println("ID for 1: " + graph3.lookupNode(1).getName());
        graph3.analyze();
    }

}
