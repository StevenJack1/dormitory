package cn.stevenjack.dormitory.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 * Table: 排班信息表
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleInfo implements Serializable{
    private static final long serialVersionUID = 891422337753582702L;

    // 编号
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "increment")
    private Integer id;

    // 楼管
    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "user")
    private User user;

    // 排班时间
    @Getter
    @Setter
    private Date workTime;


}
