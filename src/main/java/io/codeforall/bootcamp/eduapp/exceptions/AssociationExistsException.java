package io.codeforall.bootcamp.eduapp.exceptions;

import io.codeforall.bootcamp.eduapp.errors.ErrorMessage;

public class AssociationExistsException extends EduAppException {

    public AssociationExistsException() {
        super(ErrorMessage.ASSOCIATION_EXISTS);
    }
}
