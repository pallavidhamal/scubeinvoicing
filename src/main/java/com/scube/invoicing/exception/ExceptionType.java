package com.scube.invoicing.exception;
/**
 * Created by Keshav Patel.
 */
public enum ExceptionType {
    ENTITY_NOT_FOUND("not.found"),
    DUPLICATE_ENTITY("duplicate"),
    ALREADY_EXIST("duplicate"),
    ALREADY_EXIST_ENTITY("already"),
    UNAUTHORIZED("unauthorized"),
    ENTITY_EXCEPTION("exception"),
    FILE_SIZE_EXCEPTION("size.exceed"),
    VALUE_NOT_FOUND("value.not.found"),
    VALUE_NOT_LIVE("value.not.live"),
	CONTACT_ADMINISTRATOR("contact.to.Administrator"),
	FREQUENCY_NOT_FOUND("frequency.not.found"),
	NOT_AVAILABLE("not.available"),
	NOT_VALID("not.valid");
	
    String value;

    ExceptionType(String value) {
        this.value = value;
    }

    String getValue() {
        return this.value;
    }
}