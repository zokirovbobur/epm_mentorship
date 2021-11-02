package com.epam.mvc.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @CreatedDate
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime createdDate = LocalDateTime.now();

  @LastModifiedDate
  @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime updatedDate = LocalDateTime.now();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof BaseEntity)) return false;
    BaseEntity that = (BaseEntity) o;
    return getId().equals(that.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
