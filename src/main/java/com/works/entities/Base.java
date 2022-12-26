package com.works.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public class Base {

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Long createdDate;

    @LastModifiedBy
    private String lastModifiedBy;

    @LastModifiedDate
    private Long lastModifiedDate;


}
