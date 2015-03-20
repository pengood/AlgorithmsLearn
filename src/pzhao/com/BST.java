package pzhao.com;

import java.util.NoSuchElementException;


public class BST <Key extends Comparable<Key>,Value>{
	private class Node{ 
		private Key key;
		private Value value;
		private Node left,right;
		private int N;	//节点的子节点数
		
		public Node(Key key,Value value,int n){
			this.key=key;
			this.value=value;
			this.N=n;
		}
		
	}
	
	private Node root;
	
	public int size(){
		return size(root);
	}
	
	public int size(Node node){
		if(node==null)return 0;
		return node.N;
	}
	
	public void put(Key key,Value value){
		root=put(root, key, value);
	}
	
	private Node put(Node xNode,Key key,Value value){
		if(xNode==null) return new Node(key, value, 1);
		int cmp=key.compareTo(xNode.key);
		if(cmp<0)xNode.left=put(xNode.left, key, value);
		if(cmp>0)xNode.right=put(xNode.right,key , value);
		else xNode.value=value;
		xNode.N=xNode.left.N+xNode.right.N+1;
		return xNode;
	}
	
	public Value get(Key key){
		return get(root, key);
	}
	private Value get(Node x,Key key){
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0) return get(x.left,key);
		if(cmp>0)return get(x.right,key);
		return x.value;
	}
	public Key min(){
		return min(root).key;
	}
	
	private Node min(Node x){
		if(x.left==null) return x;
		return min(x.left);
	}
	
	public Key floor(Key key){
		Node xNode=floor(root,key);
		if(xNode==null) return null;
		return xNode.key;
	}
	
	private Node floor(Node x,Key key){
		if(x==null)return null;
		int cmp=key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0) return floor(x.left, key);
		Node t = floor(x.right, key); 
        if (t != null) return t;
        else return x; 
	}
	
	public Key celling(Key key){
		Node xNode=celling(root, key);
		if(xNode==null) return null;
		return xNode.key;
	}
	
	private Node celling(Node x,Key key){
		if(x==null)return null;
		int cmp=key.compareTo(x.key);
		if(cmp==0)return x;
		if(cmp>0) return celling(x.right, key);
		Node tNode=celling(x.left, key);
		if(tNode!=null) return tNode;
		return x;
	}
	
	public Key select(int k){
		if(root.N<=k) return null;
		return select(root, k);
	}
	
	private Key select(Node x, int k){
		if(x==null)return null;
		int t=x.left.N;
		if(k==t)return x.key;
		if(k<t)return select(x.left, k);
		return select(x, k-t-1);
	}
	
	public int rank(Key key){
		return rank(root, key);
	}
	private int rank(Node x,Key key){
		if(x==null)return 0;
		int cmp=key.compareTo(x.key);
		if(cmp==0)return x.left.N;	
		if(cmp<0) return rank(x.left,key);
		return x.left.N+1+rank(x.right,key);
	}
	
	public void delMin(){
		if(root==null)return;
		Node xNode=root,tmpNode=xNode;
		while(xNode.left!=null){
			tmpNode=xNode;
			xNode=xNode.left;
		}
		if(xNode.right!=null)tmpNode.left=xNode.right;
	}
	
	public void delMax(){
		if(root==null)return;
		Node xNode=root,tmpNode=xNode;
		while(xNode.right!=null){
			tmpNode=xNode;
			xNode=xNode.right;
		}
		if(xNode.left!=null)tmpNode.right=xNode.left;
	}
	
    public void deleteMin() {
        if (root==null) throw new NoSuchElementException("Symbol table underflow");
        root = deleteMin(root);
       
    }

    private Node deleteMin(Node x) {
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
	
	public void delete(Key key){
		root = delete(root, key);
	}
	
	private Node delete(Node x,Key key){
		if(x==null) return null;
		int cmp=key.compareTo(x.key);
		if(cmp<0)x.left=delete(x.left, key);
		else if(cmp>0)x.right=delete(x.right, key);
		else {
			if(x.right==null)return x.left;
			if(x.left==null)return x.right;
			Node tNode=x;
			x=min(tNode.right);
			x.right=deleteMin(tNode.right);
			x.left=tNode.left;
		}
		x.N=x.left.N+x.right.N+1;
		return x;
	}
	
}
