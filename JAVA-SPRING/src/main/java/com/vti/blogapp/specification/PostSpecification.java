package com.vti.blogapp.specification;

import ch.qos.logback.core.util.StringUtil;
import com.vti.blogapp.entity.Post;
import com.vti.blogapp.form.PostFilterForm;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;

public class PostSpecification {
    public static Specification<Post> buildSpec(PostFilterForm form) {
        return form == null ? null : (Specification<Post>) (root, query, criteriaBuilder) -> {
            var predicates = new LinkedList<Predicate>();
            var searh = form.getSearch();
            // WHERE title LIKE "%search"
            if (StringUtils.hasText(searh)) {
                // "     "
                var pattern = "%" + searh.trim() + "%";
                var predicate = criteriaBuilder.like(root.get("title"), pattern);
                predicates.add(predicate);
            }
            var minCreatedDate = form.getMinCreatedDate();
            if (minCreatedDate != null) {
                var minCreatedAt = LocalDateTime.of(minCreatedDate, LocalTime.MIN);
                var predicate = criteriaBuilder.greaterThanOrEqualTo(
                        root.get("createdAt"),
                        minCreatedAt
                );
                predicates.add(predicate);
            }

            var maxCreatedDate = form.getMaxCreatedDate();
            if (maxCreatedDate != null) {
                var maxCreatedAt = LocalDateTime.of(maxCreatedDate, LocalTime.MAX);
                var predicate = criteriaBuilder.lessThanOrEqualTo(
                        root.get("createdAt"),
                        maxCreatedAt
                );
                predicates.add(predicate);
            }

            var status = form.getStatus();
            if (status != null) {
                var predicate = criteriaBuilder.equal(root.get("status"), status);
                predicates.add(predicate);
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
