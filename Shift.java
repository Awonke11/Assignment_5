/*
 * An object of this class represents a work shift. A shift begins on a given date at a given time and ends on a given date at a given time.
 * Author : Awonke Mnotoza
 * Student number : MNTAWO002
 * Date : 1 September 2022
 */

public class Shift {
    // & Initializing the variables
    private CalendarTime startTime;
    private CalendarTime endTime;

    // & Initializing the Constructor
    public Shift(CalendarTime start, CalendarTime finish) {
        this.startTime = start;
        this.endTime = finish;

        this.toString();
    }

    // & Initializing the methods
    public CalendarTime start() {
        // ^ Obtain the start date and time for this shift.
        return this.startTime;
    }

    public CalendarTime finish() {
        // ^ Obtain the end date and time for this shift
        return this.endTime;
    }

    public boolean inWeek(Week w) {
        // ^ Determine whether this shift occurred within the given week.
        if (w.includes(this.startTime.date()) || w.includes(this.endTime.date())) {
            return true;
        }
        return false;
    }

    public boolean includesDate(Date date) {
        // ^ Determine whether this shift at least partly occurred on the given date.
        if ((date.getMonth() >= this.startTime.date().getMonth())
                && (date.getMonth() <= this.endTime.date().getMonth())) {
            return true;
        }
        return false;
    }

    public Duration length() {
        // ^ Obtain the length of this shift
        return this.endTime.subtract(this.startTime);
    }

    public String toString() {
        // ^ Obtain a string representation of this shift of the form
        // ^ "<date>%:<time>-<date>%:<time>".
        return String.format("%s:%s - %s:%s", this.startTime.date(), this.startTime.time(), this.endTime.date(),
                this.endTime.time());
    }

}
