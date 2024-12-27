package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClassificationDto implements Serializable {

    private Long classificationId;

    private String group;
}
