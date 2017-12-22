package cn.stevenjack.dormitory.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
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

@AllArgsConstructor
@NoArgsConstructor
public class DormitoryInfo implements Serializable{
    private static final long serialVersionUID = 895922917663582702L;

    // 编号
    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",strategy = "increment")
    private Integer id;

    // 住宿楼号
    @Getter
    @Setter
    private String buildNumber;

    // 宿舍号
    @Getter
    @Setter
    private String dormitoryNumber;

    // 宿舍类型
    @Getter
    @Setter
    private String dormitoryType;

    // 床铺数量
    @Getter
    @Setter
    private int bedNumber;

    // 宿舍入住学生
    @Setter
    @OneToMany(mappedBy = "dormitoryInfo",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<User> userList;

    @JsonIgnore
    public List<User> getUserList(){
        return userList;
    }

}