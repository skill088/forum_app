/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model.dto;

import java.io.Serializable;
import com.opencsv.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import javax.xml.ws.BindingType;

/**
 * Created by Vovka on 04.10.2017.
 */

public class PostItemDTO implements Serializable, WithID {
    
//    @CsvBindByName
    @CsvBindByPosition(position = 0)
    private long id;
//    @CsvBindByName
    @CsvBindByPosition(position = 1)
    private String title;
//    @CsvBindByName
    @CsvBindByPosition(position = 2)
    private String body;
//    @CsvBindByName
    @CsvBindByPosition(position = 3)
    private String poster;
//    @CsvBindByName
    @CsvBindByPosition(position = 4)
    private String modified;
    
    public PostItemDTO() {}

    public PostItemDTO(Long id, String title, String body, String poster, String modified) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.poster = poster;
        this.modified = modified;
    }
    
    @Override
    public long getId() {
        return id;
    }
    
    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String posterD) {
        this.poster = posterD;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    private class ModifiedD implements Serializable {
        private String name;
        private Integer time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }

    }

    public class PosterD implements Serializable {

        private long id;
        private String name;
        private long time;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

    }
    
    @Override
    public String toString() {
        return getId() + "," + getTitle() + ","
                + getBody() + "," + getPoster() + "," + getModified();
    }
}
