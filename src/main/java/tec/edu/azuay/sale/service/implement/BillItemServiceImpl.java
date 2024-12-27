package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.BillItemRequestDto;
import tec.edu.azuay.sale.dto.responses.BillItemDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.BillItem;
import tec.edu.azuay.sale.persistence.models.Product;
import tec.edu.azuay.sale.persistence.repository.IBillItemRepository;
import tec.edu.azuay.sale.persistence.repository.IBillRepository;
import tec.edu.azuay.sale.persistence.repository.IProductRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillItemServiceImpl implements IGenericService<BillItemDto, BillItemRequestDto> {

    private final IBillItemRepository billItemRepository;

    private final IProductRepository productRepository;

    private final IBillRepository billRepository;

    private final ModelMapper modelMapper;

    private BillItemDto entityToDto(BillItem entity) {
        return modelMapper.map(entity, BillItemDto.class);
    }

    private BillItem dtoToEntity(BillItemRequestDto request) {
        BillItem bItem = modelMapper.map(request, BillItem.class);
        bItem.setSubtotal();

        return bItem;
    }

    @Override
    public BillItemDto save(BillItemRequestDto item) {

        productRepository.findById(item.getProductId()).orElseThrow(() -> new ObjectNotFoundException("Product with id %s not found".formatted(item.getProductId())));
        billRepository.findById(item.getBillId()).orElseThrow(() -> new ObjectNotFoundException("Bill with id %s not found".formatted(item.getBillId())));

        return entityToDto(billItemRepository.save(dtoToEntity(item)));
    }

    @Override
    public BillItemDto getById(Long itemId) {
        return entityToDto(billItemRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Bill Item not found")));
    }

    @Override
    public List<BillItemDto> getAllItems() {
        return billItemRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public BillItemDto updateItem(BillItemRequestDto item, Long itemId) {
        BillItem billItemToUpdate = billItemRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Bill Item not found"));

        billItemToUpdate.setQuantity(Objects.nonNull(item.getQuantity()) ? item.getQuantity() : billItemToUpdate.getQuantity());
        billItemToUpdate.setPrice(Objects.nonNull(item.getPrice()) ? item.getPrice() : billItemToUpdate.getPrice());
        billItemToUpdate.setSubtotal();

        productRepository.findById(item.getProductId()).ifPresentOrElse(
                billItemToUpdate::setProduct,
                () -> {
                    throw new ObjectNotFoundException("Product with id %s not found".formatted(item.getProductId()));
                }
        );

        billRepository.findById(item.getBillId()).ifPresentOrElse(
                billItemToUpdate::setBill,
                () -> {
                    throw new ObjectNotFoundException("Bill with id %s not found".formatted(item.getBillId()));
                }
        );

        return entityToDto(billItemToUpdate);
    }

    @Override
    public void deleteItem(Long itemId) {
        billItemRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Bill Item not found"));

        billItemRepository.deleteById(itemId);
    }
}
