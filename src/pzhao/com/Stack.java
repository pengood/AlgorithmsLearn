package pzhao.com;

import java.util.Iterator;

public class Stack<Item>implements Iterable<Item> {
	private Item[] items;
	private int N;
	private int top;
	public Stack(){
		this(5);
	}
	@SuppressWarnings("unchecked")
	public Stack(int n){
		this.N=n;
		items=(Item[])new Object[N];
	}
	public void push(Item item){
		items[top++]=item;
	}
	
	public Item pop(){
		if(top<=0)return null;
		return items[--top];
	}
	
	public Item peek(){
		return items[top-1];
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Item>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return top>=0;
			}

			@Override
			public Item next() {
				// TODO Auto-generated method stub
				return pop();
			}
		};
	}

	public static void main(String[] args){
		Stack<Integer> stack=new Stack<>(10);
		for(int i=0;i<10;i++){
			stack.push(i);
		}
//		for(int i=0;i<10;i++){
//		System.out.print(stack.pop()+" ");	
//		}
		System.out.print(stack.peek()+" ");	
		Iterator<Integer>iterator=stack.iterator();
		while(iterator.hasNext()){
			System.out.print(iterator.next()+" ");
		}
	}
	
}
