package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BillDto implements Serializable {

    private Long billId;

    private String ruc;

    private LocalDateTime date;

    private Double discount;

    private Double total;

    private PersonDto person;

    private PayMethodDto payMethod;
}
