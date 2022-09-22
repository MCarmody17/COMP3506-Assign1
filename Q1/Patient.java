public class Patient extends PatientBase {

    public Patient(String name, String time) {
        super(name, time);
    }

    @Override
    public int compareTo(PatientBase o) {
        String appointmentTime = this.getTime();
        String comparisonTime = o.getTime();
        int timeComparison = appointmentTime.compareTo(comparisonTime);

        // compares appointments based on their time
        if (timeComparison > 0) {
            return 1;
        } else if (timeComparison < 0) {
            return -1;
        }
        return 0;
    }

    /* Add any extra functions below */
}
