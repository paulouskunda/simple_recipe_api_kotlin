package com.paul.exception

import com.paul.config.ApplicationProperties
import com.paul.data.Response
import com.paul.util.RecipeUtil
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessResourceUsageException
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest


@RestControllerAdvice
class ExceptionAdviceHandler(
    private val properties: ApplicationProperties
) {

    val logger = RecipeUtil().logger<ExceptionAdviceHandler>()

    @ExceptionHandler(InvalidDataAccessResourceUsageException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun  resourceUsageException(exception: InvalidDataAccessResourceUsageException):ResponseEntity<*>{
        logger.error("Invalid Data Access")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), properties.internalServerError))
    }

    @ExceptionHandler(value = [ChangeSetPersister.NotFoundException::class])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun notFound(exception: ChangeSetPersister.NotFoundException): ResponseEntity<*> {
        logger.error(properties.internalServerError + " {} ", exception.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                Response(
                    HttpStatus.NOT_FOUND.value(),
                  HttpStatus.NOT_FOUND.reasonPhrase
                )
            )
    }


    @ExceptionHandler(DataIntegrityViolationException::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun dataIntegrityViolationHandler(exception: DataIntegrityViolationException): ResponseEntity<*>? {
        logger.error(properties.internalServerError + " {} ", exception.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(
                Response(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    properties.internalServerError
                )
            )
    }

    @ExceptionHandler(value = [IllegalArgumentException::class])
    fun illegalArgumentException(exception: IllegalArgumentException): ResponseEntity<*> {
        logger.error("${properties.illegalArguments} => {} ", exception.message)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body<Any>(
            Response(
                HttpStatus.BAD_REQUEST.value(),
                properties.illegalArguments
            )
        )
    }

    @ExceptionHandler(value = [HttpRequestMethodNotSupportedException::class])
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    fun methodNotAllowed(
        exception: HttpRequestMethodNotSupportedException,
        request: HttpServletRequest
    ): ResponseEntity<*> {
        logger.error(properties.methodNotAllowed + " {} ", exception.message)
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body<Any>(
            Response(
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                request.method + " " + properties.methodNotAllowed
            )
        )
    }
}