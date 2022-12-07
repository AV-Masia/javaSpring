package com.example.sweater.demo;

import com.example.sweater.demo.service.MovieService;
import com.example.sweater.demo.service.impl.MovieServiceImpl;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    MovieService movieService = new MovieServiceImpl();

    public static final String HAS_AUTHORITY_DEVELOPER_WRITE = "hasAuthority('developers:write')";
    public static final String HAS_AUTHORITY_DEVELOPER_READ = "hasAuthority('developers:read')";


}
