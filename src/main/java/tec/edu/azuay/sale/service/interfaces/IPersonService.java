package tec.edu.azuay.sale.service.interfaces;

import tec.edu.azuay.sale.dto.requests.PersonRequestDto;
import tec.edu.azuay.sale.dto.responses.PersonDto;

public interface IPersonService extends IGenericService<PersonDto, PersonRequestDto> {
    PersonDto getUserByDni(String dni);

    String getFullName(String dni);
}
