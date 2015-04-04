package pzhao.com;

import java.util.Iterator;


/**
 * 
 * 先进先出队列是一种基于先进先出策略的集合类型
 * @author zhaopeng
 *
 * @param <Item>
 */
public class Queue <Item>implements Iterable<Item> {
	private class Node{
		private Item item;
		private Node next;
		public Node(Item item){
			this.item=item;
		}
	}
	private int N;
	private Node first;
	private Node last;
	
	public Queue(){
	}
	
	public boolean isEmpty(){
		return N==0;
	}
	
	public void enqueue(Item item){
		Node oldlast=last;
		last=new Node(item);
		if(isEmpty())first=last;
		else {
			oldlast.next=last;
		}
		N++;
	}
	public Item peek(){
		return first.item;
	}
	
	public Item dequeue(){
		if(isEmpty()) return null;
		Node tmpNode=first;
		first=first.next;
		N--;
		return tmpNode.item;
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Item>() {
			int n=N;
			Node xNode=first;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return n!=0;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				Item item=xNode.item;
				xNode=xNode.next;
				n--;
				return item;
			}
		};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args){
		Queue<Integer> queue=new Queue<>();
		queue.enqueue(134);
		for(int i=0;i<10;i++){
			queue.enqueue(i);
		}
		queue.enqueue(14);
		Iterator<Integer> iterator=queue.iterator();
		while(iterator.hasNext())
		{
			System.out.print(iterator.next()+" ");
		}
		
		System.out.print(queue.dequeue());
	}
	
	
}
