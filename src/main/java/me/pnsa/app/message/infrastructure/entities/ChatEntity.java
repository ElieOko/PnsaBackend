package me.pnsa.app.message.infrastructure.entities;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("chats")
public class ChatEntity {
    @Id
    private Long id = 0L;
    @Column("user_id") private Long userId;
    @Column("user_young_id") private Long userYoungId;
    @Column("message") private String message;
    @Column("path_private") private String pathPrivate;
    @Column("voice") private String voice;
    @Column("created") private LocalDateTime createdAt;
    @Column("updated") private LocalDateTime updatedAt;
    @Column("is_deleted") private Boolean isDeleted = Boolean.FALSE;
}
