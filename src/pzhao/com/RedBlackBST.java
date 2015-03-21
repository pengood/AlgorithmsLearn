package pzhao.com;


public class RedBlackBST <Key extends Comparable<Key>,Value>{
	private static boolean RED=true;
	private static boolean BLACK=false;

	private class Node{
		private Node left,right;
		private int N;
		private boolean color;
		private Key key;
		private Value value;
		
		public Node(Key key,Value value,int n,boolean color){
			this.key=key;
			this.value=value;
			this.N=n;
			this.color=color;
		}
		
	}
	private Node root;
	public int size(){
		return size(root);
	}
	
	private int size(Node x){
		if(x==null)return 0;
		return x.N;
	}
	
	private boolean isRed(Node x){
		if(x==null)return false;
		return x.color==RED;
	}
	private Node rotateLeft(Node h){
		Node xNode=h.right;
		h.right=xNode.left;
		xNode.color=h.color;
		h.color=RED;
		xNode.left=h;
		xNode.N=h.N;
		h.N=size(h.left)+size(h.right)+1;
		return xNode;
		
	}
	
	private Node rotateRight(Node h ){
		Node xNode=h.left;
		h.left=xNode.right;
		xNode.color=h.color;
		h.color=RED;
		xNode.N=h.N;
		xNode.right=h;
		h.N=size(h.left)+size(h.right)+1;
		return xNode;
	}
	
	private void flipColors(Node h){
		h.color=RED;
		h.left.color=BLACK;
		h.right.color=BLACK;
	}
	
	public void put(Key key,Value value){
		root=put(root,key,value);
		root.color=BLACK;
	}
	
	private Node put(Node h,Key key,Value value){
		if(h==null) return new Node(key, value, 1, RED);
		int cmp=key.compareTo(h.key);
		if(cmp<0) h.left=put(h.left, key, value);
		else if(cmp>0)h.right=put(h.right, key, value);
		else h.value=value;
		if(isRed(h.right)&&!isRed(h.left)) h=rotateLeft(h);
		if(isRed(h.left)&&isRed(h.left.left)) h=rotateRight(h);
		if(isRed(h.left)&&isRed(h.right)) flipColors(h);
		h.N=size(h.left)+size(h.right)+1;
		return h;
	}
	public Value get(Key key){
		return get(root, key);
	}
	private Value get(Node x,Key key){
//		if(x==null) return null;
		while(x!=null){
			int cmp=key.compareTo(x.key);
			if(cmp<0) x=x.left;
			else if(cmp>0)x=x.right;
			else return x.value;
		}
		return null;
	}
	
	
	public static void main(String[] argus){
		RedBlackBST<String, Integer> redBlackBST=new RedBlackBST<>();
		String [] a={"S","E","A","R","C"};
		Integer[] b={1,2,3,4,5,6};
		for(int i=0;i<a.length;i++){
			redBlackBST.put(a[i], b[i]);
		}
		System.out.print(redBlackBST.get("R"));
	}
	
}
