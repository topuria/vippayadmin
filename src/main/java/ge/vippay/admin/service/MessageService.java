package ge.vippay.admin.service;

import ge.vippay.admin.entity.Message;
import ge.vippay.admin.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Page<Message> searchMessage(String query, Pageable pageable) {
        return messageRepository.searchMessage(query, pageable);
    }

    public Message getMessageById(long id) {
        return messageRepository.getReferenceById(id);
    }

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessage(long id, Message message) {
        Message oldMessage = getMessageById(id);
        oldMessage.setMessage(message.getMessage());
        oldMessage.setEmail(message.getEmail());
        oldMessage.setName(message.getName());
        return messageRepository.save(oldMessage);
    }

    public void deleteMessage(long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
        }
    }
}
