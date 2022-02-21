package com.personal.school.service;

import com.personal.school.model.Address;

public interface AddressService {

    Address getAddressByZipCode(String cep);
}
