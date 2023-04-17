package com.example.travelleronline.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class AbstractService {
    @Autowired
    protected ModelMapper mapper;

}
