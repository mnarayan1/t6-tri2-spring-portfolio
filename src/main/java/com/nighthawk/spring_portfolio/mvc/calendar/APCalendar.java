package com.nighthawk.spring_portfolio.mvc.calendar;

// Prototype Implementation
public class APCalendar {
    /**
     * Returns true if year is a leap year and false otherwise.
     * isLeapYear(2019) returns False
     * isLeapYear(2016) returns True
     */
    public static boolean isLeapYear(int year) {
        // implementation not shown
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns the value representing the day of the week
     * 0 denotes Sunday,
     * 1 denotes Monday, ...,
     * 6 denotes Saturday.
     * firstDayOfYear(2019) returns 2 for Tuesday.
     */
    public static int firstDayOfYear(int year) {
        return dayOfWeek(1, 1, year);
    }

    /**
     * Returns n, where month, day, and year specify the nth day of the year.
     * This method accounts for whether year is a leap year.
     * dayOfYear(1, 1, 2019) return 1
     * dayOfYear(3, 1, 2017) returns 60, since 2017 is not a leap year
     * dayOfYear(3, 1, 2016) returns 61, since 2016 is a leap year.
     */
    public static int dayOfYear(int month, int day, int year) {
        // implementation not shown
        int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        if (isLeapYear((year))) {
            daysInMonths[1] = 29;
        }
        if (month == 1) {
            return day;
        } else {
            int sumOfMonths = 0;
            for (int i = 0; i < month - 1; i++) {
                sumOfMonths += daysInMonths[i];
            }
            return sumOfMonths + day;
        }
    }

    /**
     * Returns the number of leap years between year1 and year2, inclusive.
     * Precondition: 0 <= year1 <= year2
     */
    public static int numberOfLeapYears(int year1, int year2) {
        // to be implemented in part (a)
        int count = 0;
        for (int i = year1; i <= year2; i++) {
            if (isLeapYear(i)) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Returns the value representing the day of the week for the given date
     * Precondition: The date represented by month, day, year is a valid date.
     */
    public static int dayOfWeek(int month, int day, int year) {

        int yy = year % 100;
        int yearCode = (yy + (yy / 4)) % 7;

        int[] monthCodes = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
        int monthCode = monthCodes[month - 1];

        int century = Integer.parseInt(Integer.toString(year).substring(0, 2));
        int centuryCodes[] = { 4, 2, 0, 6, 4, 2, 0 };
        int centuryCode = centuryCodes[century - 17];

        int dayOfWeek = yearCode + monthCode + centuryCode + day;
        if (isLeapYear(year)) {
            dayOfWeek--;
        }
        return dayOfWeek % 7;
    }

    /** Tester method */
    public static void main(String[] args) {
        // Private access modifiers
        System.out.println("firstDayOfYear: " + APCalendar.firstDayOfYear(2022));
        System.out.println("dayOfYear: " + APCalendar.dayOfYear(1, 1, 2022));
        // Public access modifiers
        System.out.println("isLeapYear: " + APCalendar.isLeapYear(2022));
        System.out.println("numberOfLeapYears: " + APCalendar.numberOfLeapYears(2000, 2022));
        System.out.println("dayOfWeek: " + APCalendar.dayOfWeek(1, 1, 2022));
    }
}
