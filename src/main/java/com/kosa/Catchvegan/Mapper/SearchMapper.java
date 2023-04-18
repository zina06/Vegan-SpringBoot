package com.kosa.Catchvegan.Mapper;

import com.kosa.Catchvegan.DTO.Criteria;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    public List<RestaurantDTO> restaurantAllShow();

    public List<RestaurantDTO> getAddressWithPagingList(Criteria cri);

    public List<RestaurantDTO> getLocationWithPagingList(Criteria cri);

    public Integer getLocationPagingCnt(Criteria cri);

    public Integer getAddressPagingCnt(Criteria cri);

    public List<String> getAddressList();
}
