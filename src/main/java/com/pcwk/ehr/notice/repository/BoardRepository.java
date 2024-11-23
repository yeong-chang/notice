package com.pcwk.ehr.notice.repository;

import com.pcwk.ehr.notice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 추가적인 쿼리 메소드를 작성할 수 있습니다.
}
