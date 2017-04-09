package yerchik.service;

import yerchik.dto.SignUpDto;
import yerchik.dto.UserDto;
import yerchik.entity.Account;

import java.util.List;

/**
 * Created by Yerchik on 25.03.2017.
 */
public interface AccountService {
    void add(SignUpDto dto);

    void verify(String login);

    void delete(String login);

    Account findById(int id);

    Account findByLogin(String login);

    List<UserDto> findAllUsers();

    UserDto findByNameAndSecondname(String name, String secondName);
}
