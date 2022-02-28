// package com.ebook.book.model;

// import java.util.Date;
// import java.util.List;

// import javax.persistence.Column;
// import javax.persistence.ElementCollection;
// import javax.persistence.Entity;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.ManyToOne;
// import javax.persistence.Table;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import lombok.ToString;

// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @ToString
// @Entity
// @Table(name = "user")
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "user_id")
//     private Long userId;

//     @Column(name = "user_name", nullable = false)
//     private String userName;

//     @Column(name = "user_password", nullable = false)
//     private String userPassword;

//     @Column(name = "user_email", nullable = false)
//     private String userEmail;

//     @ElementCollection(fetch = FetchType.EAGER)
//     List<User> role;
// }
