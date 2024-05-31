package ge.vippay.admin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @Lob
    private String content;
}
