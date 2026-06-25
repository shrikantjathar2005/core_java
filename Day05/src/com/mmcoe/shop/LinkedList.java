package com.mmcoe.shop;

public class LinkedList<T> {
	private class Node {
		private T data;
		private Node next;
		
		public Node(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}
	}
		
	private Node head,last;
	private int size;
	
	public int size() {
	    return size;
	}
	
	public void add(T data) {
		Node n = new Node(data);
		if(head == null) {
			head =n;
		} else
			last.next=n;
		last = n;
		size++;
	}
	
	
	public void print() {
		Node t = head;
		while(t != null) {
			System.out.println(t.getData());
			t = t.next;
		}
	}
	
	public void insert(int idx, T data) {
		if(idx < 0 || idx > size) {
			throw new IllegalArgumentException("Index out of bound");
		}
		Node n = new Node(data);
		
		if (idx == 0) {
			n.next = head;
			head = n;
			
			if(last == null)
		        last = n;
			
			size++;
			return;
		}
		
		Node temp = head;
	    for(int i = 0; i < idx - 1; i++)	    {
	        temp = temp.next;
	    }

	    n.next = temp.next;
	    temp.next = n;
	    
	    if(n.next == null) {
	        last = n;
	    }
	    size++;
			
	}
	
	public void remove(int idx) {
		if(idx < 0 || idx >= size) {
		    throw new IllegalArgumentException("Index out of bound");
		}
				
		if (idx == 0) {
			head = head.next;
			
			if(head == null)
		        last = null;

			size--;
			return;
		}
		
		Node temp = head;
		
		for(int i = 0; i < idx-1; i++) {
			temp = temp.next;
		}
		
		if (temp.next == last) {
			last = temp;
		}
		
		temp.next = temp.next.next;		
		size--;
}
}
