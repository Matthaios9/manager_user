package base.message;

public class NoticeMessage {
    public final static String REGISTER_NOTICE_01 = "Please access URL to verify your account: " +
            "<a href='" + "$1" + "/app/login'>" + "$2" + "/app/login</a>.\n" +
            "Your password: $3";

    public final static String OTP_NOTICE_01 = "Sorry, the system currently can not verify OTP. Please retry later.";
    public final static String OTP_NOTICE_02 = "Expired OTP. Please request for new OTP.";
    public final static String OTP_NOTICE_03 = "You have entered wrong OTP more than " + "$1" + " times, please wait and login again";
    public final static String OTP_NOTICE_04 = "Incorrect OTP. Please check it again";
    public final static String OTP_NOTICE_05 = "You have entered wrong OTP more than " + "$1" + " times, please wait and forgot password again";
    public final static String OTP_NOTICE_06 = "You have entered wrong OTP more than " + "$1" + " times, please wait and reset password again";

    public final static String EMAIL_NOTICE_01 = "Here is your clientId and secretKey, please dont provide to anyone!:" + "\r\n"
            + "ClientID: " + "$1" + "\r\n"
            + "SecretID: " + "$2";

    public final static String LOGIN_NOTICE_01 = "The verification number for verifying login is: $1";

    public final static String FORGOT_PASSWORD_01 = "The verification number forgot password is: $1";
    public final static String FORGOT_PASSWORD_02 = "Your new password is: $1";

    public final static String RESET_PASSWORD_01 = "The verification number reset password is: $1";
}
