package com.example.practice.demo.controller;

import com.example.practice.demo.entity.User;
import com.example.practice.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String userList(Model model, Pageable pageable){

        Page<User> list = userRepository.findAll(pageable);

        model.addAttribute("user", new User());
        model.addAttribute("list", list);

        return "index";
    }

    @RequestMapping(value="/insert" , method = RequestMethod.POST)
    public String saveUser(User user){
        log.info(user.toString());
        userRepository.save(user);

        return "redirect:/";
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public String updateUser(User user){
        userRepository.save(user);
        log.info(user.toString());

        return "redirect:/";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String detailUser(@PathVariable Long id, Model model) {

        User user = userRepository.findUserById(id);
        log.info(user.toString());

        model.addAttribute("user", user);

        return "update";
    }

    @RequestMapping(value="/delete", method = RequestMethod.POST)
    public String deleteUser(User user){

        userRepository.deleteById(user.getId());

        return "redirect:/";
    }

}
