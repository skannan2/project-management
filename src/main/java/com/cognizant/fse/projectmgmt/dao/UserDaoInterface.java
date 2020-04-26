package com.cognizant.fse.projectmgmt.dao;

import com.cognizant.fse.projectmgmt.model.UserTbl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Sanjay
 */
@Repository
public interface UserDaoInterface extends PagingAndSortingRepository<UserTbl, Long> {
    @Query("select u from UserTbl u where lower(u.firstName) like lower(concat('%', :searchString,'%'))"+
            "or lower(u.lastName) like lower(concat('%', :searchString,'%'))"+
            "or lower(u.userName) like lower(concat('%', :searchString,'%'))")
    public List<UserTbl> findUserBySearchString(String searchString);

}
