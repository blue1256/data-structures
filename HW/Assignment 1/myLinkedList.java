
class Node {
	private String data;
	private Node next;
	
	public Node(String data) {
		this.data = data;
		this.next = null;
	}
	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
	public String get() {
		return data;
	}
	public Node next() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
}

public class myLinkedList implements LinkedList {
	private Node head = null;
	private int itIndex=0;

	public Object next() {
		Node tar = this.getNode(itIndex);
		itIndex++;
		return tar;
	}

	public boolean hasNext() {
		if(itIndex<this.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		if(head==null) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		int cnt = 0;
		Node tar = head;
		while(tar!=null) {
			tar=tar.next();
			cnt++;
		}
		return cnt;
	}
	
	private Node getNode(int index) {
		int cnt=0;
		Node tar = head;
		while(cnt<index && tar!=null) {
			tar=tar.next();
			cnt++;
		}
		if(tar==null || cnt!=index) {
			return null;
		} else {
			return tar;
		}
	}

	public Object get(int index) {
		return getNode(index).get();
	}

	public int indexOf(Object elem) {
		int cnt=0;
		String str = (String) elem;
		Node tar = head;
		while(tar!=null && str.equals(tar.get())) {
			tar = tar.next();
			cnt++;
		}
		if(tar==null) {
			return -1;
		} else {
			return cnt;
		}
	}

	public Object remove(int index) {
		int cnt = 0;
		Node tar = head;
		if(index==0) {
			String ret = tar.get();
			head = head.next();
			return ret;
		}
		while(tar!=null&&cnt<index-1) {
			tar = tar.next();
			cnt++;
		}
		String ret = tar.next().get();
		tar.setNext(tar.next().next());
		return ret;
	}

	public void add(int index, Object obj) {
		int cnt = 0;
		Node tar = head;
		if(index<0) {
			return;
		}
		if(tar==null) {
			head = new Node((String)obj);
			return;
		}
		if(index==0) {
			head = new Node((String)obj,head);
			return;
		}
		while(tar!=null&&cnt<index-1) {
			tar = tar.next();
			cnt++;
		}
		Node newNode = new Node((String)obj,tar.next());
		tar.setNext(newNode);
	}

	public String toString() {
		String ret = "{ ";
		Node tar = head;
		while(tar!=null) {
			ret = ret+tar.get()+" ";
			tar = tar.next();
		}
		ret = ret+"}";
		return ret;
	}
	
	public void removeDuplicate() {
		String[] dup = new String[this.size()];
		boolean hasDup = false;
		dup[0] = head.get();
		int dupCnt = 1;
		Node tar = head;
		while(tar!=null&&tar.next()!=null) {
			boolean contains = false;
			for(int i=0;i<dupCnt;i++) {
				if(dup[i].equals(tar.next().get())) {
					contains = true;
					break;
				}
			}
			if(contains) {
				hasDup = true;
				tar.setNext(tar.next().next());
			} else {
				dup[dupCnt] = tar.next().get();
				dupCnt++;
				tar = tar.next();
			}
		}
		if(hasDup) {
			System.out.println("duplicate removed");
		} else {
			System.out.println("there is no duplicate");
		}
	}
	
	public void reverse() {
		Node newHead = null;
		Node tar = head;
		while(tar!=null) {
			Node newNode = new Node(tar.get(),newHead);
			newHead = newNode;
			tar=tar.next();
		}
		head = newHead;
	}
	
}
