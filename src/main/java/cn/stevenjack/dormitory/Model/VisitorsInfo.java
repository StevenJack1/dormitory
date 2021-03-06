package cn.stevenjack.dormitory.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 17:17
 * Table: 访客信息表
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitorsInfo implements Serializable{
    private static final long serialVersionUID = 895922237753582702L;

    // 编号
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "increment")
    private Integer id;

    // 访客姓名
    @Getter
    @Setter
    private String visitorName;

    // 访问对象
    @Getter
    @Setter
    private String otherName;

    // 宿舍楼
    @Getter
    @Setter
    private String buildName;

    // 来访事由
    @Getter
    @Setter
    private String cause;

    // 来访时间
    @Getter
    @Setter
    private Date begin;

    // 计划离开时间
    @Getter
    @Setter
    private String end;

    public VisitorsInfo( String buildName, String visitorName, String otherName, String cause, String end){
        this.buildName =buildName;
        this.visitorName =visitorName;
        this.otherName =otherName;
        this.cause =cause;
        this.end =end;
    }
}
