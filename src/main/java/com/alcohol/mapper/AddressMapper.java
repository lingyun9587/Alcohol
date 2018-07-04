package com.alcohol.mapper;

import com.alcohol.pojo.Address;

import java.util.List;

public interface AddressMapper {
    /**
     * 添加收货地址
     * @param address
     * @return
     */
    int insertAddress(Address address);

    /**
     * 修改收货地址默认路径
     * @param address
     * @return
     */
    int upAdd(Address address);

    /**
     * 查询全部收货地址
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
}
