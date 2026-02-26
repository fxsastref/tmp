package daw.pi.platinum.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "forums_replies",
        indexes = {
            @Index(name = "idx_replies_forum_id", columnList = "forum_id"),
            @Index(name = "idx_replies_user_id", columnList = "user_id"),
            @Index(name = "idx_replies_creation_date", columnList = "creation_date")
        })
public class PlatinumForumReply {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "forum_replies_id")
    private Long forumRepliesId;

    @NotBlank(message = "Content is required")
    @Size(min = 1, message = "Content cannot be empty")
    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    // Relations
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forum_id")
    private PlatinumForum forum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructor
    public PlatinumForumReply(String content, PlatinumForum forum, User user) {
        this.setContent(content);
        this.setForum(forum);
        this.setUser(user);
        this.setCreationDate(LocalDate.now());
    }
}
