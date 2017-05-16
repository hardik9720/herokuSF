package com.ccpoc.ccpoc.service;

import com.ccpoc.ccpoc.model.Address;
import java.util.List;


public interface AddressService {
    List<Address> findAllAddress();
    Address findById(Integer Id);
    void updateAddress(Address addressObj);
}
