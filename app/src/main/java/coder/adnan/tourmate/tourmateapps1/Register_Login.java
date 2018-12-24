package coder.adnan.tourmate.tourmateapps1;

public class Register_Login {
    private  String userId;
    private String userName;
    private int userPhone;
    private String user_Mail;
    private String user_Password;

    public Register_Login() {
    }

    public Register_Login(String user_Mail, String user_Password) {
        this.user_Mail = user_Mail;
        this.user_Password = user_Password;
    }

    public Register_Login(String userId, String userName, int userPhone, String user_Mail, String user_Password) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.user_Mail = user_Mail;
        this.user_Password = user_Password;
    }

    // get -sett next time if need
}

