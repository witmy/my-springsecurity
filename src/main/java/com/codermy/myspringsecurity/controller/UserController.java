package com.codermy.myspringsecurity.controller;

import com.codermy.myspringsecurity.dto.UserDto;
import com.codermy.myspringsecurity.eneity.TbUser;
import com.codermy.myspringsecurity.service.UserService;
import com.codermy.myspringsecurity.utils.MD5;
import com.codermy.myspringsecurity.utils.PageTableRequest;
import com.codermy.myspringsecurity.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author codermy
 * @createTime 2020/6/25
 */
@Controller
@RequestMapping("/system/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUserByFuzzyUsername")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:list')")
    public Result<TbUser> findUserByFuzzyUsername(PageTableRequest request,String username){
        request.countOffset();
        return userService.getUserByFuzzyUsername(username,request.getOffset(),request.getLimit());
    }


    @GetMapping("index")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:list')")
    @ApiOperation(value = "用户列表")
    public Result<TbUser> index(PageTableRequest pageTableRequest){
        pageTableRequest.countOffset();
        return userService.getAllUsersByPage(pageTableRequest.getOffset(),pageTableRequest.getLimit());
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyAuthority('user:add')")
    @ApiOperation(value = "添加用户页面")
    public String addUser(Model model){
        model.addAttribute(new TbUser());
        return "/system/user/user-add";
    }

    @PostMapping("/add")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:add')")
    @ApiOperation(value = "添加用户")
    public Result<TbUser> saveUser(UserDto userDto,Integer roleId){
        TbUser tbUser = null;
        tbUser = userService.getUserByPhone(userDto.getPhone());
        if(tbUser !=null && !(tbUser.getId().equals(userDto.getId())) ){
            return Result.error().code(20001).message("手机号已存在");
        }
        userDto.setStatus(1);
        // userDto.setPassword(MD5.crypt(userDto.getPassword()));
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        return userService.save(userDto,roleId);
    }

    @GetMapping("edit")
    @PreAuthorize("hasAnyAuthority('user:edit')")
    @ApiOperation(value = "修改用户界面")
    public String editUser(Model model,TbUser tbUser){
        model.addAttribute(userService.getUserById(tbUser.getId()));
        return "/system/user/user-edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:edit')")
    @ApiOperation(value = "修改用户")
    public Result<TbUser> updateUser(UserDto userDto, Integer roleId){
        TbUser tbUser = null;
        tbUser = userService.getUserByPhone(userDto.getPhone());
        if(tbUser !=null && !(tbUser.getId().equals(userDto.getId())) ){
            return Result.error().code(20001).message("手机号已存在");
        }
        return userService.updateUser(userDto,roleId);
    }


    @GetMapping("delete")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('user:del')")
    @ApiOperation(value = "删除用户")
    public Result deleteUser(UserDto userDto){
        int count = userService.deleteUser(userDto.getId());
        if(count>0){
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}
