package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.Criteria;
import com.kosa.Catchvegan.DTO.RestaurantDTO;

import java.util.List;

public interface SearchService {
    public List<RestaurantDTO> restaurantAllShow();
    public List<RestaurantDTO> getAddressWithPagingList(Criteria cri);
    public List<RestaurantDTO> getLocationWithPagingList(Criteria cri);
    public Integer getLocationPagingCnt(Criteria cri);
    public Integer getAddressPagingCnt(Criteria cri);
    public List<String> getAddressList();
}
