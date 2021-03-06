package cn.stevenjack.dormitory.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

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


    // 时间
    @Getter
    @Setter
    private String workTime;

    // 事件
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;

    public ScheduleInfo(String workTime,ScheduleStatus scheduleStatus,User user){
        this.workTime = workTime;
        this.scheduleStatus = scheduleStatus;
        this.user = user;
    }


}
