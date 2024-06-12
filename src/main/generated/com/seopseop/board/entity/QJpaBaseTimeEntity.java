package com.seopseop.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJpaBaseTimeEntity is a Querydsl query type for JpaBaseTimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QJpaBaseTimeEntity extends EntityPathBase<JpaBaseTimeEntity> {

    private static final long serialVersionUID = 1834557876L;

    public static final QJpaBaseTimeEntity jpaBaseTimeEntity = new QJpaBaseTimeEntity("jpaBaseTimeEntity");

    public final DateTimePath<java.time.LocalDateTime> createdTime = createDateTime("createdTime", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifiedTime = createDateTime("lastModifiedTime", java.time.LocalDateTime.class);

    public QJpaBaseTimeEntity(String variable) {
        super(JpaBaseTimeEntity.class, forVariable(variable));
    }

    public QJpaBaseTimeEntity(Path<? extends JpaBaseTimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJpaBaseTimeEntity(PathMetadata metadata) {
        super(JpaBaseTimeEntity.class, metadata);
    }

}

