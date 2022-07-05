package com.liverday.url.facil.url.presenters.rest.exception

import com.liverday.url.facil.domain.AppError
import com.liverday.url.facil.domain.Validation
import com.liverday.url.facil.domain.url.exceptions.TokenAlreadyExistException
import com.liverday.url.facil.domain.url.exceptions.UrlNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.support.WebExchangeBindException

@ControllerAdvice
@Suppress("unused")
class GlobalExceptionHandler {
    private val logger: Logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(TokenAlreadyExistException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTokenAlreadyExists(): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        AppError(HttpStatus.BAD_REQUEST.value(), "Token já existe")
                )
    }

    @ExceptionHandler(UrlNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUrlNotFoundException(): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        AppError(HttpStatus.NOT_FOUND.value(), "URL não existe")
                )
    }

    @ExceptionHandler(WebExchangeBindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: WebExchangeBindException): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        AppError(HttpStatus.BAD_REQUEST.value(), "Erros de validação", fromWebExchangeBindException(ex))
                )
    }

    fun fromWebExchangeBindException(ex: WebExchangeBindException): List<Validation> {
        return ex.fieldErrors
                .map { fieldError -> Validation(fieldError.field, fieldError.defaultMessage!!) }
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalException(ex: Exception): ResponseEntity<AppError> {
        logger.error("Internal server error: {}", ex)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro inesperado")
                )
    }
}