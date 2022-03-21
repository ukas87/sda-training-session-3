package ui;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private boolean validate(String regex, String checking) {
        Pattern patter = Pattern.compile(regex);
        Matcher matcher = patter.matcher(checking);
        return matcher.find();
    }

    public boolean validateId(UUID checking){
        String pattern = "([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})";
        return validate(pattern, String.valueOf(checking));
    }


    public boolean validateLongitude(String checking) {
        String pattern = "^(\\+|-)?(?:180(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-9][0-9]|1[0-7][0-9])(?:(?:\\.[0-9]{1,6})?))$";
        return validate(pattern,checking);
    }

    public boolean validateLatitude(String checking) {
        String pattern = "^(\\+|-)?(?:90(?:(?:\\.0{1,6})?)|(?:[0-9]|[1-8][0-9])(?:(?:\\.[0-9]{1,6})?))$";
        return validate(pattern, checking);
    }

    public void informationMessage(){
        System.out.println("No parameter added");
    }

    public boolean countryNameValidation(String checking) {
        String pattern = "^[a-zA-Z]";
        return validate(pattern, checking);
    }

    public boolean cityNameValidation(String checking) {
        String pattern = "(^[a-zA-Z])";
        return validate(pattern, checking);
    }

    public boolean regionNameValidation(String checking) {
        String pattern = "(^[a-zA-Z])";
        return validate(pattern, checking);
    }

    public boolean dateValidation(String checking) {
        String pattern = "^\\d{4}-\\d{2}-\\d{2}$";
        return validate(pattern, checking);
    }

}


