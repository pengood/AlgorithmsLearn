package pzhao.com;



class DepthFirstOrder{
	private boolean[] marked;
	private Queue<Integer> preQueue;
	private Queue<Integer> postQueue;
	private Stack<Integer> reversePost;
	
	public DepthFirstOrder(Digraph g){
		marked=new boolean[g.V()];
		preQueue=new Queue<>();
		postQueue=new Queue<>();
		reversePost=new Stack<>(g.V());
		for(int s=0;s<g.V();s++){
			if(!marked[s])
				dfs(g, s);
		}
	}
	
	private void dfs(Digraph digraph,int s){
		preQueue.enqueue(s);
		marked[s]=true;
		for(int v:digraph.adj(s)){
			if(!marked[v])
				dfs(digraph, v);
		}
		postQueue.enqueue(s);
		reversePost.push(s);
	}
	
	public Iterable<Integer> pre(){
		return preQueue;
	}
	public Iterable<Integer>post(){
		return postQueue;
	}
	public Iterable<Integer>reversePost(){
		return reversePost;
	}
	
}

public class Topological {

	private Iterable<Integer> order;
	 public Topological(Digraph G) {
	        DirectedCycle finder = new DirectedCycle(G);
	        if (!finder.hasCycle()) {
	            DepthFirstOrder dfs = new DepthFirstOrder(G);
	            order = dfs.reversePost();
	        }
	    }
	 public Iterable<Integer> order() {
	        return order;
	    }
	 public boolean hasOrder() {
	        return order != null;
	    }
}
