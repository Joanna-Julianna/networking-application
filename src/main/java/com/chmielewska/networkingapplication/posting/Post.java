package com.chmielewska.networkingapplication.posting;

import com.chmielewska.networkingapplication.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min=1, max = 140)
    private String content;

    @ManyToOne
    private User author;

    private LocalDateTime creationDate;

}
