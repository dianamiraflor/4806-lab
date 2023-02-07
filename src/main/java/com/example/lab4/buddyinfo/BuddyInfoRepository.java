package com.example.lab4.buddyinfo;

import java.util.List;

import com.example.lab4.buddyinfo.BuddyInfo;
import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    BuddyInfo findById(long id);
    List<BuddyInfo> findByName(String name);
    List<BuddyInfo> findByAddress(String address);
    List<BuddyInfo> findByPhoneNumber(String phoneNumber);
}