/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import java.util.List;
import ru.sfedu.messenger.Constants;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import ru.sfedu.messenger.model.dto.EntityType;
import ru.sfedu.messenger.model.dto.MessageDTO;
import ru.sfedu.messenger.model.dto.PostItemDTO;
import ru.sfedu.messenger.model.dto.TopicDTO;
import ru.sfedu.messenger.model.dto.UserDTO;
import ru.sfedu.messenger.model.dto.WithID;
import ru.sfedu.messenger.utils.ConfigurationUtil;

/**
 *
 * @author Vovka
 */
public class CSVDataProvider<T extends WithID> implements IDataProvider<T>{

    private static Logger log = Logger.getLogger(CSVDataProvider.class);
    CsvToBeanBuilder<T> builder;
    FileWriter writer;
    StatefulBeanToCsv beanToCsv;
    
    public void initBuilder(EntityType type) {
        switch(type) {
            case USER:
                try {
                    builder = new CsvToBeanBuilder(new FileReader(
                        ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_USERS)))
                    .withType(UserDTO.class);
                } catch (Exception ex) {
                    log.debug(Priority.DEBUG, ex);
                }
                break;
            case MESSAGE:
                try {
                    builder = new CsvToBeanBuilder(new FileReader(
                        ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_MESSAGES)))
                    .withType(PostItemDTO.class);
                } catch (Exception ex) {
                    log.debug(Priority.DEBUG, ex);
                }
                break;
            case TOPIC:
                try {
                    builder = new CsvToBeanBuilder(new FileReader(
                        ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_TOPICS)))
                    .withType(TopicDTO.class);
                } catch (Exception ex) {
                    log.debug(Priority.DEBUG, ex);
                }
                break;
        }
    }
    
    public void initBeanToCsv(EntityType type, Boolean override) {
        switch(type) {
            case USER:
                try {
                    writer = new FileWriter(ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_USERS), override);
                    beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                } catch (IOException ex) {
                    log.info(ex);
                }
                break;
            case MESSAGE:
                try {
                    writer = new FileWriter(ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_MESSAGES), override);
                    beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                } catch (IOException ex) {
                    log.info(ex);
                }
                break;
            case TOPIC:
                try {
                    writer = new FileWriter(ConfigurationUtil.getConfigurationEntry(
                            Constants.CSV_PATH_TOPICS), override);
                    beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
                } catch (IOException ex) {
                    log.info(ex);
                }
                break;
        }
    }
    
    @Override
    public Result<T> saveRecord(T bean, EntityType type) {
        if (getRecordById(bean.getId(), type) != null) {
            log.info("Such id already exists");
            return new Result<T>(ResultType.DUPLICATE_ID);
        }
        initBeanToCsv(type, true);
        log.info(bean.toString());
        try {
            beanToCsv.write(bean);
            writer.close();
        } catch(Exception e) {
            log.info(e.getMessage());
            return new Result<T>(ResultType.ERROR);
        }
        return new Result<T>(ResultType.SUCCESS);
    }

    @Override
    public Result<T> deleteRecord(T bean, EntityType type) {
        initBuilder(type);
        try {
            List<T> list = builder.build().parse();
            T candidat = null;
            candidat = list.stream().filter(t -> 
                    Objects.equals(t.getId(), bean.getId())).findFirst()
                    .orElse(null);
            list.remove(candidat);
            initBeanToCsv(type, false);
            beanToCsv.write(list);
            writer.close();
            return new Result<T>(ResultType.SUCCESS);   
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            return new Result<T>(ResultType.ERROR);
        }
    }

    @Override
    public Result<T> getRecordById(long id, EntityType type) {
        initBuilder(type);
        try {
            List<T> list = builder.build().parse();
            T res;
            log.info(list.stream().filter(t -> 
                    Objects.equals(t.getId(), id)).findFirst()
                    .orElse(null));
            res = list.stream().filter(t -> 
                    Objects.equals(t.getId(), id)).findFirst()
                    .orElse(null);
            return res==null?(new Result<T>(ResultType.NO_SUCH_OBJECT))
                    :(new Result<T>(ResultType.SUCCESS, res));
        } catch (Exception ex) {
            log.debug(ex.getMessage());
            return new Result<T>(ResultType.ERROR);
        }
    }

    @Override
    public Result initDataSource() {
        return null;
    }
}
  
