package ru.sfedu.messenger.model.dto;

import com.opencsv.bean.CsvBindByPosition;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 *
 * @author Vovka
 */
@Root(name = "user")
public class UserDTO  implements WithID {
    @Element(name = "id")
    @CsvBindByPosition(position = 0)
    private long id;
    @Element(name = "name")
    @CsvBindByPosition(position = 1)
    private String name;
    @Element(name = "login")
    @CsvBindByPosition(position = 2)
    private String login;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
    }
    
    

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }


    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return getId() + "," + getName() + "," + getLogin();//To change body of generated methods, choose Tools | Templates.
    }
    
    
}
