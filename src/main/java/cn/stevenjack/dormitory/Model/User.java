package cn.stevenjack.dormitory.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import groovy.lang.GrabExclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by arthurme on 2017/3/20.
 * Table: 用户表
 */
@Entity
@Table

@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private static final long serialVersionUID = 895922977663522702L;

    // 姓名
    @NotBlank
    @Length(max = 10,min = 1)
    @Getter
    @Setter
    private String name;

    // 学号,楼管工号
    @NotBlank
    @Getter
    @Setter
    private String studentOrDormitoryNumber;

    // 性别
    @Getter
    @Setter
    private boolean sex;

    // 年龄
    @Getter
    @Setter
    private int age;

    // 学院
    @Column(nullable = true)
    @Getter
    @Setter
    private String college;

    // 专业
    @Getter
    @Setter
    @Column(nullable = true)
    private String profession;

    // 班级号
    @Getter
    @Setter
    @Column(nullable = true)
    private String classNumber;

    // 籍贯
    @Getter
    @Setter
    private String nativePlace;

    // 缴费时间
    @Getter
    @Setter
    @Column(nullable = true)
    private Date paymentTime;

    // 是否缴费
    @Getter
    @Setter
    private boolean isPayment;

    // 宿管楼号
    @Getter
    @Setter
    @Column(nullable = true)
    private String buildName;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "dormitoryInfo")
    private DormitoryInfo dormitoryInfo;


    @Setter
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Column(nullable = true)
    private List<ScheduleInfo> scheduleInfoList;



    // 联系方式
    @Getter
    @Setter
    private String phoneNumber;

    // 用户名
    @Id
    @Getter
    @Setter
    private String userName;

    // 密码
    @Setter
    private String passWord;



    //是否禁用
    @Getter
    @Setter
    private boolean isDeleted;

    // 角色：学生，楼管，管理员
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    @JsonIgnore    //生成json不包含此字段,必须打在Getter上面
    public String getPassWord() {
        return passWord;
    }

    @JsonIgnore
    public List<ScheduleInfo> getScheduleInfoList(){
        return scheduleInfoList;
    }
}
