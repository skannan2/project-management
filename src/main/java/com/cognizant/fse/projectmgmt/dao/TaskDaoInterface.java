package com.cognizant.fse.projectmgmt.dao;

import com.cognizant.fse.projectmgmt.model.TaskTbl;
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
public interface TaskDaoInterface extends PagingAndSortingRepository<TaskTbl, Long> {
    @Query("select count(t) from TaskTbl t where t.projectTbl.projectId=:projectId")
    public int countTask(long projectId);

    @Query("select count(t) from TaskTbl t where t.status=:status")
    public int countCompleteTask(String status);
}
