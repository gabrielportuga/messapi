package com.mishmash.messapi.services;

import java.util.ArrayList;
import java.util.List;

import com.mishmash.messapi.services.interfaces.IApiResponse;

import io.restassured.response.Response;

public class ApiResponse<T> implements IApiResponse<T> {
    private T data;
    private List<T> dataList;
    private Response response;
    private Exception e;

    public ApiResponse(Class<T> t, Response response) {
        this.response = response;
        try {
            this.data = t.getDeclaredConstructor().newInstance();
            this.dataList = new ArrayList<T>();
        } catch (Exception e) {
            throw new RuntimeException("There should be a default constructor in the Response POJO");
        }
    }

    public String getContent() {
        return response.getBody().asString();
    }

    public int getStatusCode() {
        return response.getStatusCode();
    }

    public boolean isSuccessful() {
        int code = response.getStatusCode();
        if (code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205)
            return true;
        return false;
    }

    public String getStatusDescription() {
        return response.getStatusLine();
    }

    public Response getResponse() {
        return response;
    }

    public T getBody() {
        try {
            data = (T) response.getBody().as(data.getClass());
        } catch (Exception e) {
            this.e = e;
        }
        return data;
    }

    public List<T> getBodyList() {
        try {
            dataList = (List<T>) response.getBody().as(dataList.getClass());
        } catch (Exception e) {
            this.e = e;
        }
        return dataList;
    }

    public Exception getException() {
        return e;
    }

}
