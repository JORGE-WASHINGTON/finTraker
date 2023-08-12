package jwom.fintrak.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiValidationError {
    private int statusCode;
    private String message;
    private long timestamp;
    private List<ValidationError> errors;
    private String path;
}
