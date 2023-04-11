package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.ManagerDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ManagerMapper {

    public List<ManagerDTO> findAllManagers();
    public Long createManager(ManagerDTO manager);

    public void managerRole(@Param("manageridx") Integer manageridx);

    public ManagerDTO managerGetUserByIdAndPassword(@Param("id") String id);
    public ManagerDTO managerIdGet(@Param("id") String id);
}
