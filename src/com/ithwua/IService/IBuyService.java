package com.ithwua.IService;

import com.ithwua.bean.User;

public interface IBuyService {

	boolean doBuy(String[] cartIds, User userBack);
}
