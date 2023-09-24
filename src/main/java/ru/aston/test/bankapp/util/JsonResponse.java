package ru.aston.test.bankapp.util;

public class JsonResponse<T> {
	
	private T data;
    private String message;
    private boolean success;
    private boolean error;
	    
    public JsonResponse(T data, String message, boolean success, boolean error) {
        this.data = data;
        this.message = message;
        this.success = success;
        this.error = error;
    }


    public JsonResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public JsonResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
    }

    public JsonResponse(boolean success) {
        this.success = success;
    }

    public JsonResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public JsonResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public JsonResponse(String message) {
        this.message = message;
        this.success = false;
        this.error = true;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
	
}
