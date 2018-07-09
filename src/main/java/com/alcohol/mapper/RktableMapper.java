package com.alcohol.mapper;

import com.alcohol.pojo.Rktable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RktableMapper {
    /**
     * 查询全部
     * @return
     */
    List<Rktable> SelAll(@Param("productName") String productName);
}
