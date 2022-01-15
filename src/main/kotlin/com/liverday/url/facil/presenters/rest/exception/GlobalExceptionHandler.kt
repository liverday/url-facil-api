package com.liverday.url.facil.presenters.rest.exception

import com.liverday.url.facil.domain.AppError
import com.liverday.url.facil.domain.url.exceptions.TokenAlreadyExistException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
@Suppress("unused")
class GlobalExceptionHandler {

    @ExceptionHandler(TokenAlreadyExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTokenAlreadyExists(): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        AppError(HttpStatus.BAD_REQUEST.value(), "Token já existe")
                )
    }
}