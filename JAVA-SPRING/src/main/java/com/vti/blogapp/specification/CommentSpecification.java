package com.vti.blogapp.specification;

import com.vti.blogapp.entity.Comment;
import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.CommentFilterForm;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class CommentSpecification {
    public static Specification<Comment> buildSpec(CommentFilterForm form) {
        return form == null ? null : (Specification<Comment>) (root, query, criteriaBuilder) -> {
            var predicates = new LinkedList<Predicate>();
            var searh = form.getSearch();
            // WHERE title LIKE "%search"
            if (StringUtils.hasText(searh)) {
                // "     "
                var pattern = "%" + searh.trim() + "%";
                var hasNameLike = criteriaBuilder.like(root.get("name"), pattern);
                var hasEmailLike = criteriaBuilder.like(root.get("email"), pattern);
                var predicate = criteriaBuilder.or(hasNameLike, hasEmailLike);
                predicates.add(predicate);
            }
            var postId = form.getPostId();
            if (postId != null) {
                var predicate = criteriaBuilder.equal(root.get("post").get("id"), postId);
                predicates.add(predicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
