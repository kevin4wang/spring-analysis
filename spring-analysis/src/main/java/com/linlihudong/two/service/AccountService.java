package com.linlihudong.two.service;/**
 * Created by Administrator on 2018/8/28.
 */

import java.util.List;

/**
 * @author Tommy
 *         Created by Tommy on 2018/8/28
 **/
public interface AccountService {
    public void addAccount(String name, int initMenoy);

    List<Account> queryAccount(String name);

    int updateAccount(String name, int money);
}
