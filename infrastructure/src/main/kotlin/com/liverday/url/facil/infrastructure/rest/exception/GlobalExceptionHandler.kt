package com.liverday.url.facil.infrastructure.rest.exception

import com.liverday.url.facil.domain.exceptions.EntityAlreadyExistsException
import com.liverday.url.facil.domain.exceptions.Error
import com.liverday.url.facil.domain.exceptions.NotFoundException
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

    @ExceptionHandler(EntityAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleTokenAlreadyExists(exception: EntityAlreadyExistsException): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(AppError(exception.message!!, exception.errors))
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleUrlNotFoundException(exception: NotFoundException): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(AppError(exception.message!!, exception.errors))
    }

    @ExceptionHandler(WebExchangeBindException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationError(ex: WebExchangeBindException): ResponseEntity<AppError> {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(AppError( "Erros de validação", fromWebExchangeBindException(ex)))
    }

    fun fromWebExchangeBindException(ex: WebExchangeBindException): List<Error> {
        return ex.fieldErrors
                .map { fieldError -> Error(fieldError.defaultMessage!!) }
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalException(ex: Exception): ResponseEntity<AppError> {
        logger.error("Internal server error: {}", ex)
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(AppError( "Erro inesperado"))
    }

    data class AppError(
            val message: String,
            val errors: List<Error> = listOf()
    )
}