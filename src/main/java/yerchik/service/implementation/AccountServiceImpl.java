package yerchik.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yerchik.dao.AccountDao;
import yerchik.dao.ResultDao;
import yerchik.dao.RoleDao;
import yerchik.dto.SignUpDto;
import yerchik.dto.UserDto;
import yerchik.entity.Account;
import yerchik.entity.Role;
import yerchik.service.AccountService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Yerchik on 25.03.2017.
 */
@Service("userDetailsService")
public class AccountServiceImpl implements AccountService, UserDetailsService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private ResultDao resultDao;

    @Override
    public void add(SignUpDto dto) {
        Account account = dto.convertToEntity();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        account.setPassword(encoder.encode(account.getPassword()));
        if (dto.getLogin().equals("admin")) {
            try {
                Role admin = new Role("ADMIN");
                roleDao.addRole(admin);

            } catch (Exception e) {}
           finally {
                account.setRole(roleDao.findRoleByName("ADMIN"));
            }
        } else {
            try {
                System.out.println(546);
                Role user = new Role("USER");
                roleDao.addRole(user);
            } catch (Exception e) {}
            finally {
                account.setRole(roleDao.findRoleByName("USER"));
            }
        }
        accountDao.add(account);

    }

    @Override
    public void verify(String login) {
        Account account = accountDao.findByLogin(login);
        account.setEnable(true);
        accountDao.edit(account);

    }

    @Override
    public void delete(String login) {
        try {
            resultDao.deleteListResultByLogin(login);
        }catch (Exception e){}
        finally {
            accountDao.delete(login);
        }
    }

    @Override
    public Account findById(int id) {
        return accountDao.findById(id);
    }

    @Override
    public Account findByLogin(String login) {
        return accountDao.findByLogin(login);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> users = new ArrayList<>();
        for (Account account : accountDao.findAllUsers("USER")) {
            users.add(UserDto.convertToDTO(account));
        }
        return users;
    }

    @Override
    public UserDto findByNameAndSecondname(String name, String secondName) {
        return UserDto.convertToDTO(accountDao.findByNameAndSecondname(name, secondName));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountDao.findByLogin(login);
        if (account == null) {
            throw new UsernameNotFoundException("Bad Credentials");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + account.getRole().getName().toUpperCase()));
        return new User(account.getEmail(), account.getPassword(), account.isEnable(), true, true, true, authorities);
    }


}
