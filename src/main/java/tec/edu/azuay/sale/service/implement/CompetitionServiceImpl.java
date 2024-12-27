package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.CompetitionRequestDto;
import tec.edu.azuay.sale.dto.responses.CompetitionDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Competition;
import tec.edu.azuay.sale.persistence.repository.ICompetitionRepository;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements IGenericService<CompetitionDto, CompetitionRequestDto> {

    private final ICompetitionRepository competitionRepository;

    private final ModelMapper modelMapper;

    private CompetitionDto entityToDto(Competition competition) {
        return modelMapper.map(competition, CompetitionDto.class);
    }

    private Competition dtoToEntity(CompetitionRequestDto competition) {
        return modelMapper.map(competition, Competition.class);
    }

    @Override
    public CompetitionDto save(CompetitionRequestDto item) {
        return entityToDto(competitionRepository.save(dtoToEntity(item)));
    }

    @Override
    public CompetitionDto getById(Long itemId) {
        return entityToDto(competitionRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Competition with id %s not found".formatted(itemId))));
    }

    @Override
    public List<CompetitionDto> getAllItems() {
        return competitionRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public CompetitionDto updateItem(CompetitionRequestDto item, Long itemId) {
        Competition competitionToUpdate = competitionRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Competition with id %s not found".formatted(itemId)));

        competitionToUpdate.setName(Objects.nonNull(item.getName()) ? item.getName() : competitionToUpdate.getName());
        competitionToUpdate.setDescription(Objects.nonNull(item.getDescription()) ? item.getDescription() : competitionToUpdate.getDescription());

        return entityToDto(competitionRepository.save(competitionToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        competitionRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Competition with id %s not found".formatted(itemId)));

        competitionRepository.deleteById(itemId);
    }
}
