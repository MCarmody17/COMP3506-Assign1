import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;

public class Hospital1 extends HospitalBase {
    ArrayList bookings = new ArrayList();

    public Hospital1() {
        /* Add map, key is the time, value is the patient details,
            We want to make sure no overlap, so first we check to make
            sure the key contains 20, 40, or 60 and not in the lunch break.
            then we check if it's in the map. If not we add and success, if it is, unlucky.
         */

    }

    @Override
    public boolean addPatient(PatientBase patient) {
        String[] time = patient.getTime().split(":");
        int hours = Integer.parseInt(time[0]);
        String minsString = time[1];
        int minutes = Integer.parseInt(time[1]);
        String patientName = patient.getName();


        if (minsString.equals("00") || minsString.equals("20") || minsString.equals("40")) {
            /*Add to the Bookings ArrayList with TIME as KEY, and NAME as VALUE.
               This way we can check if exists
            */
            for (int i = 0; i < bookings.size(); i++) {
                Patient tempPatient = (Patient) bookings.get(i);
                String[] patientTime = patient.getTime().split(":");
                int tempHours = Integer.parseInt(patientTime[0]);
                int tempMins = Integer.parseInt(patientTime[1]);
                if (hours <= tempHours && minutes < tempMins) {
                    bookings.add(i, patient);
                    return true;
                }

            }

        }

        return false;
    }


    @Override
    public Iterator<PatientBase> iterator() {
        /* Add your code here! */
        return bookings.iterator();
    }

    /* Add any extra functions below */


    public static void main(String[] args) {
        /*
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * The following main method is provided for simple debugging only
         */
        var hospital = new Hospital1();
        var p1 = new Patient("Max", "11:00");
        var p2 = new Patient("Alex", "13:00");
        var p3 = new Patient("George", "14:00");
        hospital.addPatient(p1);
        hospital.addPatient(p2);
        hospital.addPatient(p3);
        var patients = new Patient[]{p1, p2, p3};
        int i = 0;
        for (var patient : hospital) {
            if (!Objects.equals(patient, patients[i++])) {
                System.err.println("Wrong patient encountered, check your implementation!");
            }
        }
    }
}


// List Interface

interface List<E> {
    // Returns the number of elements in this list. ∗/
    int size();

    // Returns whether the list is empty. ∗/
    boolean isEmpty();

    // Returns (but does not remove) the element at index i. ∗/
    E get(int i) throws IndexOutOfBoundsException;

    // Replaces the element at index i with e, and returns the replaced element. ∗/
    E set(int i, E e) throws IndexOutOfBoundsException;

    // Inserts element e to be at index i, shifting all subsequent elements later. ∗/
    void add(int i, E e) throws IndexOutOfBoundsException;

    // Removes/returns the element at index i, shifting subsequent elements earlier. ∗/
    E remove(int i) throws IndexOutOfBoundsException;
}


// Basic Array List

class ArrayList<E> implements List<E> {
    // instance variables
    public static final int CAPACITY = 16; // default array capacity
    private E[] data; // generic array used for storage
    private int size = 0; // current number of elements

    protected void resize(int capacity) {
        E[] temp = (E[]) new Object[capacity]; // safe cast; compiler may give warning
        for (int k = 0; k < size; k++)
            temp[k] = data[k];
        data = temp; // start using the new array
    }

    // constructors
    public ArrayList() {
        this(CAPACITY);
    } // constructs list with default capacity

    public ArrayList(int capacity) { // constructs list with given capacity
        data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
    }

    // public methods
    /* Returns the number of elements in the array list. **/
    public int size() {
        return size;
    }

    //Returns whether the array list is empty. ∗/
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns (but does not remove) the element at index i. ∗/
    public E get(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        return data[i];
    }

    // Replaces the element at index i with e, and returns the replaced element. ∗/
    public E set(int i, E e) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        data[i] = e;
        return temp;
    }

    // Inserts element e to be at index i, shifting all subsequent elements later. ∗/
    public void add(int i, E e) throws IndexOutOfBoundsException,
            IllegalStateException {
        checkIndex(i, size + 1);
        checkIndex(i, size + 1);
        if (size == data.length) { // not enough capacity
            resize(2 * data.length);
        }
        for (int k = size - 1; k > i; k--) {
            data[k + 1] = data[k];
        }
        data[i] = e; // ready to place the new element
        size++;
    }

    // Removes/returns the element at index i, shifting subsequent elements earlier. ∗/
    public E remove(int i) throws IndexOutOfBoundsException {
        checkIndex(i, size);
        E temp = data[i];
        for (int k = i; k < size - 1; k++) {
            data[k] = data[k + 1];
        }
        data[size - 1] = null;
        size--;
        return temp;
    }

    // utility method
    // Checks whether the given index is in the range [0, n−1]. ∗/
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException {
        if (i < 0 || i >= n)
            throw new IndexOutOfBoundsException("Illegal index: " + i);
    }



    private class ArrayIterator implements Iterator<E> {
        private int j = 0; // index of the next element to report
        private boolean removable = false; // can remove be called at this time?
        /**
         * ∗ Tests whether the iterator has a next object.
         * ∗ @return true if there are further objects, false otherwise
         */
        public boolean hasNext() throws NullPointerException{
            return j < size;
        } // size is field of outer instance

        /**
         * ∗ Returns the next object in the iterator.
         * ∗
         * ∗ @return next object
         * ∗ @throws NoSuchElementException if there are no further elements
         */
        public E next() throws NoSuchElementException {
            if (j == size) throw new NoSuchElementException("No next element");
            removable = true; // this element can subsequently be removed
            return data[j++]; // post-increment j, so it is ready for future call to next
        }

    } //------------ end of nested ArrayIterator class ------------
    /**
     * Returns an iterator of the elements stored in the list.
     */
    public Iterator<E> iterator() {
        return new ArrayIterator(); // create a new instance of the inner class
    }

}


