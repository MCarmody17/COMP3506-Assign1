import java.util.Iterator;
import java.util.Objects;

public class Hospital3 extends HospitalBase {

    //Hash Table
    public Hospital3() {
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


/*

    public interface Entry<K,V> {
        K getKey( ); // returns the key stored in this entry
        V getValue( ); // returns the value stored in this entry
    }

    public interface Map<K,V> {
        int size( );
        boolean isEmpty( );
        V get(K key);
        V put(K key, V value);
        V remove(K key);
        Iterable<K> keySet( );
        Iterable<V> values( );
        Iterable<Entry<K,V>> entrySet( );
    }

    public abstract class AbstractMap<K,V> implements Map<K,V> {
        public boolean isEmpty( ) { return size( ) == 0; }
        //---------------- nested MapEntry class ----------------
        protected static class MapEntry<K,V> implements Entry<K,V> {
            private K k; // key
            private V v; // value
            public MapEntry(K key, V value) {
                 k = key;
                 v = value;
            }
            // public methods of the Entry interface
            public K getKey( ) { return k; }
            public V getValue( ) { return v; }
            // utilities not exposed as part of the Entry interface
            protected void setKey(K key) { k = key; }
            protected V setValue(V value) {
                 V old = v;
                 v = value;
                 return old;
            }
        } //----------- end of nested MapEntry class -----------

        private class KeyIterator implements Iterator<K> {
            private Iterator<Entry<K,V>> entries = entrySet( ).iterator( ); // reuse entrySet
            public boolean hasNext( ) { return entries.hasNext( ); }
            public K next( ) { return entries.next( ).getKey( ); } // return key!
            public void remove( ) { throw new UnsupportedOperationException( ); }
        }

        private class KeyIterable implements Iterable<K> {
            public Iterator<K> iterator( ) { return new KeyIterator( ); }
        }

        public Iterable<K> keySet( ) { return new KeyIterable( ); }

         // Support for public values method...
         private class ValueIterator implements Iterator<V> {
            private Iterator<Entry<K,V>> entries = entrySet( ).iterator( ); // reuse entrySet
            public boolean hasNext( ) { return entries.hasNext( ); }
            public V next( ) { return entries.next( ).getValue( ); } // return value!
            public void remove( ) { throw new UnsupportedOperationException( ); }
        }
        private class ValueIterable implements Iterable<V> {
            public Iterator<V> iterator( ) { return new ValueIterator( ); }
        }
        public Iterable<V> values( ) { return new ValueIterable( ); }
    }

*/

    public static void main(String[] args) {
        /*
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * The following main method is provided for simple debugging only
         */
        var hospital = new Hospital3();
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
