package cn.stevenjack.dormitory.Model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
 * Time: 17:12
 * Table: 缴费信息表
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo implements Serializable{
    private static final long serialVersionUID = 895922917753582702L;

    // 编号
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "increment")
    private Integer id;

    // 缴费名称
    @Getter
    @Setter
    private String paymentProjectName;

    // 缴费金额
    @Getter
    @Setter
    private String paymentMoney;

    // 缴费时间
    @Getter
    @Setter
    private Date paymentTime;

}
