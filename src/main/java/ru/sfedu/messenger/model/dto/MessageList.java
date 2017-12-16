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
@Root(name="messageList")
public class MessageList {
    @ElementList(inline = true)
    private List<MessageDTO> list = new ArrayList<>();

    public List<MessageDTO> getList() {
        return list;
    }

    public void setList(List<MessageDTO> list) {
        this.list = list;
    }
}