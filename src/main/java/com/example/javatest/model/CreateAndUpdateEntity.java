package com.example.javatest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@lombok.Data
@NoArgsConstructor
@Builder
public class CreateAndUpdateEntity {

    @CreatedDate
    @Column(name = "created_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime created;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime updated;

    @PrePersist
    private void setCreated() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    private void setUpdated() {
        this.updated = LocalDateTime.now();
    }

}
