package tec.edu.azuay.sale.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiError implements Serializable {

    private String backendMessage;

    private String message;

    private Integer httpCode;

    private String url;

    private String method;

    private LocalDateTime time;
}
