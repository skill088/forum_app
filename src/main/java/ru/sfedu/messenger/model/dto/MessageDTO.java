/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model.dto;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Vovka
 */
@Root(name = "message")
public class MessageDTO implements WithID {
    @Element(name = "id")
    private long id;
    @Element(name = "body")
    private String body;
    @Element(name = "author")
    private String author;
    @Element(name = "posted")
    private Long posted;
    @Element(name = "id_topic")
    private Long idTopic;

    public MessageDTO() {
    }

    public MessageDTO(long id, String body, String author, Long posted) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.posted = posted;
    }

    public MessageDTO(long id, String body, String author, Long posted, Long idTopic) {
        this.id = id;
        this.body = body;
        this.author = author;
        this.posted = posted;
        this.idTopic = idTopic;
    }
    
    

    /**
     * Get the value of author
     *
     * @return the value of author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the value of author
     *
     * @param author new value of author
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Get the value of posted
     *
     * @return the value of posted
     */
    public Long getPosted() {
        return posted;
    }

    /**
     * Set the value of posted
     *
     * @param posted new value of posted
     */
    public void setPosted(Long posted) {
        this.posted = posted;
    }


    /**
     * Get the value of body
     *
     * @return the value of body
     */
    public String getBody() {
        return body;
    }

    /**
     * Set the value of body
     *
     * @param body new value of body
     */
    public void setBody(String body) {
        this.body = body;
    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }

    public Long getIdTopic() {
        return idTopic;
    }

    public void setIdTopic(Long idTopic) {
        this.idTopic = idTopic;
    }
    
    

    @Override
    public String toString() {
        return "MessageDTO{" + "id=" + id + ", body=" + body + ", author=" 
                + author + ", posted=" + posted + ", idTopic=" + idTopic + '}';
    }

}
