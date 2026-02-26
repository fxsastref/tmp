package daw.pi.platinum.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forums", indexes = {
    @Index(name = "idx_forums_user_id", columnList = "user_id"),
    @Index(name = "idx_forums_creation_date", columnList = "creation_date")
})
public class PlatinumForum {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_id")
    private Long forumId;

    @NotBlank(message = "Title is required")
    @Size(min = 1, max = 255, message = "Title between 1-255 chars")
    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 1, message = "Content cannot be empty")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    // Relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "forum", fetch = FetchType.LAZY)
    private List<PlatinumForumReply> replies;

    // Constructor
    public PlatinumForum(String title, String content, User user) {
        this.setTitle(title);
        this.setContent(content);
        this.setUser(user);
        this.setCreationDate(LocalDate.now());
    }
}
