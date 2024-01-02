package mate.academy.onlinebookstore.util;

public final class ErrorMessagesConstants {
    public static final String NOT_NULL_MESSAGE = "Can't be null";
    public static final String NOT_BLANK_MESSAGE = "Can't be blank";
    public static final String MUST_BE_POSITIVE_MESSAGE = "Must be greater than 0";
    public static final String VALUES_DONT_MATCH = "Fields values don't match!";
    public static final String INVALID_JWT_TOKEN = "Expired or invalid JWT token";
    public static final String USER_WITH_THIS_EMAIL_ALREADY_EXIST =
            "User with this email already exist";
    public static final String USER_NOT_FOUND_WITH_EMAIL = "Didn't find user by email";
    public static final String USER_NOT_FOUND_WITH_ID = "Didn't find user by id";

    public static final String BOOK_NOT_FOUND_WITH_ID = "Didn't find Book in DB with id:";
    public static final String CATEGORY_NOT_FOUND_WITH_ID =
            "Didn't find Category in DB with id:";

    public static final String SHOPPING_CART_NOT_FOUND_WITH_USER_ID =
            "Didn't find ShoppingCart for user with id:";

    public static final String CART_ITEM_NOT_FOUND_WITH_ID =
            "Didn't find CartItem with id:";

    private ErrorMessagesConstants() {
    }
}
