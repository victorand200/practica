package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CompetitionDto implements Serializable {

    private Long competitionId;

    private String name;

    private String description;
}
