package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.ProductRequestDto;
import tec.edu.azuay.sale.dto.responses.ProductDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Product;
import tec.edu.azuay.sale.persistence.repository.IClassificationRepository;
import tec.edu.azuay.sale.persistence.repository.IProductRepository;
import tec.edu.azuay.sale.persistence.repository.ISupplierRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IGenericService<ProductDto, ProductRequestDto> {

    private final IProductRepository productRepository;

    private final IClassificationRepository classificationRepository;

    private final ISupplierRepository supplierRepository;

    private final ModelMapper modelMapper;

    private ProductDto entityToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product dtoToEntity(ProductRequestDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    @Override
    public ProductDto save(ProductRequestDto item) {

        classificationRepository.findById(item.getClassificationId()).orElseThrow(
                () -> new ObjectNotFoundException("Classification with id %s not found".formatted(item.getClassificationId()))
        );

        supplierRepository.findById(item.getSupplierId()).orElseThrow(
                () -> new ObjectNotFoundException("Supplier with id %s not found".formatted(item.getSupplierId()))
        );

        Product product = productRepository.save(dtoToEntity(item));

        return entityToDto(product);
    }

    @Override
    public ProductDto getById(Long itemId) {
        return entityToDto(productRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Product with id %s not found".formatted(itemId))));
    }

    @Override
    public List<ProductDto> getAllItems() {
        return productRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public ProductDto updateItem(ProductRequestDto item, Long itemId) {
        Product productToUpdate = productRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Product with id %s not found".formatted(itemId)));

        productToUpdate.setStock(Objects.nonNull(item.getStock()) ? item.getStock() : productToUpdate.getStock());
        productToUpdate.setUnitPrice(Objects.nonNull(item.getUnitPrice()) ? item.getUnitPrice() : productToUpdate.getUnitPrice());
        productToUpdate.setUnit(Objects.nonNull(item.getUnit()) ? item.getUnit() : productToUpdate.getUnit());
        productToUpdate.setIva(Objects.nonNull(item.getIva()) ? item.getIva() : productToUpdate.getIva());

        classificationRepository.findById(item.getClassificationId()).ifPresentOrElse(
                productToUpdate::setClassification,
                () -> {
                    throw new ObjectNotFoundException("Classification with id %s not found".formatted(item.getClassificationId()));
                }
        );

        supplierRepository.findById(item.getSupplierId()).ifPresentOrElse(
                productToUpdate::setSupplier,
                () -> {
                    throw new ObjectNotFoundException("Supplier with id %s not found".formatted(item.getSupplierId()));
                }
        );

        return entityToDto(productRepository.save(productToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        productRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Product with id %s not found".formatted(itemId)));

        productRepository.deleteById(itemId);
    }
}
