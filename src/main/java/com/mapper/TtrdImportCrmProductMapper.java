package com.mapper;

import com.domain.TtrdImportCrmProduct;

public interface TtrdImportCrmProductMapper {
    int deleteByPrimaryKey(Long dateId);

    int insert(TtrdImportCrmProduct record);

    int insertSelective(TtrdImportCrmProduct record);

    TtrdImportCrmProduct selectByPrimaryKey(Long dateId);

    int updateByPrimaryKeySelective(TtrdImportCrmProduct record);

    int updateByPrimaryKey(TtrdImportCrmProduct record);
}