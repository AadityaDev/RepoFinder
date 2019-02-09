package com.aditya.repsfinder.network.exceptions;

import com.aditya.repsfinder.constants.AppConstants;

public class UnsupportedFileTypeException extends HttpException {

    public UnsupportedFileTypeException() {
        super(AppConstants.MESSAGE_UNSUPPORTED_FILE_TYPE);
    }

}
