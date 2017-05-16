package com.ccpoc.ccpoc.service;

import com.ccpoc.ccpoc.model.Phone;
import java.util.List;


public interface PhoneService {
    List<Phone> findAllPhones();
    Phone findById(Integer Id);
    void updatePhone(Phone phoneObj);
}
