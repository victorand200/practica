package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.PersonRequestDto;
import tec.edu.azuay.sale.dto.responses.PersonDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.Person;
import tec.edu.azuay.sale.persistence.repository.IPersonRepository;
import tec.edu.azuay.sale.service.interfaces.IPersonService;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;

    private final ModelMapper modelMapper;

    private PersonDto entityToDto(Person person) {
        return modelMapper.map(person, PersonDto.class);
    }

    private Person dtoToEntity(PersonRequestDto personDto) {
        return modelMapper.map(personDto, Person.class);
    }

    @Override
    public PersonDto save(PersonRequestDto item) {
        personRepository.findPersonByDni(item.getDni()).ifPresent(person -> {
            throw new ObjectNotFoundException("Person with DNI %s already exists".formatted(item.getDni()));
        });

        return entityToDto(personRepository.save(dtoToEntity(item)));
    }

    @Override
    public PersonDto getById(Long itemId) {
        return entityToDto(personRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Person with id %s not found".formatted(itemId))));
    }

    @Override
    public List<PersonDto> getAllItems() {
        return personRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override
    public PersonDto updateItem(PersonRequestDto item, Long itemId) {
        Person personToUpdate = personRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Person with id %s not found".formatted(itemId)));

        personToUpdate.setName(Objects.nonNull(item.getName()) ? item.getName() : personToUpdate.getName());
        personToUpdate.setLastName(Objects.nonNull(item.getLastName()) ? item.getLastName() : personToUpdate.getLastName());
        personToUpdate.setPhone(Objects.nonNull(item.getPhone()) ? item.getPhone() : personToUpdate.getPhone());
        personToUpdate.setEmail(Objects.nonNull(item.getEmail()) ? item.getEmail() : personToUpdate.getEmail());

        return entityToDto(personRepository.save(personToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        personRepository.findById(itemId).orElseThrow(() -> new ObjectNotFoundException("Person with id %s not found".formatted(itemId)));

        personRepository.deleteById(itemId);
    }

    @Override
    public PersonDto getUserByDni(String dni) {
        return entityToDto(personRepository.findPersonByDni(dni).orElseThrow(() -> new ObjectNotFoundException("Person with dni %s not found".formatted(dni))));
    }

    @Override
    public String getFullName(String dni) {
        PersonDto person = getUserByDni(dni);
        return person.getName() + " " + person.getLastName();
    }
}
