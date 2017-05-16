package com.ccpoc.ccpoc.service;

import com.ccpoc.ccpoc.dao.UserDao;
import com.ccpoc.ccpoc.model.Address;
import com.ccpoc.ccpoc.model.Person;
import com.ccpoc.ccpoc.model.User;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

@Service("AddressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    private Connection connection;
    @Autowired
    UserDao userDao;
    public AddressServiceImpl() throws URISyntaxException {
        connection = DBUtility.getConnection();
    }

    @Override
    public List<Address> findAllAddress() {
        
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

            ResultSet rs = personStatement.executeQuery("select sfid from salesforce.person__c where custom_user__c= '"+ userObj.getsfId() +"' ");
            while (rs.next()) {
                person.setSfId(rs.getString("sfid"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddressServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Address> addressList = new ArrayList<Address>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.address__c where person__c = '" + person.getSfId() + "' ");
            while (rs.next()) {
                Address address = new Address();
                address.setId(rs.getInt("id"));
                address.setSfId(rs.getString("sfid"));
                address.setStatename(rs.getString("state_province__c"));
                address.setAddressline1(rs.getString("address_line_1__c"));
                address.setAddressline2(rs.getString("address_line_2__c"));
                address.setPersonId(rs.getString("person__c"));
                address.setZipCode(rs.getString("zip_postal_code__c"));
                address.setOwnerId(rs.getString("ownerid"));
                address.setAutoNumberName(rs.getString("name"));

                addressList.add(address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public Address findById(Integer Id) {
        Address addressObj = new Address();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from salesforce.address__c where id =" + Id);
            while (rs.next()) {
                addressObj.setId(rs.getInt("id"));
                addressObj.setSfId(rs.getString("sfid"));
                addressObj.setStatename(rs.getString("state_province__c"));
                addressObj.setAddressline1(rs.getString("address_line_1__c"));
                addressObj.setAddressline2(rs.getString("address_line_2__c"));
                addressObj.setPersonId(rs.getString("person__c"));
                addressObj.setZipCode(rs.getString("zip_postal_code__c"));
                addressObj.setOwnerId(rs.getString("ownerid"));
                addressObj.setAutoNumberName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressObj;
    }

    @Override
    public void updateAddress(Address addressObj) {
        try {
            System.out.println("Address to be updated" + addressObj);
            PreparedStatement preparedStatement = connection.prepareStatement("update salesforce.address__c set state_province__c=?,address_line_1__c=?,address_line_2__c=?,zip_postal_code__c=?  where id=?");
            preparedStatement.setString(1, addressObj.getStatename());
            preparedStatement.setString(2, addressObj.getAddressline1());
            preparedStatement.setString(3, addressObj.getAddressline2());
            preparedStatement.setString(4, addressObj.getZipCode());
            preparedStatement.setLong(5, addressObj.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
