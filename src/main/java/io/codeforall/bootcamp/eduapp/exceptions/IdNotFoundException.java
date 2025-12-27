package io.codeforall.bootcamp.eduapp.exceptions;

import io.codeforall.bootcamp.eduapp.errors.ErrorMessage;
/**
 * Thrown to indicate that the customer was not found
 */
public class IdNotFoundException extends EduAppException {

    /**
     * @see EduAppException#EduAppException(String)
     */
    public IdNotFoundException() {
        super(ErrorMessage.CUSTOMER_NOT_FOUND);
    }
}
