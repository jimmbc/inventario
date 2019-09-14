package stock;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class productoNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(productoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productoNotFoundHandler(productoNotFoundException ex) {
        return ex.getMessage();
    }
}