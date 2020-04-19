package com.cognizant.fse.projectmgmt.dao;

import com.cognizant.fse.projectmgmt.model.TaskTbl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by Sanjay
 */
@Repository
public interface TaskDaoInterface extends CrudRepository<TaskTbl, Long> {

}
