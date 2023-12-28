package com.example.apiFiles.exception;

import com.example.apiFiles.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public class FileManagerExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("The file is too long, it exceeds 2 MB"));
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseMessage> handleFileNotFundException (FileNotFoundException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("The requested file was not found or is not available"));
    }


    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseMessage> handleIOException (IOException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("There was an error procesing the file"));
    }
}