package com.mishmash.messapi.services.interfaces;

import java.util.List;

import io.restassured.response.Response;

public interface IApiResponse<T> {
	public T getBody();

	public List<T> getBodyList();

	public String getContent();

	public int getStatusCode();

	public boolean isSuccessful();

	public String getStatusDescription();

	public Response getResponse();

	public Exception getException();
}
