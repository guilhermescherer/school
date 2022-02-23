package com.personal.school.converter.impl;

import com.personal.school.converter.Converter;
import com.personal.school.form.PeopleForm;
import com.personal.school.model.Address;
import com.personal.school.service.AddressService;
import com.personal.school.service.impl.DefaultAddressService;

import static com.personal.school.utils.SetterUtils.setter;

public class AddressConverter implements Converter<PeopleForm, Address> {

    private final AddressService addressService = new DefaultAddressService();

    @Override
    public Address convert(PeopleForm source) {
        return convert(new Address(), source);
    }

    @Override
    public Address convert(Address target, PeopleForm source) {
        Address address = new Address();

        if(source.getZipCode() != null) {
            address = addressService.getAddressByZipCode(source.getZipCode());
        }

        setter(target::setAddress, source.getAddress());
        setter(target::setCity, address.getCity());
        setter(target::setCountry, address.getCountry());
        setter(target::setZipCode, address.getZipCode());

        return target;
    }
}
