package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.PayMethodRequestDto;
import tec.edu.azuay.sale.dto.responses.PayMethodDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.PayMethod;
import tec.edu.azuay.sale.persistence.repository.IPayMethodRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PayMethodServiceImpl implements IGenericService<PayMethodDto, PayMethodRequestDto> {

    private final IPayMethodRepository payMethodRepository;

    private final ModelMapper modelMapper;

    private PayMethodDto entityToDto(PayMethod payMethod) {
        return modelMapper.map(payMethod, PayMethodDto.class);
    }

    private PayMethod dtoToEntity(PayMethodRequestDto payMethodDto) {
        return modelMapper.map(payMethodDto, PayMethod.class);
    }

    @Override
    public PayMethodDto save(PayMethodRequestDto item) {
        return entityToDto(payMethodRepository.save(dtoToEntity(item)));
    }

    @Override
    public PayMethodDto getById(Long itemId) {
        return entityToDto(payMethodRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Pay method with id %s not found".formatted(itemId))));
    }

    @Override
    public List<PayMethodDto> getAllItems() {
        return payMethodRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public PayMethodDto updateItem(PayMethodRequestDto item, Long itemId) {
        PayMethod payToUpdate = payMethodRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Pay method with id %s not found".formatted(itemId)));

        payToUpdate.setDescription(Objects.nonNull(item.getDescription()) ? item.getDescription() : payToUpdate.getDescription());

        return entityToDto(payMethodRepository.save(payToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        payMethodRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Pay method with id %s not found".formatted(itemId)));

        payMethodRepository.deleteById(itemId);
    }
}
