package com.gabikersul.ilegra.technicalchallenge.exception;

public class FileException extends RuntimeException {
    public FileException(String fileErrorMessage){
        super(fileErrorMessage);
    }
}
