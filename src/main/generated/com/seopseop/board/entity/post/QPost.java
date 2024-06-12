package com.seopseop.board.entity.post;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPost is a Querydsl query type for Post
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPost extends EntityPathBase<Post> {

    private static final long serialVersionUID = 927750054L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPost post = new QPost("post");

    public final com.seopseop.board.entity.QJpaBaseTimeEntity _super = new com.seopseop.board.entity.QJpaBaseTimeEntity(this);

    public final NumberPath<Long> commentCnt = createNumber("commentCnt", Long.class);

    public final ListPath<com.seopseop.board.entity.comment.Comment, com.seopseop.board.entity.comment.QComment> comments = this.<com.seopseop.board.entity.comment.Comment, com.seopseop.board.entity.comment.QComment>createList("comments", com.seopseop.board.entity.comment.Comment.class, com.seopseop.board.entity.comment.QComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final DateTimePath<java.time.LocalDateTime> deletedTime = createDateTime("deletedTime", java.time.LocalDateTime.class);

    public final BooleanPath deletedTrue = createBoolean("deletedTrue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedTime = _super.lastModifiedTime;

    public final NumberPath<Long> likes = createNumber("likes", Long.class);

    public final com.seopseop.board.entity.member.QMember post_writer;

    public final StringPath title = createString("title");

    public final NumberPath<Long> view = createNumber("view", Long.class);

    public QPost(String variable) {
        this(Post.class, forVariable(variable), INITS);
    }

    public QPost(Path<? extends Post> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPost(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPost(PathMetadata metadata, PathInits inits) {
        this(Post.class, metadata, inits);
    }

    public QPost(Class<? extends Post> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post_writer = inits.isInitialized("post_writer") ? new com.seopseop.board.entity.member.QMember(forProperty("post_writer")) : null;
    }

}

