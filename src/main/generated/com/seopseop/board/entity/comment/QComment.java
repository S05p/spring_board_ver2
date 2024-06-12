package com.seopseop.board.entity.comment;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -476718584L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QComment comment = new QComment("comment");

    public final com.seopseop.board.entity.QJpaBaseTimeEntity _super = new com.seopseop.board.entity.QJpaBaseTimeEntity(this);

    public final com.seopseop.board.entity.member.QMember comment_writer;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final DateTimePath<java.time.LocalDateTime> deletedTime = createDateTime("deletedTime", java.time.LocalDateTime.class);

    public final BooleanPath deletedTrue = createBoolean("deletedTrue");

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedTime = _super.lastModifiedTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final NumberPath<Long> orderNumber = createNumber("orderNumber", Long.class);

    public final QComment parent;

    public final com.seopseop.board.entity.post.QPost post;

    public final ListPath<Comment, QComment> replies = this.<Comment, QComment>createList("replies", Comment.class, QComment.class, PathInits.DIRECT2);

    public QComment(String variable) {
        this(Comment.class, forVariable(variable), INITS);
    }

    public QComment(Path<? extends Comment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QComment(PathMetadata metadata, PathInits inits) {
        this(Comment.class, metadata, inits);
    }

    public QComment(Class<? extends Comment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comment_writer = inits.isInitialized("comment_writer") ? new com.seopseop.board.entity.member.QMember(forProperty("comment_writer")) : null;
        this.parent = inits.isInitialized("parent") ? new QComment(forProperty("parent"), inits.get("parent")) : null;
        this.post = inits.isInitialized("post") ? new com.seopseop.board.entity.post.QPost(forProperty("post"), inits.get("post")) : null;
    }

}

