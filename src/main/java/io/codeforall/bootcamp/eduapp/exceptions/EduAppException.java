package io.codeforall.bootcamp.eduapp.exceptions;

/**
 * A generic java bank exception to be used as base for concrete types of exceptions
 *
 * @see Exception
 */
public class EduAppException extends Exception {

    /**
     * @see Exception#Exception(String)
     */
    public EduAppException(String message) {
        super(message);
    }
}
