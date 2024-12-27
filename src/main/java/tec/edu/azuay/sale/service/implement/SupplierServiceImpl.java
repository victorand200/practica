package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.SupplierRequestDto;
import tec.edu.azuay.sale.dto.responses.SupplierDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Supplier;
import tec.edu.azuay.sale.persistence.repository.ISupplierRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements IGenericService<SupplierDto, SupplierRequestDto> {

    private final ISupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    private SupplierDto entityToDto(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDto.class);
    }

    private Supplier dtoToEntity(SupplierRequestDto supplierDto) {
        return modelMapper.map(supplierDto, Supplier.class);
    }

    @Override
    public SupplierDto save(SupplierRequestDto item) {
        supplierRepository.findSupplierByRuc(item.getRuc()).ifPresent(supplier -> {
            throw new ObjectNotFoundException("Supplier with RUC %s already exists".formatted(item.getRuc()));
        });

        return entityToDto(supplierRepository.save(dtoToEntity(item)));
    }

    @Override
    public SupplierDto getById(Long itemId) {
        return entityToDto(getSupplierById(itemId));
    }

    @Override
    public List<SupplierDto> getAllItems() {
        return supplierRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public SupplierDto updateItem(SupplierRequestDto item, Long itemId) {
        Supplier supplierToUpdate = getSupplierById(itemId);

        supplierToUpdate.setPhone(Objects.nonNull(item.getPhone()) ? item.getPhone() : supplierToUpdate.getPhone());
        supplierToUpdate.setCountry(Objects.nonNull(item.getCountry()) ? item.getCountry() : supplierToUpdate.getCountry());
        supplierToUpdate.setEmail(Objects.nonNull(item.getEmail()) ? item.getEmail() : supplierToUpdate.getEmail());
        supplierToUpdate.setCurrency(Objects.nonNull(item.getCurrency()) ? item.getCurrency() : supplierToUpdate.getCurrency());

        return entityToDto(supplierRepository.save(supplierToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        getSupplierById(itemId);

        supplierRepository.deleteById(itemId);
    }

    private Supplier getSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId).orElseThrow(() -> new ObjectNotFoundException("Supplier with id %s not found".formatted(supplierId)));
    }
}
