package com.sinor.stomp.websocket.member;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member {
    // top-level class
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // properties
    private String name;
    private String profile;

    @Builder
    public Member(String name, String profile) {
        this.name = name;
        this.profile = profile;
    }
}
