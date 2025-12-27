package io.codeforall.bootcamp.eduapp.exceptions;

import io.codeforall.bootcamp.eduapp.errors.ErrorMessage;

/**
 * Thrown to indicate that the account was not found
 */
public class AccountNotFoundException extends EduAppException {

    /**
     * @see EduAppException#EduAppException(String)
     */
    public AccountNotFoundException() {
        super(ErrorMessage.ACCOUNT_NOT_FOUND);
    }
}
