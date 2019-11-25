package com.ceej.controller;

import com.ceej.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.dnd.DropTarget;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserControllerTest {

    DataBaseUtility mock_dao;
    User user = new User();
    UserController userController ;

    @BeforeEach
    public void init(){
        mock_dao = mock(DataBaseUtility.class);
        userController = new UserController(mock_dao);
    }

    @Test
    @DisplayName("InputUserName: UserId不能为空")
    public void UserName_input_should_should_not_be_null(){
        when(mock_dao.isUserExisted(null)).thenReturn(false);
    }

    @ParameterizedTest
    @MethodSource("user_provider")
    @DisplayName("输入UserId应该与返回的姓名对应")
    public void UserName_should_correspond_to_the_name(int id, String name){
        when(mock_dao.getNickname(Integer.toString(id))).thenReturn(name);
        user = userController.getUsername(Integer.toString(id));
        assertEquals(name,user.getNickname());
    }

    static Stream<Arguments> user_provider(){
        return Stream.of(
                Arguments.of(1,"Alia"),
                Arguments.of(2,"Ben"),
                Arguments.of(3,"nuke")
        );
    }

}