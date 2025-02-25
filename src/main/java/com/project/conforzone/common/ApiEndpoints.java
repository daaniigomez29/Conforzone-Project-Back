package com.project.conforzone.common;

public class ApiEndpoints {
    //General
    public static final String BASE_API = "/api/v1";
    public static final String BASE_ID = "/{id}";
    public static final String BASE_ADD = "/add";
    public static final String BASE_EDIT = "/edit";

    //Autentication
    public static final String BASE_AUTH= "/auth";
    public static final String LOGIN_API = "/login";
    public static final String REGISTER_API = "/register";
    public static final String CONFIRM_REGISTER_API = "confirm-register/{token}";

    //User
    public static final String BASE_USER = "/users";
    public static final String EXISTS_EMAIL = "/existsEmail/{email}";

    //SpecificService
    public static final String BASE_SPECIFIC_SERVICE = "/specific_services";

    //Service
    public static final String BASE_SERVICE = "/services";

    //ServiceAM
    public static final String BASE_SERVICE_AM = "/services_am";

    //PurchaseBooking
    public static final String BASE_PURCHASE_BOOKING = "/purchases";
    public static final String PURCHASE = "/add/{userId}";
    public static final String USER_PURCHASES = "/user/{userId}";
}
