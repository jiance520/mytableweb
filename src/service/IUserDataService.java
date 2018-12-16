package service;

import java.util.List;
import java.util.Map;

import entity.UserData;

public interface IUserDataService {
	UserData selectByPrimaryKey(String www,String username);
}
