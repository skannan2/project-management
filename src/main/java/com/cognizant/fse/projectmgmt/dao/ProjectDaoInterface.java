package com.cognizant.fse.projectmgmt.dao;

import com.cognizant.fse.projectmgmt.model.ProjectTbl;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Sanjay
 */
@Repository
public interface ProjectDaoInterface extends PagingAndSortingRepository<ProjectTbl, Long> {

    @Query("select p from ProjectTbl p where lower(p.projectName) like lower(concat('%', :searchString,'%'))")
    public List<ProjectTbl> findProjectBySearchString(String searchString);

}
