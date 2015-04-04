package pzhao.com;

import java.util.Iterator;

/**
 * 栈是一种基于先进后出策略的集合类型
 * @author zhaopeng
 *
 * @param <Item>
 */
public class Stack<Item>implements Iterable<Item> {
	private Item[] items;
	private int N;
	private int top;
	public Stack(){
		this(10);
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
	
	public String toString(){
		StringBuilder sb=new StringBuilder();
		for(int i=top-1;i>=0;i--){
			sb.append(items[i]+" ");
		}
		return sb.toString();
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<Item>() {

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return top>0;
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
	//	System.out.println(stack);
		//System.out.print(stack.peek()+" ");	
//		Iterator<Integer>iterator=stack.iterator();
//		while(iterator.hasNext()){
//	//		System.out.print(iterator.next()+" ");
//		}
		System.out.println();
		for(int i:stack){
			System.out.print(i+" ");
		}
	}
	
}
