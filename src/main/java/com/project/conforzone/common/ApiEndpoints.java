package com.project.conforzone.common;

public class ApiEndpoints {
    public static String BASE_API = "/api/v1";

    //Autentication
    public static String BASE_AUTH= "/auth";
    public static String LOGIN_API = "/login";
    public static String REGISTER_API = "/register";

    //User
    public static String BASE_USER = "/users";
    public static String EXISTS_EMAIL = "/existsEmail/{email}";

    //SpecificService
    public static String BASE_SPECIFIC_SERVICE = "/specific_services";

    //Service
    public static String BASE_SERVICE = "/services";

    //ServiceAM
    public static String BASE_SERVICE_AM = "/services_am";

    //PurchaseBooking
    public static String BASE_PURCHASE_BOOKING = "/purchases";
    public static String PURCHASE = "/add/{userId}";
    public static String USER_PURCHASES = "/user/{userId}";

    //General
    public static String BY_ID = "/{id}";
}
