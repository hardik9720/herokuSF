package com.ccpoc.ccpoc.service;

import com.ccpoc.ccpoc.dao.UserDao;
import com.ccpoc.ccpoc.model.Person;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccpoc.ccpoc.model.Phone;
import com.ccpoc.ccpoc.model.User;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Service("PhoneService")
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private Connection connection;
    @Autowired
    UserDao userDao;  //Service which will do all data retrieval/manipulation work

    public PhoneServiceImpl() throws URISyntaxException {
        connection = DBUtility.getConnection();
    }
    
    public User getLoggedInUser() {
        User userObj = new User();
        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);
            userObj = userDao.findByUserName(userDetail.getUsername());
        }

        return userObj;
    }
    
    public String getPersonSFIDs(List<Person> personList) {
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            ids.append("'" + personList.get(i).getSfId() + "'");
            if (i < personList.size() - 1) {
                ids.append(",");
            }
        }

        return ids.toString();
    }
    
    public List<Person> getAllPersons() {
        User userObj = getLoggedInUser();
        List<Person> personList = new ArrayList<Person>();
        try {
            Statement personStatement = connection.createStatement();

            ResultSet rs = personStatement.executeQuery("select sfid from salesforce.person__c where custom_user__c= '" + userObj.getsfId() + "' ");
            while (rs.next()) {
                Person personObj = new Person();
                personObj.setSfId(rs.getString("sfid"));
                personList.add(personObj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return personList;
    }
    
    private static String createQuery(int length) {
        String query = "select * from salesforce.phones__c where person__c IN (";
        StringBuilder queryBuilder = new StringBuilder(query);
        for (int i = 0; i < length; i++) {
            queryBuilder.append(" ?");
            if (i != length - 1) {
                queryBuilder.append(",");
            }
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }

    @Override
    public List<Phone> findAllPhones() {

        User userObj = getLoggedInUser();

        List<Person> personList = new ArrayList<Person>();
        personList = getAllPersons();
        System.out.println("personList "+personList);
                
        String phonesQuery = createQuery(personList.size());

        List<Phone> phoneList = new ArrayList<Phone>();
        try {
            PreparedStatement statement = connection.prepareStatement(phonesQuery);
            for (int i = 1; i <= personList.size(); i++) {
                statement.setString(i, personList.get(personList.size() - i).getSfId());
            }
            System.out.println("statement "+statement.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Phone phone = new Phone();
                phone.setId(rs.getInt("id"));
                phone.setSfId(rs.getString("sfid"));
                phone.setAutoNumberName(rs.getString("name"));
                phone.setType(rs.getString("type__c"));
                phone.setPerson(rs.getString("person__c"));
                phone.setPhone(rs.getString("phone__c"));
                phone.setOwnerId(rs.getString("ownerid"));
                phoneList.add(phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneList;
    }

    @Override
    public Phone findById(Integer Id) {
        Phone phoneObj = new Phone();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.phones__c where id =" + Id);
            while (rs.next()) {
                phoneObj.setId(rs.getInt("id"));
                phoneObj.setSfId(rs.getString("sfid"));
                phoneObj.setAutoNumberName(rs.getString("name"));
                phoneObj.setType(rs.getString("type__c"));
                phoneObj.setPhone(rs.getString("phone__c"));
                phoneObj.setPerson(rs.getString("person__c"));
                phoneObj.setOwnerId(rs.getString("ownerId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phoneObj;
    }

    @Override
    public void updatePhone(Phone phoneObj) {
        try {
            System.out.println("Phone to be updated" + phoneObj);
            PreparedStatement preparedStatement = connection.prepareStatement("update salesforce.phones__c set type__c=?,phone__c=? where id=?");
            preparedStatement.setString(1, phoneObj.getType());
            preparedStatement.setString(2, phoneObj.getPhone());
            preparedStatement.setLong(3, phoneObj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
