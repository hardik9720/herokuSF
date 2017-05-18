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

    @Override
    public List<Phone> findAllPhones() {
        
                User userObj = new User();
        // check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);
            userObj = userDao.findByUserName(userDetail.getUsername());
        }

        Person person = new Person();
        try {
            Statement personStatement = connection.createStatement();
            
            ResultSet rs = personStatement.executeQuery("select sfid from salesforce.person__c where custom_user__c= '"+ userObj.getsfId() +"'");
            while (rs.next()) {
                person.setSfId(rs.getString("sfid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        List<Phone> phoneList = new ArrayList<Phone>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.phones__c where person__c = '"+person.getSfId()+"'");
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
