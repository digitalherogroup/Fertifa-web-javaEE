package com.fertifa.app.stomp;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StompChatRepository extends JpaRepository<MessagingStompChat, Long> {
}
