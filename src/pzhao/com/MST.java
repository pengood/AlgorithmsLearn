package pzhao.com;

import edu.princeton.cs.introcs.In;

class LazyPrimMST{
	private boolean[] marked;
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	
	public LazyPrimMST(EdgeWeightedGraph edgeWeightedGraph){
		marked=new boolean[edgeWeightedGraph.V()];
		pq=new MinPQ<>(edgeWeightedGraph.V());
		mst=new Queue<>();
		visit(edgeWeightedGraph, 0);
		while(!pq.isEmpty()){
			Edge edge=pq.delMin();
			int v=edge.erther(),w=edge.other(v);
			if(marked[v]&&marked[w]) continue;
			mst.enqueue(edge);
			if(!marked[v])visit(edgeWeightedGraph, v);
			if(!marked[w])visit(edgeWeightedGraph, w);
		}
	}
	
	private void visit(EdgeWeightedGraph G,int v){
		marked[v]=true;
		for(Edge e:G.adj(v)){
			if(!marked[e.other(v)]) pq.insert(e);
		}
	}
	
	public Iterable<Edge> edge(){
		return mst;
	}
	
	public double weight(){
		double weight=0.0;
		for(Edge e:edge()){
			weight+=e.weight();
		}
		return weight;
	}
}


public class MST {
	public static void main(String[] args){
		EdgeWeightedGraph graph=new EdgeWeightedGraph(new In("tinyEWG.txt"));
		for(Edge e:graph.edges()){
			System.out.println(e);
		}
		LazyPrimMST mst=new LazyPrimMST(graph);
		System.out.println("MST");
		for(Edge e:mst.edge()){
			System.out.println(e);
		}
		System.out.print(mst.weight());
		
	}
	
	

}
