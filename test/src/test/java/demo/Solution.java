package demo;

import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int getMinimumCostToConstruct(int numTotalAvailableCities,
                                  int numTotalAvailableRoads,
                                  List<List<Integer>> roadsAvailable,
                                  int numNewRoadsConstruct,
                                  List<List<Integer>> costNewRoadsConstruct)
    {
        // WRITE YOUR CODE HERE
        SimpleGraph g = new SimpleGraph(numTotalAvailableCities);
        for (int i = 0; i < roadsAvailable.size(); ++i) {
            g.addEdge(roadsAvailable.get(i).get(0),
                    roadsAvailable.get(i).get(1), 0);
        }
        for (int i = 0; i < costNewRoadsConstruct.size(); ++i) {
            g.addEdge(costNewRoadsConstruct.get(i).get(0),
                    costNewRoadsConstruct.get(i).get(1),
                    costNewRoadsConstruct.get(i).get(2));
        }
        List<SimpleEdge> mstedges = g.getMST(g);
        int ret = 0;
        for (SimpleEdge se : mstedges) {
            ret += se.weight;
        }
        return ret;
    }
    // METHOD SIGNATURE ENDS
    class SimpleEdge {
        public SimpleVertex src;
        public SimpleVertex dest;
        public int weight;

        public SimpleEdge(SimpleVertex src, SimpleVertex dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }
    class SimpleVertex {
        public int weight;
        public int data;
        public List<SimpleEdge> edges;

        public SimpleVertex(int data) {
            this.data = data;
            this.weight = Integer.MAX_VALUE;
            this.edges = new ArrayList<>();
        }
    }
    class SimpleGraph {

        private Map<Integer, SimpleVertex> vertices = new HashMap<>();
        public int numVertices;

        public SimpleGraph(int numVertices) {
            this.numVertices = numVertices;
        }

        public void addEdge(int x, int y, int weight) {
            SimpleVertex src = null;
            if (vertices.containsKey(x)) {
                src = vertices.get(x);
            }
            else {
                src = new SimpleVertex(x);
                vertices.put(x, src);
            }
            SimpleVertex dest = null;
            if (vertices.containsKey(y)) {
                dest = vertices.get(y);
            }
            else {
                dest = new SimpleVertex(y);
                vertices.put(y, dest);
            }
            SimpleEdge e = new SimpleEdge(src, dest, weight);
            src.edges.add(e);
        }

        public List<SimpleVertex> getAllVertices() {
            List<SimpleVertex> ret = new ArrayList<>();
            for (SimpleVertex v : vertices.values()) {
                ret.add(v);
            }
            return ret;
        }

        public List<SimpleEdge> getMST(SimpleGraph g) {
            Map<SimpleVertex, SimpleEdge> vToE = new HashMap<>();
            List<SimpleEdge> ret = new ArrayList<>();

            Queue<SimpleVertex> q = new LinkedList<SimpleVertex>();

            for (SimpleVertex v : getAllVertices()) {
                q.add(v);
            }

            SimpleVertex firstVertex = getAllVertices().get(0);
            firstVertex.weight = 0;

            while (q.isEmpty()) {
                SimpleVertex cur = q.remove();

                if (vToE.containsKey(cur)) {
                    ret.add(vToE.get(cur));
                }

                for (SimpleEdge e : cur.edges) {
                    SimpleVertex nxt = getVertexByEdge(cur, e);
                    if (q.contains(nxt) && nxt.weight > e.weight) {
                        nxt.weight = e.weight;
                        q.add(nxt);
                        vToE.put(nxt, e);
                    }
                }
            }
            return ret;
        }

        public SimpleVertex getVertexByEdge(SimpleVertex v, SimpleEdge e) {
            if (e.src.equals(v)) {
                return e.dest;
            }
            else {
                return e.src;
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
    }
}