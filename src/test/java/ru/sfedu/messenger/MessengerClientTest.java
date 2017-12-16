/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.sfedu.messenger;

import com.mysql.cj.api.jdbc.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import ru.sfedu.messenger.Constants.*;
import ru.sfedu.messenger.utils.ConfigurationUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.sfedu.messenger.model.CSVDataProvider;
import ru.sfedu.messenger.model.XMLDataProvider;
import ru.sfedu.messenger.model.dto.EntityType;
import ru.sfedu.messenger.model.dto.MessageDTO;
import ru.sfedu.messenger.model.dto.PostItemDTO;
import ru.sfedu.messenger.model.dto.UserDTO;

/**
 *
 * @author Vovka
 */
public class MessengerClientTest {
    
    public MessengerClientTest() {
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
     * Test of logBasicSystemInfo method, of class MessengerClient.
     */
    @Test
    public void testLogBasicSystemInfo() {
        System.out.println("logBasicSystemInfo");
        MessengerClient instance = new MessengerClient();
        instance.logBasicSystemInfo();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    @Test
    public void testFirstMethod() throws IOException {
        MessengerClient client = new MessengerClient();
//        client.logBasicSystemInfo();
        System.out.println(ConfigurationUtil.getConfigurationEntry(Constants.CSV_PATH_MESSAGES));
        
        CSVDataProvider<UserDTO> csvDataProvider = new CSVDataProvider();
        EntityType type = EntityType.USER;
//        csvDataProvider.initDataSource(type);
        csvDataProvider.getRecordById(2L, type);
        System.out.println(csvDataProvider.getRecordById(2L, type).getResType());
//        csvDataProvider.getRecordById(3);
//        csvDataProvider.saveRecord(new PostItemDTO(13l,"here","is","some","test"), type);
//        csvDataProvider.saveRecord(new UserDTO(3l,"jake","jake87"), type);
        
//        csvDataProvider.deleteRecord(new PostItemDTO(12l,"here","is","some","test"), type);
//          MessengerClient obj = new B();
    }
    
    @Test
    public void xmlTests() throws IOException {
        XMLDataProvider<UserDTO> xMLDataProvider = new XMLDataProvider();
        xMLDataProvider.getRecordById(2l, EntityType.USER);
        System.out.println(xMLDataProvider.getRecordById(5l, EntityType.USER).getResType());
//        xMLDataProvider.saveRecord(new UserDTO(4l, "Misha", "mikhail007"), EntityType.USER);
//        xMLDataProvider.deleteRecord(new UserDTO(3l, "Misha", "mikhail007"), EntityType.USER);
//        xMLDataProvider.deleteRecord(xMLDataProvider.getRecordById(2, EntityType.USER).getResObject(), EntityType.USER);
//        xMLDataProvider.saveRecord(new MessageDTO(5l,"some second body", "some another author", 1312312312l), EntityType.MESSAGE);
    }
    
    @Test
    public void jdbcTest() {
        final String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        final String user = "root";
        final String password = "";
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String query = "select id, name, author from books";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

//             getting Statement object to execute query
            stmt = (Statement) con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String author = rs.getString(3);
                System.out.printf("id: %d, name: %s, author: %s %n", id, name, author);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
