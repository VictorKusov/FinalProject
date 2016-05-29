package ru.list.victor_90.study.moneykeeper.rest.events;


public class LoginResponse extends BaseResponse{

    public LoginResponse() {
        super();
    }

    public LoginResponse(boolean isError, String errorMessage) {
        super(isError, errorMessage);
    }
}

