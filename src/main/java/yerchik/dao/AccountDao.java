package yerchik.dao;

import yerchik.entity.Account;

import java.util.List;

/**
 * Created by Yerchik on 22.03.2017.
 */
public interface AccountDao {
    void add(Account account);

    void edit(Account account);

    void delete(String login);

    Account findById(int id);

    Account findByLogin(String login);

    List<Account> findAllUsers(String  role);

    Account findByNameAndSecondname(String name, String secondName);
}
