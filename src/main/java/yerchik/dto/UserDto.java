package yerchik.dto;

import yerchik.entity.Account;

/**
 * Created by Yerchik on 26.03.2017.
 */
public class UserDto {
    private String name;
    private String secondName;
    private String login;
    private String email;
    private boolean activated;

    public UserDto() {
    }

    public UserDto(String name, String secondName, String login, String email, boolean activated) {
        this.name = name;
        this.secondName = secondName;
        this.login = login;
        this.email = email;
        this.activated = activated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public static UserDto convertToDTO(Account account){
        return new UserDto(account.getName(), account.getSecondName(), account.getLogin(), account.getEmail(), account.isEnable());
    }
}
