package tec.edu.azuay.sale.service.implement;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tec.edu.azuay.sale.dto.requests.UserRequestDto;
import tec.edu.azuay.sale.dto.responses.UserDto;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.User;
import tec.edu.azuay.sale.persistence.repository.IPersonRepository;
import tec.edu.azuay.sale.persistence.repository.IUserRepository;
import tec.edu.azuay.sale.service.PasswordHasher;
import tec.edu.azuay.sale.service.interfaces.IGenericService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IGenericService<UserDto, UserRequestDto> {

    private final IUserRepository userRepository;

    private final IPersonRepository personRepository;

    private final ModelMapper modelMapper;

    private final PasswordHasher passwordHasher;

    private UserDto entityToDto(User request) {
        return modelMapper.map(request, UserDto.class);
    }

    private User dtoToEntity(UserRequestDto request) {
        modelMapper.typeMap(UserRequestDto.class, User.class).addMappings(mapper -> mapper.skip(User::setUserId));

        request.setPassword(passwordHasher.hashPassword(request.getPassword()));

        return modelMapper.map(request, User.class);
    }

    @Override
    public UserDto save(UserRequestDto item) {
        return entityToDto(userRepository.save(dtoToEntity(item)));
    }

    @Override
    public UserDto getById(Long itemId) {
        return entityToDto(userRepository
                .findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));
    }

    @Override
    public List<UserDto> getAllItems() {
        return userRepository.findAll().stream().map(this::entityToDto).toList();
    }

    @Override

    public UserDto updateItem(UserRequestDto user, Long userId) {
        User userToUpdate = userRepository.findById(userId)
                .orElseThrow(() -> new ObjectNotFoundException("User with id %s not found".formatted(userId)));
        String hashedPassword = passwordHasher.hashPassword(user.getPassword());

        userToUpdate.setPassword(hashedPassword);

        personRepository.findById(user.getPersonId()).ifPresentOrElse(
                userToUpdate::setPerson,
                () -> {
                    throw new ObjectNotFoundException("Person with id %s not found".formatted(user.getPersonId()));
                }
        );

        return entityToDto(userRepository.save(userToUpdate));
    }

    @Override
    public void deleteItem(Long itemId) {
        userRepository.findById(itemId)
                .orElseThrow(() -> new ObjectNotFoundException("User not found"));


        userRepository.deleteById(itemId);
    }

    public UserDto findOneByUser(String user) {
        return entityToDto(userRepository
                .findOneByUserIgnoreCase(user)
                .orElseThrow(() -> new ObjectNotFoundException("User not found")));
    }
}
