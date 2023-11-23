package base.message;

public class ErrorMessage {
    public final static String SYSTEM_ERROR = "System error. Please try again";

    public final static String AUTHENTICATION_ERROR = "Authentication failed";

    public final static String REGISTER_ERROR_01 = "Enroll the company failed, please try again";
    public final static String REGISTER_ERROR_02 = "Register user failed, please try again";
    public final static String REGISTER_ERROR_03 = "Please select company";

    public final static String LOGIN_ERROR_01 = "Email/phone or password is wrong, please try again.";
    public final static String LOGIN_ERROR_02 = "Email or phone have not registered in the system. Please registration and try again";

    public final static String RESET_PASSWORD_ERROR_01 = "Your new password and confirm password is not match, please try again.";
    public final static String RESET_PASSWORD_ERROR_02 = "Current password is wrong, please try again.";


    public final static String USER_DASHBOARD_ERROR_01 = "Cannot find user in the system, please try again.";
    public final static String USER_DASHBOARD_ERROR_02 = "The public key or userId cannot empty";
    public final static String USER_DASHBOARD_ERROR_03 = "The user dont exist in the system";
    public final static String USER_DASHBOARD_ERROR_04 = "Invalid format public key";
    public final static String USER_DASHBOARD_ERROR_05 = "The service group cannot empty";

    public final static String ADMIN_DASHBOARD_ERROR_01 = "The userId cannot empty";
    public final static String ADMIN_DASHBOARD_ERROR_02 = "The user dont exist in the system";

    public final static String TECHNICAL_ERROR_01 = "The technical dont exist";

    public final static String FORMAT_EMAIL_PHONE_ERROR = "Invalid email or phone! Please enter again.";
    //Email
    public final static String EMAIL_ERROR_01 = "Email is mandatory";
    public final static String EMAIL_ERROR_02 = "Invalid email! Please enter again.";
    public final static String EMAIL_ERROR_03 = "This email have existed in the system";

    // Phone
    public final static String PHONE_ERROR_01 = "Your phone is mandatory";
    public final static String PHONE_ERROR_02 = "Invalid phone! Please enter again.";
    public final static String PHONE_ERROR_03 = "This phone have existed in the system, please enter an another phone!";

    // OTP
    public final static String OTP_ERROR_01 = "OTP is mandatory";
    public final static String OTP_ERROR_02 = "Just allow enter six number digits";

    // Password
    public final static String PASSWORD_ERROR_01 = "Invalid password! Please enter again.";

    //User
    public final static String USER_ERROR_01 = "UserId is mandatory";
    public final static String USER_ERROR_02 = "Your name is mandatory";
    public final static String USER_ERROR_03 = "Your country phone is mandatory";
    public final static String USER_ERROR_04 = "Service list is not empty";
    public final static String USER_ERROR_05 = "Company type is mandatory";

    //Check token
    public final static String TOKEN_INVALID_ERROR_CODE = "invalid_token";
    public final static String TOKEN_INVALID_ERROR_MESSAGE = "Your access token is invalid. Please generate a new access token and try again";
    public final static String TOKEN_EXPIRED_ERROR_CODE = "expire_token";
    public final static String TOKEN_EXPIRED_ERROR_MESSAGE = "Your access token is expired. Please generate a new access token and try again";
}
