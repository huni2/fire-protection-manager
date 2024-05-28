package net.skhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import lombok.AllArgsConstructor;
import lombok.Data;
import net.skhu.dto.FireSuitUsage;
import net.skhu.model.Pagination1;

@Mapper
public interface FireSuitUsageMapper {

	@Data
    @AllArgsConstructor
    public class Order {
        int value;
        String label;
    }

    Order[] orders = new Order[] {
        new Order(0, "정렬 순서"),
        new Order(1, "이름 오름차순")
//        new Order(2, "스트레스지수 내림차순"),
//        new Order(3, "사용자 오름차순"),
//        new Order(4, "등록자 오름차순")
    };

    // 사용 내역 전체 조회
    @Select("""
            SELECT fsu.*, u.username
            FROM firesuitusage fsu LEFT JOIN users u ON fsu.username = u.username
    	    WHERE #{eq} = ''
               OR fsu.username = #{eq}
               OR u.username LIKE CONCAT('%', #{eq}, '%')
            ORDER BY
            (CASE WHEN #{od} = 0 THEN fsu.usageDate END) DESC,
            (CASE WHEN #{od} = 1 THEN fsu.username END) ASC
            LIMIT #{firstRecordIndex}, #{sz}
            """)
    List<FireSuitUsage> findAll(Pagination1 pagination);

    // 사용 내역 개수 조회
    @Select("""
    	    SELECT COUNT(*) AS recordCount
    	    FROM firesuitusage fsu LEFT JOIN users u ON fsu.username = u.username
    	    WHERE #{eq} = ''
               OR fsu.username = #{eq}
               OR u.username LIKE CONCAT('%', #{eq}, '%')
    	""")
    	int getCount(Pagination1 pagination);

    // 특정 ID를 가진 사용 내역 조회
    @Select("SELECT * FROM firesuitusage WHERE id = #{id}")
    FireSuitUsage findOne(String id);

    // 사용자명으로 사용 내역 조회
    @Select("SELECT * FROM firesuitusage WHERE id LIKE CONCAT('%', #{id}, '%')")
    FireSuitUsage findById(String id);

    // 사용 내역 추가
    @Insert("INSERT INTO firesuitusage (id, fireSuitId, username, usageDate, purpose, duration) "
    		+ "VALUES (#{id}, #{fireSuitId}, #{username}, #{usageDate}, #{purpose}, #{duration})")
    @Options(useGeneratedKeys=true, keyProperty="id")
    void insert(FireSuitUsage fireSuitUsage);

    // 사용 내역 수정
    @Update("UPDATE firesuitusage SET "
    		+ "fireSuitId = #{fireSuitId}, "
    		+ "username = #{username}, "
    		+ "usageDate = #{usageDate}, "
    		+ "purpose = #{purpose}, "
    		+ "duration = #{duration} "
    		+ "WHERE id = #{id}")
    void update(FireSuitUsage fireSuitUsage);

    // 사용 내역 삭제
    @Delete("DELETE FROM firesuitusage WHERE id = #{id}")
    void delete(String id);
}