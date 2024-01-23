package com.mishmash.messapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mishmash.messapi.models.Post;
import com.mishmash.messapi.services.ApiResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@RestController
public class PostController {
    @GetMapping("/")
    public String index() {
        return "Hello everyone!";
    }

    @GetMapping("/posts")
    public List<Post> GetPosts() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        Response response = request.get();

        ApiResponse<Post> result = new ApiResponse<Post>(Post.class, response);

        return result.getBodyList();
    }
}