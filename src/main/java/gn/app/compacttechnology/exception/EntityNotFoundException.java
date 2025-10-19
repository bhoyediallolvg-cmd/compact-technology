package gn.app.compacttechnology.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
     String message;

    /*public EntityNotFoundException(String message) {
        super(message);
    }*/
}
