package Modeloa.Support;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private static Regex r;

    public Regex(){}

    public static Regex getRegex(){
        if (r==null){
            r = new Regex();
        }
        return r;
    }

    public boolean izenFormatua(String pIzena) {
        return passFullExpression("^[a-z0-9]*$", pIzena);
    }

    public boolean leaderBoardFormatua(String lerroa){
        return passFullExpression("[^;]+;[0-9]+;[0-9\\.]+", lerroa);
    }

    private boolean passFullExpression(String expressionToPass, String expression){
        Pattern pattern = Pattern.compile(expressionToPass, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(expression);
        return match.matches();
    }

    private boolean lookForExpression(String expressionToPass, String expression){
        Pattern pattern = Pattern.compile(expressionToPass, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(expression);
        return match.find();
    }
}
