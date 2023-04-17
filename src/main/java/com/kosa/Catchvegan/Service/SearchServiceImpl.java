package com.kosa.Catchvegan.Service;

import com.kosa.Catchvegan.DTO.Criteria;
import com.kosa.Catchvegan.DTO.RestaurantDTO;
import com.kosa.Catchvegan.Mapper.SearchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService{
    @Autowired
    private SearchMapper mapper;
    @Override
    public List<RestaurantDTO> restaurantAllShow() {
        return mapper.restaurantAllShow();
    };
    @Override
    public List<RestaurantDTO> getAddressWithPagingList(Criteria cri){
        return mapper.getAddressWithPagingList(cri);
    };
    @Override    public List<RestaurantDTO> getLocationWithPagingList(Criteria cri){
        return mapper.getLocationWithPagingList(cri);
    }

    @Override    public Integer getLocationPagingCnt(Criteria cri){
        return mapper.getLocationPagingCnt(cri);
    }

    @Override    public Integer getAddressPagingCnt(Criteria cri){
        return mapper.getAddressPagingCnt(cri);
    }

    @Override
    public List<String> getAddressList(){
        return mapper.getAddressList();
    }
}
