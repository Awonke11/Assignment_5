import java.util.ArrayList;
import java.util.List;

/*
 * An object of this class represents an Employee from the perspective of time keeping.
 */

public class Employee {
    // & Initializing the instance variables
    private String employeeName;
    private String employeeId;
    private CalendarTime recordStart;
    private CalendarTime recordEnd;
    private Shift employeeShift = new Shift(recordStart, recordEnd);
    private List<Shift> shiftListDate = new ArrayList<Shift>();
    private List<Shift> shiftListWeek = new ArrayList<Shift>();

    // & Initializing the Constructor
    public Employee(String name, String uid) {
        this.employeeName = name;
        this.employeeId = uid;
    }

    // & Initializing the methods
    public String name() {
        // ^ Obtain this employee’s name.
        return this.employeeName;
    }

    public String UID() {
        // ^ Obtain this Employee’s ID.
        return this.employeeId;
    }

    public void signIn(Date d, Time t) {
        // ^ Record that this employee has begun a shift on the given date and at the
        // given time.
        this.recordStart = new CalendarTime(d, t);
    }

    public void signOut(Date d, Time t) {
        // ^ Record that this employee has completed a shift on the given date and at
        // the given time.
        this.recordEnd = new CalendarTime(d, t);
    }

    public boolean present() {
        // ^ Determine whether this employee is present i.e. has signed-in and not yet
        // ^ signed-out.
        if (employeeShift.finish() == null) {
            return true;
        }
        return false;
    }

    public boolean worked(Date d) {
        // ^ Determine whether this employee worked a shift that at least partly
        // ^ occurred on the given date.
        if (employeeShift.includesDate(d)) {
            return true;
        }
        return false;
    }

    public boolean worked(Week w) {
        // ^ Determine whether this employee worked at least one shift during the given
        // ^ week.
        if (employeeShift.inWeek(w)) {
            return true;
        }
        return false;
    }

    public List<Shift> get(Date d) {
        // ^ Obtain the shift(s) worked by this employee that at least partly occur on
        // ^ given date.
        if (this.worked(d)) {
            this.shiftListDate.add(employeeShift);
            return this.shiftListDate;
        }
        return this.shiftListDate;
    }

    public List<Shift> get(Week w) {
        // ^ Obtain a list of the shifts worked by this employee during the given week.
        if (this.worked(w)) {
            this.shiftListWeek.add(employeeShift);
            return this.shiftListWeek;
        }
        return this.shiftListWeek;
    }

    public Duration hours(Week w) {
        // ^ Returns the total time (hours and minutes) worked during the given week.
        for (int i = 1; i < this.shiftListWeek.size(); i++) {
            if (this.shiftListWeek.get(i).inWeek(w)) {
                return this.shiftListWeek.get(i).length();
            }
        }
        return null;
    }

}
