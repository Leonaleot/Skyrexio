package user;

import utils.PropertyReader;

public class UserFactory {
    public static User withAdminPermission() {
        return new User(
                PropertyReader.getProperty("saucedemoo.login"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withLockedPermission() {
        return new User(
                PropertyReader.getProperty("saucedemoo.locked_user"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withIncorrectLoginPermission() {
        return new User(
                PropertyReader.getProperty("saucedemoo.incorrect_login"),
                PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withIncorrectPassPermission() {
        return new User(
                PropertyReader.getProperty("saucedemoo.login"),
                PropertyReader.getProperty("saucedemoo.incorrect_pass"));
    }

    public static User withEmptyLoginPermission() {
        return new User("", PropertyReader.getProperty("saucedemoo.password"));
    }

    public static User withEmptyPassPermission() {
        return new User(PropertyReader.getProperty("saucedemoo.login"), "");
    }

    public static User withEmptyLoginPassPermission() {
        return new User("", "");
    }
}
