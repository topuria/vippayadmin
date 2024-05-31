package ge.vippay.admin.controller;

import ge.vippay.admin.entity.Message;
import ge.vippay.admin.model.MessageDTO;
import ge.vippay.admin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/message")
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<Message> addMessage(@RequestBody MessageDTO message) {
        Message newMessage = dtoToEntity(message);
        return ResponseEntity.ok(messageService.addMessage(newMessage));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable long id,@RequestBody MessageDTO message) {
        Message updatedMessage = dtoToEntity(message);
        return ResponseEntity.ok(messageService.updateMessage(id,updatedMessage));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message With Id "+id+" deleted");
    }

    private Message dtoToEntity(MessageDTO dto) {
        Message message = new Message();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setMessage(dto.getMessage());
        return message;
    }
}
