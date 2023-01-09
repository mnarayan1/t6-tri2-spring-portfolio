package com.nighthawk.spring_portfolio.mvc.calendar;

/**
 * Simple POJO
 * Used to Interface with APCalendar
 * The toString method(s) prepares object for JSON serialization
 * Note... this is NOT an entity, just an abstraction
 */
class Year {
   private int year;
   private boolean isLeapYear;
   private int dayOfYear;
   private int firstDayOfYear;
   private int numberOfLeapYears;

   // zero argument constructor
   public Year() {
   }

   /* year getter/setters */
   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
      this.setIsLeapYear(year);
   }

   /* isLeapYear getter/setters */
   public boolean getIsLeapYear(int year) {
      return APCalendar.isLeapYear(year);
   }

   private void setIsLeapYear(int year) { // this is private to avoid tampering
      this.isLeapYear = APCalendar.isLeapYear(year);
   }

   /* isLeapYearToString formatted to be mapped to JSON */
   public String isLeapYearToString() {
      return ("{ \"year\": " + this.year + ", " + "\"isLeapYear\": " + this.isLeapYear + " }");
   }

   // day of year getters and setters
   public int getDayOfYear(int month, int day, int year) {
      return APCalendar.dayOfYear(month, day, year);
   }

   public void setDayOfYear(int month, int day, int year) {
      this.dayOfYear = APCalendar.dayOfYear(month, day, year);
   }

   // dayOfYearToString formatted to be mapped to JSON
   public String dayOfYearToString() {
      return (("{ \"year\": " + this.year + ", " + "\"dayOfYear\": " + this.dayOfYear + " }"));
   }

   // day of year getters and setters
   public int getFirstDayOfYear(int year) {
      return APCalendar.firstDayOfYear(year);
   }

   public void setFirstDayOfYear(int year) {
      this.firstDayOfYear = APCalendar.firstDayOfYear(year);
   }

   // dayOfYearToString formatted to be mapped to JSON
   public String firstDayOfYearToString() {
      return (("{ \"year\": " + this.year + ", " + "\"firstDayOfYear\": " + this.firstDayOfYear + " }"));
   }

   // day of year getters and setters
   public int getNumberOfLeapYears(int year1, int year2) {
      return APCalendar.numberOfLeapYears(year1, year2);
   }

   public void setNumberOfLeapYears(int year1, int year2) {
      this.numberOfLeapYears = APCalendar.numberOfLeapYears(year1, year2);
   }

   // dayOfYearToString formatted to be mapped to JSON
   public String numberOfLeapYearsToString() {
      return (("{ \"numerOfLeapYears\": " + this.numberOfLeapYears + " }"));
   }

   // day of week getters and setters
   public int getDayOfWeek(int month, int day, int year) {
      return APCalendar.dayOfWeek(month, day, year);
   }

   public void setDayOfWeek(int month, int day, int year) {
      this.dayOfYear = APCalendar.dayOfWeek(month, day, year);
   }

   // dayOfYearToString formatted to be mapped to JSON
   public String dayOfWeekToString() {
      return (("{ \"year\": " + this.year + ", " + "\"dayOfWeek\": " + this.dayOfYear + " }"));
   }

   /* standard toString placeholder until class is extended */
   public String toString() {
      return isLeapYearToString();
   }

   public static void main(String[] args) {
      Year year = new Year();
      year.setYear(2022);
      System.out.println(year);
   }
}
