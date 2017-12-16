/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.messenger.model.dto.EntityType;
import static ru.sfedu.messenger.model.dto.EntityType.USER;
import ru.sfedu.messenger.model.dto.MessageDTO;
import ru.sfedu.messenger.model.dto.TopicDTO;
import ru.sfedu.messenger.model.dto.UserDTO;

/**
 *
 * @author Vovka
 */
public class JDBCDataProviderTest {
    
    JDBCDataProvider instance;
    
    public JDBCDataProviderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of saveRecord method, of class JDBCDataProvider.
     */
    @Test
    public void testSaveRecord() {
        System.out.println("saveRecord");
//        TopicDTO bean = new TopicDTO();
        MessageDTO bean = new MessageDTO();
//        bean.setId(1L);
//        bean.setTitle("What the amazing title!?");
        bean.setBody("This message is still alive too 2");
        bean.setAuthor("1");
        bean.setPosted(11771231283812L);
        bean.setIdTopic(2L);
        EntityType type = EntityType.MESSAGE;
        JDBCDataProvider instance = new JDBCDataProvider();
        
        instance.initDataSource();
        Result expResult = null;
        Result result = instance.saveRecord(bean, type);
        instance.closeConnection();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRecord method, of class JDBCDataProvider.
     */
    @Test
    public void testDeleteRecord() {
        System.out.println("deleteRecord");
        TopicDTO bean = new TopicDTO(1, "pohnah");
        EntityType type = EntityType.TOPIC;
        JDBCDataProvider instance = new JDBCDataProvider();
        instance.initDataSource();
        Result result = instance.deleteRecord(bean, type);
        instance.closeConnection();
        System.out.println(result.getResType());
//        Result expResult = null;
//        Result result = instance.deleteRecord(bean, type);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecordById method, of class JDBCDataProvider.
     */
    @Test
    public void testGetRecordById() {
        System.out.println("getRecordById");
        long id = 1L;
        EntityType type = EntityType.USER;
        JDBCDataProvider instance = new JDBCDataProvider();
        instance.initDataSource();
        Result expResult = null;
        Result<UserDTO> result = instance.getRecordById(id, type);
        System.out.println(result.getResObject().toString());
        System.out.println(result.getResType());
        instance.closeConnection();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetAllRecords() {
        System.out.println("getRecordById");
        EntityType type = EntityType.TOPIC;
        JDBCDataProvider instance = new JDBCDataProvider();
        instance.initDataSource();
        Result expResult = null;
        Result<TopicDTO> result = instance.getAllRecords(type);
        System.out.println(result.getList().size());
        result.getList().stream().forEach((t) -> {
            System.out.println(t.toString());
        });
//        System.out.println(result.getResObject().toString());
        System.out.println(result.getResType());
        instance.closeConnection();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of initDataSource method, of class JDBCDataProvider.
     */
    @Test
    public void testInitDataSource() {
        System.out.println("initDataSource");
        EntityType type = null;
        JDBCDataProvider instance = new JDBCDataProvider();
        instance.initDataSource();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of closeConnection method, of class JDBCDataProvider.
     */
    @Test
    public void testCloseConnection() {
        System.out.println("closeConnection");
        EntityType type = null;
        JDBCDataProvider instance = new JDBCDataProvider();
        instance.initDataSource();
        instance.closeConnection();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
}
