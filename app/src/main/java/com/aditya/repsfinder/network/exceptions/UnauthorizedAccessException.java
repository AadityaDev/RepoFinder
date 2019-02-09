package com.aditya.repsfinder.network.exceptions;

import com.aditya.repsfinder.constants.AppConstants;

public class UnauthorizedAccessException extends HttpException {

    public UnauthorizedAccessException() {
        super(AppConstants.UNAUTHORIZED_ACCESS);
    }
}
