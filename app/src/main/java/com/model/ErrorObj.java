package com.model;

import com.configuration.Config;
import com.service.UserService;

//import dao.DAOFactory;
//import dao.UserControllerDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ErrorObj {

    private StringBuilder errorText = new StringBuilder("<font color='red'><ul>");
    private boolean isError = false;

    public boolean checkForMistakes(String email, String password, String passwordConfirmation, String location, String gender) {
        Pattern patternEmail = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\\.[a-z]{2,}$");
        Matcher matcherEmail = patternEmail.matcher(email);

        Pattern patternPass = Pattern.compile("^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,20}$");
        Matcher matcherPass = patternPass.matcher(password);

//        DAOFactory factory = DAOFactory.getFactory(1);
//        UserControllerDAO userControllerDAO = factory.getUserControllerDAO();

        ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = ctx.getBean(UserService.class);


//		if (userControllerDAO.checkEmail(email)){
        if (userService.checkEmail(email)) {
            appendErrorText("SUCH EMAIL ALREADY EXISTS");
        }

        if (email == null || email.isEmpty() || !matcherEmail.matches()) {
            appendErrorText("incorrect email");
            setError(true);

        }
        if (password == null || password.isEmpty() || !matcherPass.matches()) {
            appendErrorText("incorrect password");
            setError(true);
        }
        if (passwordConfirmation == null || passwordConfirmation.isEmpty() || !passwordConfirmation.equals(password)) {
            appendErrorText("incorrect password confirmation");
            setError(true);
        }
        if (location == null || location.isEmpty()) {
            appendErrorText("choose your location");
            setError(true);
        }
        if (gender == null || gender.isEmpty()) {
            appendErrorText("who are you?");
            setError(true);
        }
        return isError();
    }

    public StringBuilder getErrorText() {
        return errorText.append("</ul></font>");
    }

    public void appendErrorText(String errorText) {
        this.errorText.append("<li>" + errorText + "</li>");
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean isError) {
        this.isError = isError;
    }


}
