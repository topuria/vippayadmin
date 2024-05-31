package ge.vippay.admin.controller;

import ge.vippay.admin.entity.Message;
import ge.vippay.admin.model.MessageDTO;
import ge.vippay.admin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/message")
public class MessagesController {
    private final MessageService messageService;

    @Autowired
    public MessagesController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public Page<Message> getMessages(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageService.searchMessage(query, pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> addMessage(@RequestBody MessageDTO message) {
        Message newMessage = dtoToEntity(message);
        return ResponseEntity.ok(messageService.addMessage(newMessage));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable long id, @RequestBody MessageDTO message) {
        Message updatedMessage = dtoToEntity(message);
        return ResponseEntity.ok(messageService.updateMessage(id, updatedMessage));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable long id) {
        messageService.deleteMessage(id);
        return ResponseEntity.ok("Message With Id " + id + " deleted");
    }

    private Message dtoToEntity(MessageDTO dto) {
        Message message = new Message();
        message.setName(dto.getName());
        message.setEmail(dto.getEmail());
        message.setMessage(dto.getMessage());
        return message;
    }
}
