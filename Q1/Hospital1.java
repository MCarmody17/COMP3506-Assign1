import java.util.Iterator;
import java.util.Objects;

public class Hospital1 extends HospitalBase {
    OrderedLinkedList bookings;


    public Hospital1() {
        this.bookings = new OrderedLinkedList();
    }

    @Override
    public boolean addPatient(PatientBase patient) {
        String[] time = patient.getTime().split(":");
        int hours = Integer.parseInt(time[0]);
        int minutes = Integer.parseInt(time[1]);

        if (minutes == 0 || minutes == 20 || minutes == 40 && hours != 12 && hours > 8 && hours < 18) {
            //Initial patient, They can be booked whenever
            bookings.addNode(new Patient(patient.getName(), patient.getTime()));
            return true;
        }
        return false;
    }


    /* Add any extra functions below */



    // Node with data of plane Object - pseudocode from Data Structures and Algorithms in Java, page 126
    class Node {
        Patient patient;
        Node next;

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

        Node head;
        int size = 0;

        public void addNode(Patient patient) {
            Node newPatient = new Node(patient);
            Node pointer = head;
            Node previousNodeOfPointer = null;

            // add first - pseudocode from Data Structures and Algorithms in Java, page 127
            if (pointer == null) {
                head = newPatient;
                size++;
                return;
            }

            // insert node at start - pseudocode from Introduction to Algorithms, page 238
            if (newPatient.patient.getTime().compareTo(pointer.patient.getTime()) <= 0) {
                if(newPatient.patient.getTime().compareTo(pointer.patient.getTime()) == 0){
                    return;
                }
                newPatient.next = head;
                head = newPatient;
                size++;
                return;
            }

            // list search to find valid position - pseudocode from Introduction to Algoritms, page 239
            while (pointer != null && newPatient.patient.getTime().compareTo(pointer.patient.getTime()) >= 0) {
                if(newPatient.patient.getTime().compareTo(pointer.patient.getTime()) == 0){
                    return;
                }
                previousNodeOfPointer = pointer;
                pointer = pointer.next;
            }

            previousNodeOfPointer.next = newPatient;
            newPatient.next = pointer;
            size++;
            return;
        }



        public boolean validTimeDifference(String currentTime, String earliestPatientTime) {

            String[] currTime = currentTime.split(":");
            String[] earliestTime = earliestPatientTime.split(":");

            String cTimeHours = currTime[0];
            String cTimeMinutes = currTime[1];
            String eTimeHours = earliestTime[0];
            String eTimeMinutes = earliestTime[1];

            int currentTimeHours;
            int currentTimeMinutes;
            int earliestTimeHours;
            int earliestTimeMinutes;

            if (cTimeHours.charAt(0) == '0') {
                currentTimeHours = Integer.parseInt(Character.toString(cTimeHours.charAt(1)));
            } else {
                currentTimeHours = Integer.parseInt(cTimeHours);
            }

            if (cTimeMinutes.charAt(0) == '0') {
                currentTimeMinutes = Integer.parseInt(Character.toString(cTimeMinutes.charAt(1)));
            } else {
                currentTimeMinutes = Integer.parseInt(cTimeMinutes);
            }

            if (eTimeHours.charAt(0) == '0') {
                earliestTimeHours = Integer.parseInt(Character.toString(eTimeHours.charAt(1)));
            } else {
                earliestTimeHours = Integer.parseInt(eTimeHours);
            }

            if (eTimeMinutes.charAt(0) == '0') {
                earliestTimeMinutes = Integer.parseInt(Character.toString(eTimeMinutes.charAt(1)));
            } else {
                earliestTimeMinutes = Integer.parseInt(eTimeMinutes);
            }

            if (currentTimeHours <= earliestTimeHours && (earliestTimeMinutes - currentTimeMinutes <= 5)) {
                return true;
            }

            return false;
        }



        public boolean presentNode(String patientName) {
            Node pointer = head;

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
                Node current = head;

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

    public static void main(String[] args) {
        /*
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * REMOVE THE MAIN METHOD BEFORE SUBMITTING TO THE AUTOGRADER
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * The following main method is provided for simple debugging only
         */
        var hospital = new Hospital1();
        var p1 = new Patient("Max", "11:00");
        var p2 = new Patient("Alex", "12:00");
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



