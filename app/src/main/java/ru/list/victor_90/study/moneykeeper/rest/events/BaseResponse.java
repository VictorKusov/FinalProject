package ru.list.victor_90.study.moneykeeper.rest.events;

public class BaseResponse {

    private boolean isError;
    private String errorMessage;

    public BaseResponse() {
    }

    public BaseResponse(boolean isError, String errorMessage) {
        this.isError = isError;
        this.errorMessage = errorMessage;
    }

    public boolean isError() {
        return isError;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}