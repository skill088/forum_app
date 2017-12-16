/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.sfedu.messenger.Constants;
import ru.sfedu.messenger.model.dto.EntityType;
import ru.sfedu.messenger.model.dto.MessageDTO;
import ru.sfedu.messenger.model.dto.TopicDTO;
import ru.sfedu.messenger.model.dto.UserDTO;
import ru.sfedu.messenger.model.dto.WithID;
import ru.sfedu.messenger.utils.ConfigurationUtil;

/**
 *
 * @author Vovka
 */
public class JDBCDataProvider<T extends WithID> implements IDataProvider<T>{
    
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(JDBCDataProvider.class);
    
    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    @Override
    public Result<T> saveRecord(T bean, EntityType type) {
        try {
            String query;
            switch(type) {
                case USER:
                    String login = ((UserDTO) bean).getLogin();
                    String name = ((UserDTO) bean).getName();
                    query = "INSERT INTO Users (login, name) \n" +
                            " VALUES ('" + login + "', '" + name + "');";
                    stmt.executeUpdate(query);
                    break;
                case MESSAGE:
                    String body = ((MessageDTO) bean).getBody();
                    Long author = Long.parseLong(((MessageDTO) bean).getAuthor());
                    String posted = String.valueOf(((MessageDTO) bean).getPosted());
                    String idTopic = String.valueOf(((MessageDTO) bean).getIdTopic());
                    query = "INSERT INTO Messages (body, author, posted, id_topic) \n" + 
                            " VALUES ('" + body + "', '" + author + 
                            "', '" + posted + "', '" + idTopic + "');";
                    stmt.executeUpdate(query);
                    break;
                case TOPIC:
                    String title = ((TopicDTO) bean).getTitle();
                    query = "INSERT INTO Topics (title) \n" + 
                            " VALUES ('" + title + "');";
                    stmt.executeUpdate(query);
                    break;
            }
        } catch (SQLException ex) {
            log.error(ex);
            return new Result<T>(ResultType.ERROR);
        }
            return new Result<T>(ResultType.SUCCESS);
    }

    @Override
    public Result<T> deleteRecord(T bean, EntityType type) {
        try {
            String query;
            switch (type) {
                case USER:
                    query = "delete from Users where id = " + bean.getId();
                    stmt.executeUpdate(query);
                    return new Result(ResultType.SUCCESS, null);
                case MESSAGE:
                    query = "delete from Messages where id = " + bean.getId();
                    stmt.executeUpdate(query);
                    return new Result(ResultType.SUCCESS, null);
                case TOPIC:
                    String subQuery = "delete from Messages where id_topic = " 
                            + bean.getId();
                    query = "delete from Topics where id = " + bean.getId();
                    stmt.executeUpdate(subQuery);
                    stmt.executeUpdate(query);
                    return new Result(ResultType.SUCCESS, null);
            }
        } catch (SQLException ex) {
            log.error(ex);
            return new Result(ResultType.ERROR, null);
        }
        return new Result(ResultType.NO_SUCH_OBJECT, null);
    }

    @Override
    public Result<T> getRecordById(long id, EntityType type) {
        try {
            String query;
            long iD= -1;
            switch(type) {
                case USER:
                    String login = null;
                    String name = null;
                    query = "select id, login, name from Users where id = " + id;
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.NO_SUCH_OBJECT, null);
                    else 
                        rs.previous();
                    iD = rs.getLong(1);
                    login = rs.getString(2);
                    name = rs.getString(3);
                    return (Result) new Result<UserDTO>(ResultType.SUCCESS, 
                            new UserDTO(iD, login, name));
                case MESSAGE:
                    String body = null;
                    String author = null;
                    Long posted = null;
                    Long idTopic = null;
                    query = "select id, body, author, posted, id_topic from Messages where id = " + id;
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.NO_SUCH_OBJECT, null);
                    else 
                        rs.previous();
                    while (rs.next()) {
                        iD = rs.getInt(1);
                        body = rs.getString(2);
                        author = rs.getString(3);
                        posted = rs.getLong(4);
                        idTopic = rs.getLong(5);
                    }
                    return (Result) new Result<MessageDTO>(ResultType.SUCCESS, 
                            new MessageDTO(iD, body, author, posted, idTopic));
                case TOPIC:
                    String title = null;
                    query = "select id, title from Topics where id = " + id;
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.NO_SUCH_OBJECT, null);
                    else 
                        rs.previous();
                    while(rs.next()) {
                        iD = rs.getInt(1);
                        title = rs.getString(2);
                    }
                    return (Result) new Result<TopicDTO>(ResultType.SUCCESS,
                            new TopicDTO(iD, title));
            }
        } catch (SQLException ex) {
            log.error(ex);
            return new Result(ResultType.ERROR, null);
        }
            return null;
    }

    @Override
    public Result initDataSource() {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(ConfigurationUtil.getConfigurationEntry(
                            Constants.DATA_BASE_URL),
                    ConfigurationUtil.getConfigurationEntry(
                            Constants.DATA_BASE_USER),
                    ConfigurationUtil.getConfigurationEntry(
                            Constants.DATA_BASE_PASSWORD));
            // getting Statement object to execute query
            stmt = con.createStatement();
            return new Result(ResultType.SUCCESS, null);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return new Result(ResultType.ERROR, null);
        } catch (IOException ex) {
            log.error(ex);
            return new Result(ResultType.ERROR, null);
        }
    }

    @Override
    public Result updateRecord(T bean, EntityType type) {
        switch(type) {
            case USER:
                List<UserDTO> userList = getAllRecords(type).getList();
                userList.stream().filter(t -> Objects.equals(t.getId(), 
                        bean.getId())).findFirst();
            case MESSAGE:
            case TOPIC:
        }
        return new Result(ResultType.SUCCESS, null);
    }

    @Override
    public Result getAllRecords(EntityType type) {
        try {
            String query;
            switch(type) {
                case USER:
                    List<UserDTO> userList = new ArrayList<>();
                    query = "select id, login, name from Users";
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.EMPTY_LIST);
                    else 
                        rs.previous();
                    while (rs.next()) {
                        userList.add(new UserDTO(rs.getLong(1), 
                                rs.getString(2), rs.getString(3)));
                    }
                    return new Result(ResultType.SUCCESS, userList);
                case MESSAGE:
                    List<MessageDTO> messageList = new ArrayList<>();
                    query = "select id, body, author, posted, id_topic from Messages";
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.EMPTY_LIST);
                    else 
                        rs.previous();
                    while (rs.next()) {
                        messageList.add(new MessageDTO(rs.getLong(1), rs.getString(2),
                                rs.getString(3), rs.getLong(4), rs.getLong(5)));
                    }
                    return new Result(ResultType.SUCCESS, messageList);
                case TOPIC:
                    List<TopicDTO> topicList = new ArrayList<>();
                    query = "select id, title from Topics";
                    rs = stmt.executeQuery(query);
                    if (!rs.next())
                        return new Result(ResultType.EMPTY_LIST, null);
                    else 
                        rs.previous();
                    while(rs.next()) {
                        topicList.add(new TopicDTO(rs.getLong(1), 
                                rs.getString(2)));
                    }
                    return new Result(ResultType.SUCCESS, topicList);
            }
        } catch (SQLException ex) {
            log.error(ex);
            return new Result(ResultType.ERROR);
        }
        return new Result(ResultType.EMPTY_LIST);
    }
    
    
    
    public void closeConnection() {
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
    }    
}
