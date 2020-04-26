package com.cognizant.fse.projectmgmt.service;

import com.cognizant.fse.projectmgmt.dao.UserDaoInterface;
import com.cognizant.fse.projectmgmt.model.UserTbl;
import com.cognizant.fse.projectmgmt.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sanjay on 10/2/2018.
 */
@Service
public class UserService {

	private UserDaoInterface userDao;


	@Autowired
	public UserService(UserDaoInterface userDao) {
		this.userDao = userDao;
	}


	@Transactional
	public String addUpdateUser(User user) {
		long userId = user.getUserId();
		Optional<UserTbl> userObj = null;
		UserTbl userTbl = null;
		if (userId > 0) {

			userObj = userDao.findById(userId);
			userTbl = userObj.get();
			userTbl.setUserId(userId);

		} else {
			userTbl = new UserTbl();
		}

		userTbl.setUserName(user.getUserName());
		userTbl.setFirstName(user.getFirstName());
		userTbl.setLastName(user.getLastName());

		userDao.save(userTbl);

		return "Successful";
	}


	@Transactional
	public List<UserTbl> getUser() {
		List<UserTbl> userList = (List<UserTbl>) userDao.findAll();

		return userList;
	}

	@Transactional
	public UserTbl getUserById(long userId) {
		Optional<UserTbl> userObj = userDao.findById(userId);
		UserTbl user = (UserTbl) userObj.get();

		return user;
	}

	@Transactional
	public void deleteUser(long userId) {

		userDao.deleteById(userId);
	}

	@Transactional
	public List<UserTbl> findUser(String searchString) {

		return userDao.findUserBySearchString(searchString);
	}

	@Transactional
	public List<UserTbl> sortUser(String sortField) {

		return (List<UserTbl>)userDao.findAll(Sort.by(""+sortField+""));
	}


}
