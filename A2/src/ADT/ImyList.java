package ADT;

public interface ImyList<A> {
    //Appends the specified element to the end of this list.
    public boolean add(A element);

    //Removes all of the elements from this list .
    public void clear();

    //Returns true if this list contains the specified element.
    public boolean contains(A element);

    //Returns true if this list contains no elements.
    public boolean isEmpty();

    //Returns the element at the specified position in this list.
    public A get(int index);

    //Returns the number of elements in this list.
    public int size();

    public String toSting();
}
