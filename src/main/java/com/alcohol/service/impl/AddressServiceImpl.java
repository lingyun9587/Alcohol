package com.alcohol.service.impl;

import com.alcohol.mapper.AddressMapper;
import com.alcohol.pojo.Address;
import com.alcohol.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Override
    public int insertAddress(Address address) {
        return addressMapper.insertAddress(address);
    }

    @Override
    public int upAdd(Address address) {
        return addressMapper.upAdd(address);
    }

    @Override
    public List<Address> listAdd(Long userId) {
        return addressMapper.listAdd(userId);
    }

    @Override
    public Address SelAdd(Long addressId) {
        return addressMapper.SelAdd(addressId);
    }

    @Override
    public int updataAdd(Address address) {
        return addressMapper.updataAdd(address);
    }

    @Override
    public int delAdd(Long addressId) {
        return addressMapper.delAdd(addressId);
    }
}
