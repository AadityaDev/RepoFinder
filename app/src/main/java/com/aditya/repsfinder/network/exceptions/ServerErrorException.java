package com.aditya.repsfinder.network.exceptions;

import com.aditya.repsfinder.constants.AppConstants;

public class ServerErrorException extends HttpException {

    public ServerErrorException() {
        super(AppConstants.MESSAGE_SERVER_ERROR);
    }
}
