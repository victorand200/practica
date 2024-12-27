package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PayMethodDto implements Serializable {

    private Long payMethodId;

    private String type;

    private String description;
}
