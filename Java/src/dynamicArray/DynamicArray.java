package dynamicArray;

/**
 * * A resizable array implementation that can grow and shrink dynamically
 * @param <E> the type of elements stored in this dynamic array
 */
public class DynamicArray<E>{
d
  /** the array buffer into which the elements are stored */
  private E[] array;
  /** how many elements we have */
  private int size = 0;
  /** total space available */
  private int capacity = 0;

  /**
   * Constructs a dynamic array with the specified initial capacity.
   *
   * @param capacity the initial capacity of the dynamic array
   * @throws IllegalArgumentException if the specified capacity is negative
   */
  @SuppressWarnings("unchecked")
  public DynamicArray(int capacity) {
    // capacity validation
    if (capacity < 0) {
      throw new IllegalArgumentException("Illegal Capacity: " + capacity);
    }
    // set capacity
    this.capacity = capacity;
    // init array and cast to T type
    this.array = (E[]) new Object[capacity];
  }

  /**
   * Constructs a dynamic array with a default initial capacity of 10.
   */
  public DynamicArray() {
    this(10);
  }

  /**
   * Returns the number of elements in this list
   *
   * @return the number of elements in this list
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns true if this list contains no elements
   *
   * @return true if this list contains no elements
   */
  public boolean isEmpty(){
    return this.size == 0;
  }

  /**
   * Add element to end
   *
   * @param element element to be appended to this list
   */
  @SuppressWarnings("unchecked")
  public boolean add(E element){

    // check if current array is full and needs resizing
    if (size >= capacity) {

      // calculate new capacity
      if (capacity == 0) {
        capacity = 1; // edge case of zero-capacity array
      } else {
        capacity *= 2; // double capacity
      }

      // allocate new array with increased capacity
      E[] newArray = (E[]) new Object[capacity];

      // copy all existing elements from old array to new array
      for (int i = 0; i < size; i++) {
        newArray[i] = array[i];
      }

      // update array reference to point to new larger array
      array = newArray;
    }

    // add new element at end of array and increment size
    array[size++] = element;

    return true;
  }

  /**
   * Retrieve element
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public E get(int index) {
    checkBounds(index);

    return this.array[index];
  }

  /**
   * Replace element
   *
   * @param index index of the element to replace
   * @param element element to be stored at the specified position
   * @return the element previously at the specified position
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public E set(int index, E element) {
    checkBounds(index);

    // store element to return
    E oldElement = this.array[index];

    // replace element
    this.array[index] = element;

    return oldElement;
  }

  /**
   * Remove by index
   * Shifts any subsequent elements to the left
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException if the index is out of range
   */
  public E remove(int index) {
    checkBounds(index);

    // store element to return
    E oldElement = this.array[index];

    // calculate how many elements need to be shifted left
    int numElementsToShift = size - index - 1;

    // shift all elements after the removed index one position left
    if (numElementsToShift > 0) {
      for (int i = index; i < size - 1; i++) {
        array[i] = array[i + 1]; // move element from right to left
      }
    }

    // clear the last element and decrement size
    array[--size] = null;

    return oldElement;
  }

  /**
   * Checks if the given index is within valid bounds
   *
   * @param index the index to check
   * @throws IndexOutOfBoundsException if index is out of bounds
   */
  private void checkBounds(int index) {
    if (index < 0 || index >= size)
      throw new IndexOutOfBoundsException("Index out of bounds");
  }

  public static void main(String[] args) {
    DynamicArray<Integer> array = new DynamicArray<>();

    array.add(1);
    array.add(2);
    array.add(3);
    for (int i = 0; i < array.size; i++) {
      System.out.print(array.get(i) + " ");
    }
    System.out.println();

    array.set(1, 10);
    for (int i = 0; i < array.size; i++) {
      System.out.print(array.get(i) + " ");
    }
    System.out.println();

    array.remove(1);
    for (int i = 0; i < array.size; i++) {
      System.out.print(array.get(i) + " ");
    }
  }

}
