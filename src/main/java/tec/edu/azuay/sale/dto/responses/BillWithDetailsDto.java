package tec.edu.azuay.sale.dto.responses;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class BillWithDetailsDto extends BillDto implements Serializable {

    private List<BillItemsDto> billItems;
}
