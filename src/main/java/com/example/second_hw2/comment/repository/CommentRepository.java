package com.example.second_hw2.comment.repository;

import com.example.second_hw2.comment.entity.Comment;
import com.example.second_hw2.comment.dto.CommentCountDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.example.second_hw2.comment.dto.CommentCountDto(c.schedule.id, count(c)) " +
            "from Comment c " +
            "where c.schedule.id in :scheduleIds " +
            "group by c.schedule.id")
    List<CommentCountDto> countByScheduleIds(List<Long> scheduleIds);

    List<Comment> findByScheduleId(Long scheduleId);
}
