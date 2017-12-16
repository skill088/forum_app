/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ru.sfedu.messenger.Constants;
import ru.sfedu.messenger.model.dto.MessageList;
import ru.sfedu.messenger.model.dto.EntityType;
import ru.sfedu.messenger.model.dto.MessageDTO;
import ru.sfedu.messenger.model.dto.TopicDTO;
import ru.sfedu.messenger.model.dto.TopicList;
import ru.sfedu.messenger.model.dto.UserDTO;
import ru.sfedu.messenger.model.dto.UserList;
import ru.sfedu.messenger.model.dto.WithID;
import ru.sfedu.messenger.utils.ConfigurationUtil;

/**
 *
 * @author Vovka
 */
public class XMLDataProvider<T extends WithID> implements IDataProvider<T>{
    
    private static org.apache.log4j.Logger log = org.apache.log4j
            .Logger.getLogger(XMLDataProvider.class);
    Serializer serializer = null;
    File source = null;
    MessageList messageList = null;
    UserList userList = null;
    TopicList topicList = null;

    @Override
    public Result<T> saveRecord(T bean, EntityType type) {
        serializer = new Persister();
        File result = null;
        switch(type) {
            case MESSAGE:
                try {
                    result = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_MESSAGES));
                    messageList = serializer.read(MessageList.class, result);
                    this.messageList.getList().add((MessageDTO) bean);
                    serializer.write(messageList, result);
                    return new Result<T>(ResultType.SUCCESS, null);
                } catch(XMLStreamException ex) {
                    try {
                        this.messageList = new MessageList();
                        this.messageList.getList().add((MessageDTO) bean);
                        serializer.write(messageList, result);
                        return new Result<T>(ResultType.SUCCESS, null);
                        } catch (Exception ex1) {
                            log.error(ex1);
                        }
                }
                catch (Exception ex) {
                    log.info(ex);
                    return new Result<T>(ResultType.ERROR, null);
                }
            case USER:
                try {
                    result = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_USERS));
                    userList = serializer.read(UserList.class, result);
                    this.userList.getList().add((UserDTO) bean);
                    serializer.write(userList, result);
                    return new Result<T>(ResultType.SUCCESS, null);
                } catch(XMLStreamException ex) {
                    try {
                        this.userList = new UserList();
                        this.userList.getList().add((UserDTO) bean);
                        serializer.write(userList, result);
                        return new Result<T>(ResultType.SUCCESS, null);
                        } catch (Exception ex1) {
                            log.error(ex1);
                        }
                }
                catch (Exception ex) {
                    log.info(ex.getClass());
                    return new Result<T>(ResultType.ERROR, null);
                }
            case TOPIC:
                try {
                    result = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_TOPICS));
                    topicList = serializer.read(TopicList.class, result);
                    this.topicList.getList().add((TopicDTO) bean);
                    serializer.write(topicList, result);
                    return new Result<T>(ResultType.SUCCESS, null);
                } catch(XMLStreamException ex) {
                    try {
                        this.topicList = new TopicList();
                        this.topicList.getList().add((TopicDTO) bean);
                        serializer.write(topicList, result);
                        return new Result<T>(ResultType.SUCCESS, null);
                        } catch (Exception ex1) {
                            log.error(ex1);
                        }
                } 
                catch (Exception ex) {
                    log.info(ex);
                    return new Result<T>(ResultType.ERROR, null);
                }
        }
        return new Result<T>(ResultType.ERROR, null);
    }

    @Override
    public Result<T> deleteRecord(T bean, EntityType type) {
        serializer = new Persister();
        switch(type) {
            case USER:
                try {
                    UserDTO candidat = null;
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_USERS));
                    userList = serializer.read(UserList.class, source);
                    candidat = userList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), bean.getId()))
                            .findFirst().orElse(null);
                    if (candidat!=null) {
                        userList.getList().remove(candidat);
                        serializer.write(userList, source);
                        return new Result<T>(ResultType.SUCCESS, null);
                    }
                    else {
                        return new Result<T>(ResultType.NO_SUCH_OBJECT, null);
                    }
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    return new Result<T>(ResultType.ERROR, null);
                }        
            case MESSAGE:
                try {
                    MessageDTO candidat = null;
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_MESSAGES));
                    messageList = serializer.read(MessageList.class, source);
                    candidat = messageList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), bean.getId()))
                            .findFirst().orElse(null);
                    if (candidat!=null) {
                        messageList.getList().remove(candidat);
                        serializer.write(messageList, source);
                        return new Result<T>(ResultType.SUCCESS, null);
                    }
                    else {
                        return new Result<T>(ResultType.NO_SUCH_OBJECT, null);
                    }
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    ex.printStackTrace();
                    return new Result<T>(ResultType.ERROR, null);
                }
            case TOPIC:
                try {
                    TopicDTO candidat = null;
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_TOPICS));
                    topicList = serializer.read(TopicList.class, source);
                    candidat = topicList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), bean.getId()))
                            .findFirst().orElse(null);
                    if (candidat!=null) {
                        topicList.getList().remove(candidat);
                        serializer.write(topicList, source);
                        return new Result<T>(ResultType.SUCCESS, null);
                    }
                    else {
                        return new Result<T>(ResultType.NO_SUCH_OBJECT, null);
                    }
                    
                    
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    return new Result<T>(ResultType.ERROR, null);
                }
        }
        return new Result<T>(ResultType.ERROR, null);
    }

    @Override
    public Result<T> getRecordById(long id, EntityType type) {
        serializer = new Persister();
        T res = null;
        switch(type) {
            case USER:
                try {
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_USERS));
                    userList = serializer.read(UserList.class, source);
                    log.info(userList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), id)).findFirst()
                            .orElse(null));
                    res = (T) userList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), id)).findFirst()
                            .orElse(null);
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                }        
                break;
            case MESSAGE:
                try {
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_MESSAGES));
                    messageList = serializer.read(MessageList.class, source);
                    res = (T) messageList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), id)).findFirst().orElse(null);
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case TOPIC:
                try {
                    source = new File(ConfigurationUtil
                            .getConfigurationEntry(Constants.XML_PATH_TOPICS));
                    topicList = serializer.read(TopicList.class, source);
                    res =  (T) topicList.getList().stream().filter(t -> 
                            Objects.equals(t.getId(), id)).findFirst().orElse(null);
                } catch (Exception ex) {
                    log.info(ex.getMessage());
                    return new Result<T>(ResultType.ERROR, null);
                }
        }
        if (res != null ) {
            return new Result<T>(ResultType.SUCCESS, res);
        }
        else 
            return new Result<T>(ResultType.NO_SUCH_OBJECT, null);
    }

    @Override
    public Result initDataSource() {
        return null;
    }
    
}
