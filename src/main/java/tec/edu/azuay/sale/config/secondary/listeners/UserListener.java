package tec.edu.azuay.sale.config.secondary.listeners;

import jakarta.persistence.PrePersist;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import tec.edu.azuay.sale.exceptions.ObjectNotFoundException;
import tec.edu.azuay.sale.persistence.models.User;
import tec.edu.azuay.sale.persistence.repository.IPersonRepository;
import tec.edu.azuay.sale.persistence.repository.IUserRepository;

@Component
@RequiredArgsConstructor(onConstructor_ = @__(@Lazy))
public class UserListener {

    private final IUserRepository userRepository;

    private final IPersonRepository personRepository;

    @PrePersist
    public void prePersist(User user) {
        userRepository.findOneByUserIgnoreCase(user.getUser()).ifPresent(
                user1 -> {
                    System.out.println("User already exists");
                    throw new ObjectNotFoundException("User already exists");
                }
        );

        checkPerson(user.getPerson().getPersonId());
    }

    private void checkPerson(Long personId) {
        personRepository.findById(personId).ifPresentOrElse(
                person -> {
                },
                () -> {
                    throw new ObjectNotFoundException("Person with id %s not found".formatted(personId));
                }
        );
    }
}
