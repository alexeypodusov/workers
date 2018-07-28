package ru.alexey_podusov.workers.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class ResponseUtils {
    private final static String FIRST_YEAR_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private final static String LAST_YEAR_FORMAT_REGEX = "\\d{2}-\\d{2}-\\d{4}";


    /**
     * Приведение строки к виду: первая буква заглавная, остальные строчные
     */
    public static String toFirstUpperCase(String string) {
        String lowerCaseName = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + lowerCaseName.substring(1);
    }

    /**
     * Приведение даты в нужный формат
     *
     * @param dateString возможные форматы: "dd-mm-yyyy" или "yyyy-mm-dd"
     * @return дата в формате "dd.mm.yyyy", если dateString входит в список возможных входных форматов, иначе "-"
     */
    public static String toNeedFormatDate(String dateString) {
        SimpleDateFormat firstYearFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat lastYearFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat lastYearWithDotsFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            if (Pattern.compile(FIRST_YEAR_FORMAT_REGEX).matcher(dateString).matches()) {
                Date date = firstYearFormat.parse(dateString);
                return lastYearWithDotsFormat.format(date);

            }

            if (Pattern.compile(LAST_YEAR_FORMAT_REGEX).matcher(dateString).matches()) {
                Date date = lastYearFormat.parse(dateString);
                return lastYearWithDotsFormat.format(date);

            }

            return "-";
        } catch (Exception e) {
            return "-";
        }
    }

    /**
     * @param stringDate в виде "dd.MM.yyyy"
     * @return возраст, если stringDate другого формата, то вернет "-"
     */
    public static String getAgeByStringDate(String stringDate) {
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("dd.MM.yyyy").parse(stringDate);
        } catch (ParseException e) {
            return "-";
        }

        Calendar birthdateCalendar = Calendar.getInstance();
        birthdateCalendar.setTime(birthdate);
        birthdateCalendar.add(Calendar.DAY_OF_MONTH, -1);

        Calendar currentDateCalendar = Calendar.getInstance();

        int age = currentDateCalendar.get(Calendar.YEAR) - birthdateCalendar.get(Calendar.YEAR);
        if (currentDateCalendar.get(Calendar.DAY_OF_YEAR) <= birthdateCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return String.valueOf(age);
    }
}
