package com.aditya.repsfinder.network.exceptions;


import com.aditya.repsfinder.constants.AppConstants;

public class AccessDeniedException extends HttpException {
    public AccessDeniedException() {
        super(AppConstants.ACCESS_DENIED);
    }
}
