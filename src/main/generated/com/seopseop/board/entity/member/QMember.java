package com.seopseop.board.entity.member;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1206211866L;

    public static final QMember member = new QMember("member1");

    public final com.seopseop.board.entity.QJpaBaseTimeEntity _super = new com.seopseop.board.entity.QJpaBaseTimeEntity(this);

    public final NumberPath<Long> commentCnt = createNumber("commentCnt", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdTime = _super.createdTime;

    public final BooleanPath deletedTrue = createBoolean("deletedTrue");

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> lastModifiedTime = _super.lastModifiedTime;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final NumberPath<Long> postCnt = createNumber("postCnt", Long.class);

    public final StringPath username = createString("username");

    public final ListPath<com.seopseop.board.entity.post.Post, com.seopseop.board.entity.post.QPost> written_post = this.<com.seopseop.board.entity.post.Post, com.seopseop.board.entity.post.QPost>createList("written_post", com.seopseop.board.entity.post.Post.class, com.seopseop.board.entity.post.QPost.class, PathInits.DIRECT2);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

