package ru.alexey_podusov.workers.utils;

import org.androidannotations.annotations.EFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseUtils {
    private static String FIRST_YEAR_FORMAT_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    private static String LAST_YEAR_FORMAT_REGEX = "\\d{2}-\\d{2}-\\d{4}";


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

}
