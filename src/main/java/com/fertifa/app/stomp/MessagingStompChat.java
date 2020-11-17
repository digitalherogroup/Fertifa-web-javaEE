package com.fertifa.app.stomp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stomp_chat")
public class MessagingStompChat{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date created;
    private Date updated;
    private String messageTo;
    private String messageFrom;
    @Column(columnDefinition = "LONGTEXT")
    private String messageBody;
    @Column(columnDefinition = "varchar(255) default 'CHAT'")
    private String messageType;
    private Long documentId;
    @Column(columnDefinition = "integer default 0")
    private long received;

}