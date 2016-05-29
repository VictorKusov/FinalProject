package ru.list.victor_90.study.moneykeeper.rest.events;


public class RegisterResponse extends BaseResponse {

    public RegisterResponse() {
        super();
    }

    public RegisterResponse(boolean isError, String errorMessage) {
        super(isError, errorMessage);
    }

}
