package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.ClassificationRequestDto;
import tec.edu.azuay.sale.dto.responses.ClassificationDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Classification;
import tec.edu.azuay.sale.persistence.repository.IClassificationRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClassificationServiceImpl implements IGenericService<ClassificationDto, ClassificationRequestDto> {

    private final IClassificationRepository classificationRepository;

    private final ModelMapper modelMapper;

    private ClassificationDto entityToDto(Classification entity) {
        return modelMapper.map(entity, ClassificationDto.class);
    }

    private Classification dtoToEntity(ClassificationRequestDto request) {
        return modelMapper.map(request, Classification.class);
    }

    @Override
    public ClassificationDto save(ClassificationRequestDto item) {
        return entityToDto(classificationRepository.save(dtoToEntity(item)));
    }

    @Override
    public ClassificationDto getById(Long itemId) {
        return entityToDto(classificationRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Classification with id %s not found".formatted(itemId))));
    }

    @Override
    public List<ClassificationDto> getAllItems() {
        return classificationRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public ClassificationDto updateItem(ClassificationRequestDto item, Long itemId) {
        Classification classificationToUpdate = classificationRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Classification with id %s not found".formatted(itemId)));

        classificationToUpdate.setGroup(Objects.nonNull(item.getGroup()) ? item.getGroup() : classificationToUpdate.getGroup());

        return entityToDto(classificationRepository.save(classificationToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        classificationRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Classification with id %s not found".formatted(itemId)));

        classificationRepository.deleteById(itemId);
    }
}
