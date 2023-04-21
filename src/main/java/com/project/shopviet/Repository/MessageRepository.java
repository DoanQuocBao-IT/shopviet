package com.project.shopviet.Repository;

import com.project.shopviet.Model.Messages;
import com.project.shopviet.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Messages,Integer> {
    List<Messages> findBySenderAndReceiverOrSenderAndReceiverOrderByCreateAtAsc(User sender,User receiver,User receiver1,User sender1);
}
