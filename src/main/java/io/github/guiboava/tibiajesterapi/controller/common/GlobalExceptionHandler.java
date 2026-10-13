package io.github.guiboava.tibiajesterapi.controller.common;

import io.github.guiboava.tibiajesterapi.controller.dto.error.ErrorField;
import io.github.guiboava.tibiajesterapi.controller.dto.response.ErrorResponse;
import io.github.guiboava.tibiajesterapi.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import org.springframework.security.core.AuthenticationException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // ---------- Helper ----------

    private ErrorResponse build(HttpStatus status, String message,
                                List<ErrorField> errors, HttpServletRequest request) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), message, errors, request.getRequestURI());
    }

    private ErrorResponse build(HttpStatus status, String message, HttpServletRequest request) {
        return build(status, message, List.of(), request);
    }

    // ---------- Fallback ----------

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleUnhandledException(Exception e, HttpServletRequest request) {
        log.error("Erro não catalogado", e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro inesperado, entre em contato com a administração.", request);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleInternalServerErrorException(InternalServerErrorException e, HttpServletRequest request) {
        log.error("Erro interno do servidor", e);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request);
    }

    // ---------- Validação / requisição inválida (400 / 422) ----------

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT) // antes: UNPROCESSABLE_ENTITY (depreciado)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                               HttpServletRequest request) {
        log.error("Erro na validação: {}", e.getMessage());
        List<ErrorField> errors = e.getFieldErrors()
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .toList(); // antes: Collectors.toList()

        return build(HttpStatus.UNPROCESSABLE_CONTENT, "Erro de validação", errors, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e,
                                                            HttpServletRequest request) {
        log.error("Erro de validação de constraint: {}", e.getMessage());
        List<ErrorField> errors = e.getConstraintViolations()
                .stream()
                .map(cv -> new ErrorField(cv.getPropertyPath().toString(), cv.getMessage()))
                .toList();

        return build(HttpStatus.UNPROCESSABLE_CONTENT, "Erro de validação", errors, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("Erro conflito de tipo: {}", e.getMessage());
        return build(HttpStatus.BAD_REQUEST,
                "O parâmetro '" + e.getName() + "' possui um valor inválido.", request);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.error("Erro corpo da requisição ilegível: {}", e.getMessage());
        return build(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido ou malformado.", request);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingParameter(MissingServletRequestParameterException e, HttpServletRequest request) {
        log.error("Erro parâmetro obrigatório ausente: {}", e.getMessage());
        return build(HttpStatus.BAD_REQUEST,
                "O parâmetro '" + e.getParameterName() + "' é obrigatório.", request);
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidRequestException(InvalidRequestException e, HttpServletRequest request) {
        log.error("Erro requisição inválida: {}", e.getMessage());
        return build(HttpStatus.BAD_REQUEST, e.getMessage(), request);
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleOperationNotPermittedException(OperationNotPermittedException e, HttpServletRequest request) {
        log.error("Erro operação não permitida: {}", e.getMessage());
        return ErrorResponse.defaultError(e.getMessage(), request.getRequestURI());
    }

    // ---------- Autenticação / autorização (401 / 403) ----------

    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidCredentialsException(InvalidCredentialsException e, HttpServletRequest request) {
        log.error("Erro falha de autenticação, credenciais inválidas: {}", e.getMessage());
        return build(HttpStatus.UNAUTHORIZED, e.getMessage(), request);
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleInvalidTokenException(InvalidTokenException e, HttpServletRequest request) {
        log.error("Erro token inválido ou expirado: {}", e.getMessage());
        return build(HttpStatus.UNAUTHORIZED, e.getMessage(), request);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorizedException(UnauthorizedException e, HttpServletRequest request) {
        log.error("Erro não autenticado: {}", e.getMessage());
        return build(HttpStatus.UNAUTHORIZED, e.getMessage(), request);
    }

    // Exceção do Spring Security: lançada pelos filtros (sem token, token inválido/expirado)
    // e encaminhada pelo RestSecurityErrorHandlers
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleAuthenticationException(AuthenticationException e, HttpServletRequest request) {
        log.error("Erro de autenticação: {}", e.getMessage());
        return build(HttpStatus.UNAUTHORIZED, "Autenticação necessária ou token inválido.", request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("Erro acesso não permitido: {}", e.getMessage());
        return build(HttpStatus.FORBIDDEN, "Acesso Negado.", request);
    }

    // ---------- Recurso não encontrado (404) ----------

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        log.error("Erro o recurso requisitado não existe: {}", e.getMessage());
        return build(HttpStatus.NOT_FOUND, e.getMessage(), request);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNoResourceFound(NoResourceFoundException e, HttpServletRequest request) {
        log.error("Erro rota inexistente: {}", e.getMessage());
        return build(HttpStatus.NOT_FOUND, "Endpoint não encontrado.", request);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("Erro método HTTP não suportado: {}", e.getMessage());
        return build(HttpStatus.METHOD_NOT_ALLOWED,
                "Método '" + e.getMethod() + "' não é suportado para este endpoint.", request);
    }

    // ---------- Conflitos (409) ----------

    @ExceptionHandler(DuplicateRegisterException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateRegisterException(DuplicateRegisterException e, HttpServletRequest request) {
        log.error("Erro de dados duplicados: {}", e.getMessage());
        return ErrorResponse.conflict(e.getMessage(), request.getRequestURI());
    }

    @ExceptionHandler(EntityInUseException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleEntityInUseException(EntityInUseException e, HttpServletRequest request) {
        log.error("Erro entidade em uso: {}", e.getMessage());
        return build(HttpStatus.CONFLICT, e.getMessage(), request);
    }

    // Exceção customizada do seu pacote
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("Erro violação de integridade de dados: {}", e.getMessage());
        return build(HttpStatus.CONFLICT, e.getMessage(), request);
    }

    // Exceção do Spring/JPA (nome igual, por isso o nome completo)
    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleSpringDataIntegrityViolation(
            org.springframework.dao.DataIntegrityViolationException e, HttpServletRequest request) {
        log.error("Erro violação de integridade no banco: {}", e.getMessage());
        return build(HttpStatus.CONFLICT,
                "Operação viola uma restrição de integridade dos dados.", request);
    }

    // ---------- Regra de negócio (422) ----------

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErrorResponse handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("Erro de regra de negócio: {}", e.getMessage());
        return build(HttpStatus.UNPROCESSABLE_CONTENT, e.getMessage(), request);
    }
}