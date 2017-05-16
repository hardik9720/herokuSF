package com.ccpoc.ccpoc.service;

import com.ccpoc.ccpoc.dao.UserDao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccpoc.ccpoc.model.Person;
import com.ccpoc.ccpoc.model.User;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service("PersonService")
@Transactional
public class PersonServiceImpl implements PersonService {

    private Connection connection;
    @Autowired
    UserDao userDao;  //Service which will do all data retrieval/manipulation work

    public PersonServiceImpl() throws URISyntaxException {
        connection = DBUtility.getConnection();
    }

    @Override
    public List<Person> findAllUsers() {
        List<Person> personList = new ArrayList<Person>();
        
        User userObj = new User();
        try // check if user is login
        {
            // check if user is login
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                UserDetails userDetail = (UserDetails) auth.getPrincipal();
                System.out.println(userDetail);
                userObj = userDao.findByUserName(userDetail.getUsername());
            }
            
            System.out.println("user Obj sfId"+userObj.getsfId());
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.person__c where custom_user__c= '"+ userObj.getsfId() +"' ");
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setFirstname(rs.getString("first_name__c"));
                person.setLastname(rs.getString("last_name__c"));
                person.setFullname(rs.getString("full_name__c"));
                person.setAutoNumberName(rs.getString("name"));
                person.setBirthdate(rs.getDate("birthdate__c"));
                person.setAmount(rs.getDouble("amount__c"));
                person.setSfId(rs.getString("sfid"));
                person.setDescription(rs.getString("description__c"));
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public Person findById(Integer Id) {
        Person person = new Person();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.person__c where id =" + Id);
            while (rs.next()) {
                person.setId(rs.getInt("id"));
                person.setFirstname(rs.getString("first_name__c"));
                person.setLastname(rs.getString("last_name__c"));
                person.setFullname(rs.getString("full_name__c"));
                person.setAutoNumberName(rs.getString("name"));
                person.setBirthdate(rs.getDate("birthdate__c"));
                person.setAmount(rs.getDouble("amount__c"));
                person.setSfId(rs.getString("sfid"));
                person.setDescription(rs.getString("description__c"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void updatePerson(Person personObj) {
        try {
            System.out.println("Person to be updated" + personObj);
            PreparedStatement preparedStatement = connection.prepareStatement("update salesforce.person__c set first_name__c=?,last_name__c=?,birthdate__c=?  where id=?");
            preparedStatement.setString(1, personObj.getFirstname());
            preparedStatement.setString(2, personObj.getLastname());
            preparedStatement.setDate(3, convertJavaDateToSqlDate(personObj.getBirthdate()));
            preparedStatement.setLong(4, personObj.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(PersonServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date dateToConvert) throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String formatted = format1.format(dateToConvert);
        System.out.println("formatted date" + formatted);

        java.util.Date utilDate = format1.parse(formatted);
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        System.out.println("utilDate:" + utilDate);
        System.out.println("sqlDate:" + sqlDate);
        return sqlDate;
    }

}
