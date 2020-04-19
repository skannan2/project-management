package com.cognizant.fse.projectmgmt.dao;

import com.cognizant.fse.projectmgmt.model.ParentTaskTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Sanjay
 */
@Repository
public interface ParentTaskDaoInterface extends JpaRepository<ParentTaskTbl, Long> {

	@Query("select c from ParentTaskTbl c where c.parentTask=:name")
    public List<ParentTaskTbl> findParentTaskByName(String name);

}
