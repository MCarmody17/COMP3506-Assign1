import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;

public class Hospital2 extends HospitalBase {
    //Priority Queue or List
    public Hospital2() {
        /* Add your code here! */
    }

    @Override
    public boolean addPatient(PatientBase patient) {
        /* Add your code here! */
        return false;
    }

    @Override
    public Iterator<PatientBase> iterator() {
        /* Add your code here! */
        return null;
    }

    /* Add any extra functions below */
    public interface Entry<K,V> {
        K getKey( ); // returns the key stored in this entry
        V getValue( ); // returns the value stored in this entry
    }

    public interface PriorityQueue<K,V> {
        int size( );
        boolean isEmpty( );
        Entry<K,V> insert(K key, V value) throws IllegalArgumentException;
        Entry<K,V> min( );
        Entry<K,V> removeMin( );
    }

    public class DefaultComparator<E> implements Comparator<E> {
        public int compare(E a, E b) throws ClassCastException {
             return ((Comparable<E>) a).compareTo(b);
        }
    }

     public abstract class AbstractPriorityQueue<K,V>
        implements PriorityQueue<K,V> {
         //---------------- nested PQEntry class ----------------
            protected static class PQEntry<K,V> implements Entry<K,V> {
                private K k; // key
                private V v; // value
                public PQEntry(K key, V value) {
                                 k = key;
                                 v = value;
                                 }
                // methods of the Entry interface
                public K getKey( ) { return k; }
                public V getValue( ) { return v; }
                 // utilities not exposed as part of the Entry interface
                protected void setKey(K key) { k = key; }
                protected void setValue(V value) { v = value; }
            } //----------- end of nested PQEntry class -----------

             // instance variable for an AbstractPriorityQueue
             // The comparator defining the ordering of keys in the priority queue. ∗/
             private Comparator<K> comp;
             // Creates an empty priority queue using the given comparator to order keys. ∗/
             protected AbstractPriorityQueue(Comparator<K> c) { comp = c; }
             // Creates an empty priority queue based on the natural ordering of its keys. ∗/
             protected AbstractPriorityQueue( ) { this(new DefaultComparator<K>( )); }
             //Method for comparing two entries according to key ∗/
             protected int compare(Entry<K,V> a, Entry<K,V> b) {
                 return comp.compare(a.getKey( ), b.getKey( ));
             }
            // Determines whether a key is valid. ∗/
            protected boolean checkKey(K key) throws IllegalArgumentException {
                try {
                    return (comp.compare(key, key) == 0); // see if key can be compared to itself
                } catch (ClassCastException e) {
                    throw new IllegalArgumentException("Incompatible key");
                }
            }
            // Tests whether the priority queue is empty. ∗/
            public boolean isEmpty( ) { return size( ) == 0; }
        }



    public static void main(String[] args) {
        /*
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * The following main method is provided for simple debugging only
         */
        var hospital = new Hospital2();
        var p1 = new Patient("Max", "11:00");
        var p2 = new Patient("Alex", "13:15");
        var p3 = new Patient("George", "14:00");
        hospital.addPatient(p1);
        hospital.addPatient(p2);
        hospital.addPatient(p3);
        var patients = new Patient[] {p1, p2, p3};
        int i = 0;
        for (var patient : hospital) {
            assert Objects.equals(patient, patients[i++]);
        }
    }
}

