package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.enums.ConvertMethod;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.Address;
import com.personal.school.service.AddressService;
import com.personal.school.service.impl.DefaultAddressService;

import static com.personal.school.utils.ConverterUtils.isValidSet;

public class AddressConverter implements Converter<PeopleForm, Address> {

    private final AddressService addressService = new DefaultAddressService();

    @Override
    public Address convert(PeopleForm source) {
        final Address address = addressService.getAddressByZipCode(source.getZipCode());
        populateAddress(address, source.getAddress(), ConvertMethod.ADD);
        return address;
    }

    @Override
    public Address convert(Address target, PeopleForm source) {
        target = populateZipCode(target, source.getZipCode(), ConvertMethod.UPDATE);
        populateAddress(target, source.getAddress(), ConvertMethod.UPDATE);

        return target;
    }

    private void populateAddress(Address target, String address, ConvertMethod convertMethod) {
        if(isValidSet(address, convertMethod)) {
            target.setAddress(address);
        }
    }

    private Address populateZipCode(Address target, String zipCode, ConvertMethod convertMethod) {
        if(isValidSet(zipCode, convertMethod)) {
            target = addressService.getAddressByZipCode(zipCode);
        }
        return target;
    }
}
