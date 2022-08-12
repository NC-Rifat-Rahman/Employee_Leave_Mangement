package com.example.leave_management.Model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "User.findByEmailId",query = "select u from User u where u.email=:email")

@NamedQuery(name ="User.getAllUser",query = "select new com.example.leave_management.wrapper.UserWrapper(u.userId,u.name,u.email,u.status) from User u where u.role='user'")

@NamedQuery(name = "User.updateStatus", query = "update User u set u.status=:status where u.userId=:id")

@NamedQuery(name ="User.getAllAdmin",query = "select u.email from User u where u.role='admin'")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="user")
public class User implements Serializable
{
    private static final long serialVersionUID=1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="name")
    private String name;

    @Column(name="email", unique = true, nullable = false)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="status")
    private String status;

    @Column(name="role")
    private String role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User managerId;

}
