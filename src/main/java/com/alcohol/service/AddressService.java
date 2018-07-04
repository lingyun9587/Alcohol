package com.alcohol.service;

import com.alcohol.pojo.Address;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {
    /**
     * 添加收货地址
     * @param address
     * @return
     */
    int insertAddress(Address address);

    /**
     * 修改收货地址
     * @param address
     * @return
     */
    int upAdd(Address address);

    /**
     * 查询该用户的所有收货地址
     * @return
     */
    List<Address> listAdd(Long userId);

    /**
     * 查询单个收货地址
     * @param addressId
     * @return
     */
    Address SelAdd(Long addressId);
    /**
     * 修改收货地址
     * @param address
     * @return
     */
    int updataAdd(Address address);
    /**
     * 删除收货地址
     * @param addressId
     * @return
     */
    int delAdd(Long addressId);
    /**
     *点击设置默认地址
     * @param addressId
     * @return
     */
    int upMoAdd(Long addressId);
}
