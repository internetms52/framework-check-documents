package io.github.internetms52.db.repository;

import io.micronaut.data.annotation.*;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

@Serdeable
@MappedEntity(value = "email_instance")
public class EmailInstanceRecord {
    @GeneratedValue
    @Id
    private Long id;

    private String category;

    @MappedProperty(value = "email_id")
    private String emailId;

    @Nullable
    @MappedProperty(value = "uid")
    private Long uid;

    @MappedProperty(value = "email_json")
    private String emailJson;

    @DateCreated
    @MappedProperty(value = "create_time")
    private LocalDateTime createTime;

    public EmailInstanceRecord(String category, String emailId,@Nullable Long uid, String emailJson) {
        this.category = category;
        this.emailId = emailId;
        this.uid = uid;
        this.emailJson = emailJson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Long getUid() {
        return uid;
    }

    @Nullable
    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getEmailJson() {
        return emailJson;
    }

    public void setEmailJson(String emailJson) {
        this.emailJson = emailJson;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
