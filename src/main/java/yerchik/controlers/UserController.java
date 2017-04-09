package yerchik.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yerchik.dto.ShowResultDTO;
import yerchik.dto.SignUpDto;
import yerchik.dto.UserDto;
import yerchik.entity.SendEmail;
import yerchik.service.AccountService;
import yerchik.service.ResultService;

import java.security.Principal;
import java.util.List;

/**
 * Created by Yerchik on 25.03.2017.
 */
@Controller
public class UserController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResultService resultService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    @ResponseBody
    public void signUn(@RequestBody SignUpDto dto){
        accountService.add(dto);
        SendEmail.sendEmail(dto.getEmail(), dto.getName(), dto.getLogin());
    }

    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ResponseBody
    public UserDto getUserInfo(Principal principal){
        return UserDto.convertToDTO(accountService.findByLogin(principal.getName()));
    }

    @RequestMapping(value = "/myResult", method = RequestMethod.GET)
    @ResponseBody
    public List<ShowResultDTO> getMyResult(Principal principal){
        return ShowResultDTO.convertListToDTO(resultService.findByAccount(principal.getName()));
    }



    @RequestMapping(value = "/allUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDto> getAllUser(){
        return accountService.findAllUsers();
    }

    @RequestMapping(value = "/userResult/{login}", method = RequestMethod.GET)
    @ResponseBody
    public List<ShowResultDTO> getUserResult(@PathVariable String login){
        return ShowResultDTO.convertListToDTO(resultService.findByAccount(login));
    }



}
