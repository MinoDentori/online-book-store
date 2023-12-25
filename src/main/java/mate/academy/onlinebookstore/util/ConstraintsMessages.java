package mate.academy.onlinebookstore.util;

public final class ConstraintsMessages {
    public static final String NOT_NULL_MESSAGE = "Can't be null";
    public static final String NOT_BLANK_MESSAGE = "Can't be blank";
    public static final String CANT_FIND_USER_BY_EMAIL = "Can't find user by email";
    public static final String MUST_BE_POSITIVE_MESSAGE = "Must be greater than 0";
    public static final String VALUES_DONT_MATCH = "Fields values don't match!";
    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXIST =
            "User with this email already exist";
    public static final String INVALID_JWT_TOKEN = "Expired or invalid JWT token";

    private ConstraintsMessages() {
    }
}
