package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SupplierDto implements Serializable {

    private Long supplierId;

    private String ruc;

    private String phone;

    private String country;

    private String email;

    private String currency;
}
