package me.pnsa.app.message.infrastructure.repositories;

import me.pnsa.app.message.infrastructure.entities.ChatEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends ReactiveCrudRepository<ChatEntity, Long> {
}
