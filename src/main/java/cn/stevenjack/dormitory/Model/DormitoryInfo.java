package cn.stevenjack.dormitory.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: StevenJack
 * Date: ${DATA}
 * Time: 15:22
 * To change this template use File | Settings | File Templates.
 * Table: 宿舍信息表
 */
@Entity
@Table

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryInfo implements Serializable{
    private static final long serialVersionUID = 895922917663582702L;

    // 编号
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "increment")
    private Integer id;

    // 楼号
    @Getter
    @Setter
    private String buildNumber;

    // 宿舍号
    @Getter
    @Setter
    @Column(nullable = true)
    private String dormitoryNumber;

    // 宿舍类型
    @Getter
    @Setter
    @Column(nullable = true)
    private String dormitoryType;

    // 缴费金额
    @Getter
    @Setter
    @Column(nullable = true)
    private int paymentMoney;

    // 床铺数量
    @Getter
    @Setter
    @Column(nullable = true)
    private int bedNumber;

    // 宿舍入住学生
    @Setter
    @OneToMany(mappedBy = "dormitoryInfo",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(nullable = true)
    private List<User> userList;

    public DormitoryInfo(String buildNumber,String dormitoryNumber,String dormitoryType,int bedNumber,int paymentMoney,List<User> userList){
        this.buildNumber = buildNumber;
        this.dormitoryNumber = dormitoryNumber;
        this.dormitoryType = dormitoryType;
        this.bedNumber = bedNumber;
        this.paymentMoney = paymentMoney;
        this.userList = userList;
    }

    @JsonIgnore
    public List<User> getUserList(){
        return userList;
    }

}
