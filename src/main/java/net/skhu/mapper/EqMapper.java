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
import net.skhu.dto.Eq;
import net.skhu.model.Pagination1;

@Mapper
public interface EqMapper {

	@Data
    @AllArgsConstructor
    public class Order {
        int value;
        String label;
    }

    Order[] orders = new Order[] {
        new Order(0, "정렬 순서"),
        new Order(1, "스트레스지수 오름차순"),
        new Order(2, "스트레스지수 내림차순"),
        new Order(3, "사용자 오름차순"),
        new Order(4, "등록자 오름차순")
    };

    @Select("""
            SELECT e.*, u.username
            FROM eq e LEFT JOIN users u ON e.UserId = u.id
    		WHERE #{eq} = '' OR
            e.EQ_STRESS_NUM = #{eq} OR
            e.EQ_USER LIKE CONCAT('%', #{eq}, '%') OR
            u.username LIKE CONCAT('%', #{eq}, '%')
          ORDER BY
            (CASE WHEN #{od} = 0 THEN e.EQ_SEQ END) ASC,
            (CASE WHEN #{od} = 1 THEN e.EQ_STRESS_NUM END) ASC,
            (CASE WHEN #{od} = 2 THEN e.EQ_STRESS_NUM END) DESC,
            (CASE WHEN #{od} = 3 THEN e.EQ_USER END) ASC,
            (CASE WHEN #{od} = 4 THEN u.username END) ASC
          LIMIT #{firstRecordIndex}, #{sz} """)
  List<Eq> findAll(Pagination1 pagination);

  @Select("""
          SELECT COUNT(*)
          FROM eq e LEFT JOIN users u ON e.UserId = u.id
          WHERE #{eq} = '' OR
            e.EQ_STRESS_NUM = #{eq} OR
            e.EQ_NAME LIKE CONCAT('%', #{eq}, '%') OR
            u.username LIKE CONCAT('%', #{eq}, '%') """)
  int getCount(Pagination1 pagination);

  @Select("SELECT * FROM eq WHERE EQ_SEQ = #{EQ_SEQ}")
  Eq findOne(int EQ_SEQ);

  @Select("SELECT * FROM eq WHERE EQ_USER = #{EQ_USER}")
  Eq findByEQ_USER(String EQ_USER);

  @Insert("""
      INSERT eq (EQ_SEQ, EQ_NAME, EQ_TYPE, EQ_INPUT_DATE, EQ_USER, EQ_STRESS_NUM, EQ_DISUSE, REG_DATE)
      VALUES (#{EQ_SEQ}, #{EQ_NAME}, #{EQ_TYPE}, #{EQ_INPUT_DATE}, #{EQ_USER}, #{EQ_STRESS_NUM}, #{EQ_DISUSE}, #{REG_DATE}) """)
  @Options(useGeneratedKeys=true, keyProperty="EQ_SEQ")
  void insert(Eq eq);

  @Update("""
      UPDATE eq SET
        EQ_SEQ= #{EQ_SEQ},
        EQ_NAME = #{EQ_NAME},
        EQ_TYPE = #{EQ_TYPE},
        EQ_INPUT_DATE = #{EQ_INPUT_DATE},
        EQ_USER = #{EQ_USER},
        EQ_STRESS_NUM = #{EQ_STRESS_NUM}
        EQ_DISUSE = #{EQ_DISUSE}
        REG_DATE = #{REG_DATE}
      WHERE EQ_SEQ = #{EQ_SEQ} """)
  void update(Eq eq);

  @Delete("DELETE FROM eq WHERE EQ_SEQ = #{EQ_SEQ}")
  void delete(int EQ_SEQ);
}
