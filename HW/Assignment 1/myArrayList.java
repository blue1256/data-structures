import java.util.*;

public class myArrayList implements ArrayList {
	private Object[] data;
	private int size = 0;

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		if(index>=0 && index<size) {
			return data[index];
		} else {
			return null;
		}
	}

	public int indexOf(Object elem) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals((String) elem)) {
				return i;
			}
		}
		return -1;
	}

	public Object remove(int index) {
		if (index >= 0 && index < size) {
			String ret = (String)data[index];
			for (int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			data[size-1] = null;
			size--;
			return ret;
		} else {
			return null;
		}
	}

	public void add(int index, Object obj) {
		if (index < 0 || index > size) {
			return;
		}
		if (data == null) {
			data = new String[10];
		}
		if (data.length == size) {
			data = Arrays.copyOf(data, data.length*3/2);
		}
		for (int i = size-1; i >= index; i--) {
			data[i+1]=data[i];
		}
		data[index] = (String) obj;
		size++;
	}

	public String toString() {
		String ret = "{ ";
		for(int i=0;i<size;i++) {
			ret = ret + data[i] + " ";
		}
		ret = ret + "}";
		return ret;
	}
}
