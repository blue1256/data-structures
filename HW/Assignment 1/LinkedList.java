public interface LinkedList
{ 
   Object next(); 
   boolean hasNext(); 
   public boolean isEmpty();
   public int size();
   public Object get(int index);
   public int indexOf(Object elem);
   public Object remove(int index);
   public void add(int index, Object obj);
   public String toString();
}