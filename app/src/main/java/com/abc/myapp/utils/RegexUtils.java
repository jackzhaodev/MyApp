package com.abc.myapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author imjackzhao@gmail.com
 * @date 2019/5/5
 */
public class RegexUtils {
    /**
     * 判断文本的格式是否正确
     */
    public static boolean textFormatIsWrong(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return !matcher.matches();
    }
}
