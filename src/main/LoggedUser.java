package main;

public class LoggedUser {

    private String name;
    private String lastname;
    private String mail;
    private String password;
    private boolean isAccountOpen;

    public LoggedUser() {
        isAccountOpen = false;
    }

    public void login(String name, String lastname, String mail, String password) {
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.password = password;
        isAccountOpen = true;
    }

    public void logout() {
        this.name = null;
        this.lastname = null;
        this.mail = null;
        this.password = null;
        isAccountOpen = false;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountOpen() {
        return isAccountOpen;
    }
}
