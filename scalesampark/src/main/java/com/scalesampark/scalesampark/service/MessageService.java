package com.scalesampark.scalesampark.service;

import com.scalesampark.scalesampark.data.Message;
import com.scalesampark.scalesampark.model.MessageData;
import com.scalesampark.scalesampark.model.api.MessageRequest;
import com.scalesampark.scalesampark.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    EncryptionDecryptionService encryptionDecryptionService;

    @Transactional
    public String addMessage(MessageRequest messageRequest) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Message message = new Message();
        message.setArrivedAt(Calendar.getInstance().getTime());
        message.setUniqueId(UUID.randomUUID().toString());
        message.setUserUniqueId(messageRequest.getParticipant_uuid());
        message.setMessageType(messageRequest.getMessageType());
        message.setContent(encryptionDecryptionService.encrypt(messageRequest.getMessage()));

        messageRepository.save(message);

        return  message.getUniqueId();
    }

    public List<MessageData> getPendingMessages(Date lastFetched) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        List<MessageData> messageDataList = new ArrayList<>();
        List<Message> messages = messageRepository.findByArrivedAtAfter(lastFetched);

        for (Message message : messages) {
            try
            {
                MessageData messageData = new MessageData(message.getUserUniqueId(), message.getUniqueId(), message.getMessageType().ordinal(), encryptionDecryptionService.decrypt(message.getContent()));
                messageDataList.add(messageData);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw e;
            }
        }
        return messageDataList;
    }
}
