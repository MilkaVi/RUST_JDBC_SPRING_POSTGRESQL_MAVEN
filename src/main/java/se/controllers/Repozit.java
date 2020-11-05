package se.controllers;

import se.repository.UserRepository;
import se.repository.UserRepositoryImpl;
import se.service.FileService;
import se.service.FileServiceImpl;

public class Repozit {
    static FileService fileRepository = new FileServiceImpl();
    static UserRepository users = new UserRepositoryImpl();
}
