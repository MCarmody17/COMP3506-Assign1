import java.util.Iterator;
import java.util.Objects;

public class Hospital2 extends HospitalBase {
    //Priority Queue or List
    Hospital2.OrderedLinkedList bookings;

    public Hospital2() {
        this.bookings = new Hospital2.OrderedLinkedList();
    }

    @Override
    public boolean addPatient(PatientBase patient) {
        String[] time = patient.getTime().split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);

        if ( hours != 12 && hours > 8 && hours < 18) {
            //Initial patient, They can be booked whenever
            bookings.addNode(new Patient(patient.getName(), patient.getTime()));
            return true;
        }
        return false;
    }



    //Pseudo code on Page 62

    // Node with data of plane Object - pseudocode from Data Structures and Algorithms in Java, page 126
    class Node {
        Patient patient;
        Hospital2.Node next;

        Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    @Override
    public Iterator<PatientBase> iterator() {
        System.out.println(bookings.iterator());
        return bookings.iterator();
    }

    class OrderedLinkedList {

        Hospital2.Node head;
        int size = 0;

        public void addNode(Patient patient) {
            Hospital2.Node newPatient = new Hospital2.Node(patient);
            Hospital2.Node pointer = head;
            Hospital2.Node previousNodeOfPointer = null;

            // add first - pseudocode from Data Structures and Algorithms in Java, page 127
            if (pointer == null) {
                head = newPatient;
                size++;
                return;
            }

            // insert node at start - pseudocode from Introduction to Algorithms, page 238
            if (newPatient.patient.getTime().compareTo(pointer.patient.getTime()) < 0) {
                newPatient.next = head;
                head = newPatient;
                size++;
                return;
            }

            // list search to find valid position - pseudocode from Introduction to Algoritms, page 239
            while (pointer != null && newPatient.patient.getTime().compareTo(pointer.patient.getTime()) >= 0) {
                previousNodeOfPointer = pointer;
                pointer = pointer.next;

            }

            previousNodeOfPointer.next = newPatient;
            newPatient.next = pointer;
            size++;
            return;
        }




        public boolean presentNode(String patientName) {
            Hospital2.Node pointer = head;

            if (pointer == null) {
                return false;
            }

            // list search to find valid position - pseudocode from Introduction to Algorithms, page 239
            while (pointer != null) {
                if (pointer.patient.getName().equals(patientName)) {
                    return true;
                }
                pointer = pointer.next;
            }

            return false;
        }

        public Iterator<PatientBase> iterator() {
            return  new Iterator<PatientBase>() {
                Hospital2.Node current = head;

                @Override
                public boolean hasNext() {
                    return current!=null;
                }

                @Override
                public PatientBase next() {
                    if(hasNext()){
                        PatientBase patient = current.patient;
                        current = current.next;
                        System.out.println(patient);
                        return patient;
                    }
                    return null;
                }

            };

        }


    }

}

