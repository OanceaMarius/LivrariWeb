/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ro.papetti.exceptions;


import jakarta.persistence.EntityNotFoundException;
    import org.springframework.http.HttpStatus ;
    import org.springframework.http.ResponseEntity ;
    import org.springframework.web.bind.annotation.ExceptionHandler ;
    import org.springframework.web.bind.annotation.RestControllerAdvice ;

    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
