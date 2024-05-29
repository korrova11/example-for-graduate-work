package ru.skypro.homework.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.repository.UserRepository;
@Service

public interface UserService {

    boolean changePassword(NewPassword changePassword);
    User getUserDto();

    boolean validationPassword(NewPassword newPassword);

}
