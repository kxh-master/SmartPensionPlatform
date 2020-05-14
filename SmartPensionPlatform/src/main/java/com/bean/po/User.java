package com.bean.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

//@Entity(name="EntityName") 必须，用来标注一个数据库对应的实体，数据库中创建的表名默认和类名一致。其中，name 为可选，对应数据库中一个表，
//使用此注解标记 Pojo 是一个 JPA 实体。
//@Table(name=""，catalog=""，schema="") 可选，用来标注一个数据库对应的实体，数据库中创建的表名默认和类名一致。通常和 @Entity 配合使用，
//只能标注在实体的 class 定义处，表示实体对应的数据库表的信息。
//@Id 必须，@Id 定义了映射到数据库表的主键的属性，一个实体只能有一个属性被映射为主键。
//@GeneratedValue(strategy=GenerationType，generator="") 可选，strategy: 表示主键生成策略，有 AUTO、INDENTITY、SEQUENCE 和 TABLE 4 种，
//分别表示让 ORM 框架自动选择，generator: 表示主键生成器的名称。
//@Column(name = "user_code"， nullable = false， length=32) 可选，@Column 描述了数据库表中该字段的详细定义，这对于根据 JPA 注解生成数据库表结构的工具。
//name: 表示数据库表中该字段的名称，默认情形属性名称一致；nullable: 表示该字段是否允许为 null，默认为 true；unique: 表示该字段是否是唯一标识，
//默认为 false；length: 表示该字段的大小，仅对 String 类型的字段有效。
//@Transient可选，@Transient 表示该属性并非一个到数据库表的字段的映射，ORM 框架将忽略该属性。
//@Enumerated 可选，使用枚举的时候，我们希望数据库中存储的是枚举对应的 String 类型，而不是枚举的索引值，需要在属性上面添加 @Enumerated(EnumType.STRING) 注解。

@Entity
@Table(name="sys_user")
//使用懒加载时，使用对应的方法，查出关联数据
//@NamedEntityGraph(name = "User.lazy", attributeNodes = {@NamedAttributeNode("roles")})
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id",columnDefinition="bigint(36) COMMENT'id' ")
	private Integer userId;
	
	@Column(name="user_name",columnDefinition="varchar(20) COMMENT'用户名' ")
	private String userName;
	
	@Column(name="name",columnDefinition="varchar(20) COMMENT'姓名' ")
	private String name;
	
	@Column(name="pass_word",columnDefinition="varchar(50) COMMENT'密码' ")
	private String passWord;
	
	@Column(name="age",columnDefinition="int(3) COMMENT'年龄' ")
	private Integer age;
	
	@Column(name="address",columnDefinition="varchar(100) COMMENT'地址' ")
	private String address;
	
	@Column(name="delete_flag",columnDefinition="tinyint(4) COMMENT'删除标记，0:未删除，1:已删除' ")
	private Short deleteFlag;
	
	@Column(name="salt",columnDefinition="varchar(50) COMMENT'盐' ")
	private String salt;
	//下边这个注解中写的是关系，只要在一边配置，另一边详细写就好
	//	如果是FetchType.EAGER，那么表示取出这条数据时，它关联的数据也同时取出放入内存中
	//  如果是FetchType.LAZY，那么取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取
	@ManyToMany
	//@JoinTable:映射中间表
    //name属性是关联中间表的名称，
    // joinColumns属性表示，在保存关系中的表中，所保存关联关系的外键的字段。并配合@JoinColumn标记使用。
    //inverseJoinColumns  属性与joinColumns属性类似，它保存的是保存关系的另一个外键字段。
	@JoinTable(name="sys_user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<Role>();
	
	@Column(name="add_time",columnDefinition="datetime COMMENT'添加时间' ")
	private Date addTime;
	
	@Column(name="update_time",columnDefinition="datetime COMMENT'修改时间' ")
	private Date updateTime;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Short getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCredentialsSalt() {
        return userName + salt + salt;
    }
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

