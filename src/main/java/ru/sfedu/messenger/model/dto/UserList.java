/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model.dto;

import java.util.ArrayList;
import java.util.List;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 *
 * @author Vovka
 */
@Root(name = "userList")
    public class UserList {
        @ElementList(inline = true)
        private List<UserDTO> list = new ArrayList<>();

        public List<UserDTO> getList() {
            return list;
        }

        public void setList(List<UserDTO> list) {
            this.list = list;
        }    
    }