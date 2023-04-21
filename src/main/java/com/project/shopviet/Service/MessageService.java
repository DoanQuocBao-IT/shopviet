package com.project.shopviet.Service;

import com.project.shopviet.DTO.MessageDto;
import com.project.shopviet.Model.Messages;
import com.project.shopviet.Model.User;

import java.util.List;

public interface MessageService {
    List<MessageDto> findBySenderAndReceiverOrderByCreateAtAsc(int receiver_id);
    Messages sendMessage(Messages messages,int receiver_id);
}
